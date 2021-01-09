package com.plohoy.generator.util.codegenhelper.codebuilder;

import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassType;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;

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

    public String buildControllerCode(Source source, String fileName, String mainEntityName, ClassEntity mainDtoEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DOT + CONTROLLER_SUFFIX.toLowerCase()))
                .imports(codeTemplate.getSpringRestImports(source.getCorePackageName(), mainEntityName))
                .annotations(codeTemplate.getSpringRestAnnotations(source.getEndPoints().get(EndPointType.MAIN_END_POINT)))
                .modifiers(codeTemplate.getPublicMod())
                .classType(ClassType.CLASS)
                .name(fileName)
                .fields(codeTemplate.getEntityServiceField(mainEntityName))
                .methods(codeTemplate.getSpringRestMethods(source.getEndPoints(), mainDtoEntity))
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
                .extendsClass(codeTemplate.getJpaRepoClassName(mainEntity))
                .methods(codeTemplate.getRepoDeleteMethods(mainEntity))
                .build()
                .toString();
    }

//    public String buildServiceCode(Source source, String fileName, ClassEntity mainEntity) {
//        return ClassEntity.builder()
//                .packageString(codeTemplate.getPackageString(
//                        source.getCorePackageName() + DelimiterType.DOT + SERVICE_SUFFIX.toLowerCase()))
//                .imports(codeTemplate.generateSpringServiceImports(source.getCorePackageName(), mainEntity.getName()))
//                .annotations(codeTemplate.generateSpringServiceAnnotations())
//                .modifiers(codeTemplate.getPublicMod())
//                .classType(ClassType.CLASS)
//                .name(fileName)
//                .fields(codeTemplate.generateServiceFields(mainEntity))
//                .constructors(codeTemplate.generateServiceConstructor(fileName, mainEntity))
//                .methods(codeTemplate.getSpringRestMethods(source, fileName, mainEntity))
//                .build()
//                .toString();
//    }
//
//    public String buildMapperCode(Source source, String fileName, ClassEntity mainEntity) {
//        return ClassEntity.builder()
//                .packageString(codeTemplate.getPackageString(
//                        source.getCorePackageName() + DelimiterType.DOT + MAPPER_SUFFIX.toLowerCase()))
//                .imports(codeTemplate.generateMapstructImports(source.getCorePackageName(), mainEntity.getName()))
//                .annotations(codeTemplate.generateMapstructAnnotations())
//                .modifiers(codeTemplate.getPublicMod())
//                .classType(ClassType.INTERFACE)
//                .name(fileName)
//                .methods(codeTemplate.generateMapperMethods(mainEntity))
//                .build()
//                .toString();
//    }

//    public String buildEntityCode(Source source, String fileName, ClassEntity entity) {
//        return ClassEntity.builder()
//                .packageString(codeTemplate.getPackageString(
//                        source.getCorePackageName() + DelimiterType.DOT + ENTITY_SUFFIX.toLowerCase()))
//                .imports(codeTemplate.generateEntityImports(source.getCorePackageName(), entity.getName()))
//                .annotations(codeTemplate.generatePersistenceAnnotations())
//                .modifiers(codeTemplate.getPublicMod())
//                .classType(ClassType.CLASS)
//                .name(fileName)
//                .fields(codeTemplate.generateEntityFields(entity))
//                .build()
//                .toString();
//    }
//
//    public String buildDtoCode(Source source, String dtoFileName, ClassEntity entity) {
//        return ClassEntity.builder()
//                .packageString(codeTemplate.getPackageString(
//                        source.getCorePackageName() + DelimiterType.DOT + DTO_SUFFIX.toLowerCase()))
//                .imports(codeTemplate.generateDTOImports(source.getCorePackageName(), entity.getName()))
//                .annotations(codeTemplate.generateDTOAnnotations())
//                .modifiers(codeTemplate.getPublicMod())
//                .classType(ClassType.CLASS)
//                .name(dtoFileName)
//                .fields(codeTemplate.generateDTOFields(entity))
//                .build()
//                .toString();
//    }
//
//    public String buildDefaultExceptionHandler(Source source) {
//        return ClassEntity.builder()
//                .packageString(codeTemplate.getPackageString(
//                        source.getCorePackageName() + DelimiterType.DOT + EXCEPTION_SUFFIX.toLowerCase()))
//                .imports(codeTemplate.generateExceptionHandlerImports())
//                .annotations(codeTemplate.generateExceptionHandlerAnnotations())
//                .modifiers(codeTemplate.getPublicMod())
//                .classType(ClassType.CLASS)
//                .name("ExceptionResolver")
//                .methods(codeTemplate.generateExceptionMethods())
//                .build()
//                .toString();
//    }
//
//    public String buildApplicationProperty() {
//        return new IndentList<String>(DelimiterType.NONE, false,
//                codeTemplate.generateAppProperties());
//    }
//
//    public String buildApplicationDevProperty() {
//        return new IndentList<String>(DelimiterType.NONE, false,
//                codeTemplate.generateAppDevProperties());
//    }
//
//    public String buildMessageProperty() {
//        return new IndentList<String>(DelimiterType.NONE, false,
//                codeTemplate.generateMessageProperties());
//    }
//
//    public String buildDBProperty() {
//        return new IndentList<String>(DelimiterType.NONE, false,
//                codeTemplate.generateDBProperties());
//    }
}
