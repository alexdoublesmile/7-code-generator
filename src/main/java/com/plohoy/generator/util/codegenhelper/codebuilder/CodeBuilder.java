package com.plohoy.generator.util.codegenhelper.codebuilder;

import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassType;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@SuppressWarnings("Duplicates")
public class CodeBuilder {
    CodeTemplate codeTemplate = new CodeTemplate();

    public String buildSpringBootLaunchCode(Source source, String fileName) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(source.getCorePackageName()))
                .imports(codeTemplate.getSpringBootLaunchImports())
                .annotations(codeTemplate.getSpringBootLaunchAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .methods(codeTemplate.getMainMethod(
                        codeTemplate.getSpringLaunchBody(fileName)))
                .build()
                .toString();
    }

    public String buildControllerCode(Source source, String fileName, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + CONTROLLER_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getSpringRestImports(source.getCorePackageName(), mainEntity.getName()))
                .annotations(codeTemplate.getSpringRestAnnotations(source.getEndPoints().get(EndPointType.MAIN_END_POINT)))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .fields(codeTemplate.getSpringRestFields(mainEntity))
                .methods(codeTemplate.getSpringRestMethods(source.getEndPoints(), mainDtoEntity))
                .build()
                .toString();
    }

    public String buildServiceCode(Source source, String fileName, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + SERVICE_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getSpringServiceImports(source.getCorePackageName(), mainEntity.getName()))
                .annotations(codeTemplate.getSpringServiceAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .fields(codeTemplate.getSpringServiceFields(mainEntity))
                .methods(codeTemplate.getSpringServiceMethods(source.getEndPoints(), mainEntity, mainDtoEntity))
                .build()
                .toString();
    }

    public String buildRepositoryCode(Source source, String fileName, ClassEntity mainEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + REPO_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getSpringDataJpaImports(source.getCorePackageName(), mainEntity.getName()))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.INTERFACE)
                .name(fileName)
                .extendsClass(codeTemplate.getSpringDataJpaRepoClassName(mainEntity))
                .methods(codeTemplate.getSpringDataJpaDeleteMethods(mainEntity))
                .build()
                .toString();
    }

    public String buildMapperCode(Source source, String fileName, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + MAPPER_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getMapstructImports(source.getCorePackageName(), mainEntity.getName()))
                .annotations(codeTemplate.getMapstructAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.INTERFACE)
                .name(fileName)
                .methods(codeTemplate.getMapstructMethods(mainEntity))
                .build()
                .toString();
    }

    public String buildEntityCode(Source source, String fileName, ClassEntity entity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + ENTITY_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getEntityImports(source.getCorePackageName(), entity.getName()))
                .annotations(codeTemplate.getPersistenceAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .fields(codeTemplate.getEntityFields(entity))
                .build()
                .toString();
    }

    public String buildDtoCode(Source source, String dtoFileName, ClassEntity dtoEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + DTO_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getDTOImports(source.getCorePackageName(), dtoEntity.getName()))
                .annotations(codeTemplate.getDTOAnnotations())
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(dtoFileName)
                .fields(codeTemplate.getDTOFields(dtoEntity))
                .build()
                .toString();
    }

    public String buildDefaultExceptionHandler(Source source) {
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
                .build()
                .toString();
    }

    public String buildApplicationProperty() {
        return codeTemplate.getAppProperties().toString();
    }

    public String buildApplicationDevProperty() {
        return codeTemplate.getAppDevProperties().toString();
    }

    public String buildMessageProperty() {
        return codeTemplate.getMessageProperties().toString();
    }

    public String buildDBProperty() {
        return codeTemplate.getDBProperties().toString();
    }
}
