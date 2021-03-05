package com.plohoy.generator.util.codegenhelper.codebuilder;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.AppPropertiesEntity;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassType;
import com.plohoy.generator.model.codeentity.clazz.ImportEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.domainhelper.FieldHelper;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.plohoy.generator.model.EndPointType.CONTROLLER_END_POINT;
import static com.plohoy.generator.model.file.FileType.ENTITY;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@SuppressWarnings("Duplicates")
public class CodeBuilder {
    CodeTemplate codeTemplate = new CodeTemplate();

    public ClassEntity buildSpringBootLaunchCode(Source source, String fileName) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(source.getCorePackageName()))
                .imports(codeTemplate.getSpringBootLaunchImports())
                .annotations(codeTemplate.getSpringBootLaunchAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .methods(codeTemplate.getMainMethod(
                        codeTemplate.getSpringLaunchBody(fileName)))
                .build();
    }

    public ClassEntity buildControllerCode(Source source, String fileName, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        EndPoint mainEndPoint = mainEntity.getEndPoints()
                .stream()
                .filter(endPoint ->
                        CONTROLLER_END_POINT.equals(endPoint.getType()))
                .findFirst()
                .get();

        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + CONTROLLER_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getSpringRestImports(source.getCorePackageName(), mainEntity.getName()))
                .annotations(codeTemplate.getSpringRestAnnotations(mainEndPoint.getPath()))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .schemaDescription(mainEntity.getSchemaDescription())
                .fields(codeTemplate.getSpringRestFields(mainEntity))
                .methods(codeTemplate.getSpringRestMethods(mainEntity.getEndPoints(), mainEntity, mainDtoEntity))
                .build();
    }

    public ClassEntity buildServiceCode(Source source, String fileName, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + SERVICE_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getSpringServiceImports(source.getCorePackageName(), mainEntity.getName()))
                .annotations(codeTemplate.getSpringServiceAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .fields(codeTemplate.getSpringServiceFields(mainEntity))
                .methods(codeTemplate.getSpringServiceMethods(mainEntity.getEndPoints(), mainEntity, mainDtoEntity))
                .build();
    }

    public ClassEntity buildRepositoryCode(Source source, String fileName, ClassEntity mainEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + REPO_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getSpringDataJpaImports(source.getCorePackageName(), mainEntity.getName()))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.INTERFACE)
                .name(fileName)
                .extendsClass(codeTemplate.getSpringDataJpaRepoClassName(mainEntity))
                .methods(codeTemplate.getSpringDataJpaDeleteMethods(mainEntity))
                .build();
    }

    public ClassEntity buildMapperCode(Source source, String fileName, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        HashMap<FieldEntity, FieldEntity> loopPossibleWithMappedFields = new HashMap<>();

        mainEntity.getFields().stream()
                .filter(FieldHelper::hasAnyRelations)
                .forEach(field -> loopPossibleWithMappedFields.put(
                        field,
                        FieldHelper.getMappedFieldFromFiles(field, mainEntity.getName(), source.getSourceData().get(ENTITY))));

        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + MAPPER_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getMapStructImports(source.getCorePackageName(), mainEntity.getName(), loopPossibleWithMappedFields))
                .annotations(codeTemplate.getMapStructAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.INTERFACE)
                .name(fileName)
                .methods(codeTemplate.getMapStructMethods(mainEntity, source.getSourceData().get(ENTITY)))
                .build();
    }

    public ClassEntity buildEntityCode(Source source, String fileName, ClassEntity entity) {
        return ClassEntity.builder()
                .idType(entity.getIdType())
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + ENTITY_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getEntityImports(source.getCorePackageName(), entity.getName()))
                .annotations(codeTemplate.getPersistenceAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .fields(codeTemplate.getEntityFields(entity))
                .methods(codeTemplate.getEntityMethods(entity, source.getEntities()))
                .endPoints(entity.getEndPoints())
                .schemaDescription(entity.getSchemaDescription())
                .pageable(entity.isPageable())
                .build();
    }

    public ClassEntity buildDtoCode(Source source, String dtoFileName, ClassEntity dtoEntity) {
        return ClassEntity.builder()
                .idType(dtoEntity.getIdType())
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + DTO_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getDTOImports(source.getCorePackageName(), dtoEntity.getName()))
                .annotations(codeTemplate.getDTOAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(dtoFileName)
                .schemaDescription(dtoEntity.getSchemaDescription())
                .fields(codeTemplate.getDTOFields(dtoEntity))
                .endPoints(dtoEntity.getEndPoints())
//                .methods(codeTemplate.getEqualsAndHashMethods(dtoEntity.getName()))
                .build();
    }

    public ClassEntity buildValidationExceptionHandler(Source source) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + EXCEPTION_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getExceptionHandlerImports())
                .annotations(codeTemplate.getExceptionHandlerAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name("ValidationExceptionHandler")
                .methods(codeTemplate.getExceptionMethods())
                .build();
    }

    public ClassEntity buildValidationErrorResponse(Source source) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + EXCEPTION_SUFFIX.toLowerCase()))
                .imports(new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                        ImportEntity.builder()
                                .value(LOMBOK_MAIN_PACKAGE + ".Builder")
                                .build(),
                        ImportEntity.builder()
                                .value(LOMBOK_MAIN_PACKAGE + ".Getter")
                                .build(),
                        ImportEntity.builder()
                                .value(JAVA_UTIL_PACKAGE + ".List")
                                .build()))
                .annotations(new IndentList<>(
                        AnnotationEntity.builder()
                                .name("Builder")
                                .build(),
                        AnnotationEntity.builder()
                                .name("Getter")
                                .build()))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name("ValidationErrorResponse")
                .fields(new IndentList<FieldEntity>(DelimiterType.SEMICOLON, true, true,
                        FieldEntity.builder()
                                .modifiers(new EnumerationList<>(PRIVATE_MOD, FINAL_MOD))
                                .type(String.format(LIST_TEMPLATE, "Violation"))
                                .name("violations")
                                .build()))
                .build();
    }

    public ClassEntity buildViolation(Source source) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + EXCEPTION_SUFFIX.toLowerCase()))
                .imports(new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                        ImportEntity.builder()
                                .value(LOMBOK_MAIN_PACKAGE + ".Builder")
                                .build()))
                .annotations(new IndentList<>(AnnotationEntity.builder().name("Builder").build()))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name("Violation")
                .fields(new IndentList<FieldEntity>(DelimiterType.SEMICOLON, true, true,
                        FieldEntity.builder()
                                .modifiers(new EnumerationList<>(PRIVATE_MOD, FINAL_MOD))
                                .type("String")
                                .name("fieldName")
                                .build(),
                        FieldEntity.builder()
                                .modifiers(new EnumerationList<>(PRIVATE_MOD, FINAL_MOD))
                                .type("String")
                                .name("message")
                                .build()))
                .build();
    }

    public AppPropertiesEntity buildApplicationProperty() {
        return AppPropertiesEntity.builder()
                .propertiesMap(codeTemplate.getAppProperties())
                .build();
    }

    public AppPropertiesEntity buildApplicationDevProperty() {
        return AppPropertiesEntity.builder()
                .propertiesMap(codeTemplate.getAppDevProperties())
                .build();
    }

    public AppPropertiesEntity buildMessageProperty() {
        return AppPropertiesEntity.builder()
                .propertiesMap(codeTemplate.getMessageProperties())
                .build();
    }

    public AppPropertiesEntity buildDBProperty() {
        return AppPropertiesEntity.builder()
                .propertiesMap(codeTemplate.getDBProperties())
                .build();
    }

    public CodeEntity buildCriteriaRepoCode(Source source, String fileName, ClassEntity entity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + REPO_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getCriteriaRepoImports(source.getCorePackageName(), entity.getName()))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.INTERFACE)
                .name(fileName)
                .methods(codeTemplate.getCriteriaRepoMethods(entity))
                .build();
    }

    public CodeEntity buildCriteriaImplRepoCode(Source source, String fileName, ClassEntity entity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + REPO_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getCriteriaImplRepoImports(source.getCorePackageName(), entity.getName()))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .implInterfaces(new EnumerationList<String>(false,
                        fileName.substring(0, fileName.length() - 4)))
                .fields(new IndentList<FieldEntity>(DelimiterType.SEMICOLON, true, true,
                        FieldEntity.builder()
                                .annotations(new IndentList<AnnotationEntity>(AnnotationEntity.builder().name("Autowired").build()))
                                .modifiers(getPublicMod())
                                .type("EntityManager")
                                .name("em")
                                .build()))
                .methods(codeTemplate.getCriteriaImplRepoMethods(entity))
                .build();
    }
}
