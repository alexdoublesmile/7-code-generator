package com.plohoy.generator.util.codegenhelper.codebuilder;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.AppPropertiesEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassType;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.domainhelper.DomainHelper;

import java.util.List;
import java.util.stream.Collectors;

import static com.plohoy.generator.model.EndPointType.CONTROLLER_END_POINT;
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
                .methods(codeTemplate.getSpringRestMethods(mainEntity.getEndPoints(), mainDtoEntity))
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
        List<FieldEntity> relationFields = mainEntity.getFields().stream()
                .filter(field -> DomainHelper.hasOneToRelation(field))
                .collect(Collectors.toList());

        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + MAPPER_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getMapstructImports(source.getCorePackageName(), mainEntity.getName(), relationFields))
                .annotations(codeTemplate.getMapstructAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.INTERFACE)
                .name(fileName)
                .methods(codeTemplate.getMapstructMethods(mainEntity, relationFields))
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
//                .methods(codeTemplate.getEqualsAndHashMethods(dtoEntity.getName()))
                .build();
    }

    public ClassEntity buildDefaultExceptionHandler(Source source) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + EXCEPTION_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getExceptionHandlerImports())
                .annotations(codeTemplate.getExceptionHandlerAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name("GlobalExceptionHandler")
                .extendsClass("ResponseEntityExceptionHandler")
                .methods(codeTemplate.getExceptionMethods())
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
}
