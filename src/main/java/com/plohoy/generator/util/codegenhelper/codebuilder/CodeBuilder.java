package com.plohoy.generator.util.codegenhelper.codebuilder;

import com.plohoy.generator.model.EntryPointType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.ClassEntity;
import com.plohoy.generator.model.codeentity.ClassType;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@SuppressWarnings("Duplicates")
public class CodeBuilder {
    CodeTemplate codeTemplate = new CodeTemplate();

    public String buildSpringBootLaunchCode(Source source, String fileName) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName()))
                .imports(codeTemplate.generateSpringBootLauncherImports())
                .annotations(codeTemplate.generateSpringBootLauncherAnnotations())
                .modifiers(codeTemplate.getPublicModifier())
                .classType(ClassType.CLASS)
                .name(fileName)
                .methods(codeTemplate.generateMainMethod(
                        codeTemplate.generateSpringLauncherBody(fileName)))
                .build()
                .toString();
    }

    public String buildControllerCode(Source source, String fileName, ClassEntity mainEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DelimiterType.DOT.getDelimiter() + CONTROLLER_SUFFIX.toLowerCase()))
                .imports(codeTemplate.generateSpringRestControllerImports(source.getCorePackageName(), mainEntity.getName()))
                .annotations(codeTemplate.generateSpringRestControllerAnnotations(source.getEntryPoints().get(EntryPointType.MAIN_ENTRY_POINT)))
                .modifiers(codeTemplate.getPublicModifier())
                .classType(ClassType.CLASS)
                .name(fileName)
                .fields(codeTemplate.getEntityServiceField(mainEntity))
                .methods(codeTemplate.generateControllerMethods(source, mainEntity))
                .build()
                .toString();
    }

//    public String buildServiceCode(Source source, String fileName, ClassEntity mainEntity) {
//        return ClassEntity.builder()
//                .packageString(codeTemplate.getPackageString(
//                        source.getCorePackageName() + DelimiterType.DOT + SERVICE_SUFFIX.toLowerCase()))
//                .imports(codeTemplate.generateSpringServiceImports(source.getCorePackageName(), mainEntity.getName()))
//                .annotations(codeTemplate.generateSpringServiceAnnotations())
//                .modifiers(codeTemplate.getPublicModifier())
//                .classType(ClassType.CLASS)
//                .name(fileName)
//                .fields(codeTemplate.generateServiceFields(mainEntity))
//                .constructors(codeTemplate.generateServiceConstructor(fileName, mainEntity))
//                .methods(codeTemplate.generateControllerMethods(source, fileName, mainEntity))
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
//                .modifiers(codeTemplate.getPublicModifier())
//                .classType(ClassType.INTERFACE)
//                .name(fileName)
//                .methods(codeTemplate.generateMapperMethods(mainEntity))
//                .build()
//                .toString();
//    }

    public String buildRepositoryCode(Source source, String fileName, ClassEntity mainEntity) {
        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName() + DelimiterType.DOT + REPO_SUFFIX.toLowerCase()))
                .imports(codeTemplate.generateSpringDataJpaImports(source.getCorePackageName(), mainEntity.getName()))
                .modifiers(codeTemplate.getPublicModifier())
                .classType(ClassType.INTERFACE)
                .name(fileName)
                .extendsClass(codeTemplate.getJpaRepositoryClassName(mainEntity))
                .methods(codeTemplate.generateRepoDeleteMethods(mainEntity))
                .build()
                .toString();
    }

//    public String buildEntityCode(Source source, String fileName, ClassEntity entity) {
//        return ClassEntity.builder()
//                .packageString(codeTemplate.getPackageString(
//                        source.getCorePackageName() + DelimiterType.DOT + ENTITY_SUFFIX.toLowerCase()))
//                .imports(codeTemplate.generateEntityImports(source.getCorePackageName(), entity.getName()))
//                .annotations(codeTemplate.generatePersistenceAnnotations())
//                .modifiers(codeTemplate.getPublicModifier())
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
//                .modifiers(codeTemplate.getPublicModifier())
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
//                .modifiers(codeTemplate.getPublicModifier())
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
