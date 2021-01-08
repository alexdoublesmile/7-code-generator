package com.plohoy.generator.util.codegenhelper.filebuilder;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.ClassEntity;
import com.plohoy.generator.model.file.*;
import com.plohoy.generator.util.codegenhelper.codebuilder.CodeBuilder;
import com.plohoy.generator.util.pathhelper.PathHelper;
import com.plohoy.generator.util.stringhelper.StringUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.model.file.FileType.*;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class FileBuilder {
    private CodeBuilder codeBuilder = new CodeBuilder();
    private PathHelper pathHelper = new PathHelper();
    private Source source;

    public FileBuilder addSpringBootLauncher() {
        String fileName = StringUtil.toCamelCase(source.getArtifactName()) + LAUNCHER_SUFFIX;

        source.getSourceData()
                .get(LAUNCHER)
                .add(LaunchFile.builder()
                        .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                        .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath())
                        .data(codeBuilder.buildSpringBootLaunchCode(source, fileName))
                        .build()
                );
        return this;
    }

    public FileBuilder addSimpleController() {
        for (ClassEntity mainEntity : source.getMainEntities()) {
            String fileName = StringUtil.toCamelCase(mainEntity.getName()) + CONTROLLER_SUFFIX;

            source.getSourceData()
                    .get(CONTROLLER)
                    .add(ControllerFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + CONTROLLER_SUFFIX.toLowerCase() + SLASH_SYMBOL)
                            .data(codeBuilder.buildControllerCode(source, fileName, mainEntity))
                            .build()
                    );
        }
        return this;
    }

//    public FileBuilder addSimpleService() {
//        for (ClassEntity mainEntity : source.getMainEntities()) {
//            String fileName = StringUtil.toCamelCase(mainEntity.getName()) + SERVICE_SUFFIX;
//
//            source.getSourceData()
//                    .get(SERVICE)
//                    .add(ServiceFile.builder()
//                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
//                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + SERVICE_SUFFIX.toLowerCase() + SLASH_SYMBOL)
//                            .data(codeBuilder.buildServiceCode(source, fileName, mainEntity))
//                            .build()
//                    );
//        }
//        return this;
//    }

    public FileBuilder addSimpleRepository() {
        for (ClassEntity mainEntity : source.getMainEntities()) {
            String fileName = StringUtil.toCamelCase(mainEntity.getName()) + REPO_SUFFIX;

            source.getSourceData()
                    .get(REPOSITORY)
                    .add(RepositoryFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + REPO_SUFFIX.toLowerCase() + SLASH_SYMBOL)
                            .data(codeBuilder.buildRepositoryCode(source, fileName, mainEntity))
                            .build()
                    );
        }
        return this;
    }

//    public FileBuilder addMapper() {
//        for (ClassEntity mainEntity : source.getMainEntities()) {
//            String fileName = StringUtil.toCamelCase(mainEntity.getName()) + MAPPER_SUFFIX;
//
//            source.getSourceData()
//                    .get(MAPPER)
//                    .add(MapperFile.builder()
//                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
//                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + MAPPER_SUFFIX.toLowerCase() + SLASH_SYMBOL)
//                            .data(codeBuilder.buildMapperCode(source, fileName, mainEntity))
//                            .build()
//                    );
//        }
//        return this;
//    }
//
//    public FileBuilder addDomain() {
//        List<ClassEntity> allEntities = Stream.of(
//                    source.getMainEntities(), source.getSecondaryEntities())
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//
//        for (ClassEntity entity : allEntities) {
//            String fileName = StringUtil.toCamelCase(entity.getName());
//
//            source.getSourceData()
//                    .get(ENTITY)
//                    .add(EntityFile.builder()
//                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
//                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + ENTITY_SUFFIX.toLowerCase() + SLASH_SYMBOL)
//                            .data(codeBuilder.buildEntityCode(source, fileName, entity))
//                            .build()
//                    );
//
//            if (!source.getDtoPackagePath().isEmpty()) {
//                String dtoFileName = fileName + DTO_SUFFIX;
//
//                source.getSourceData()
//                        .get(DTO)
//                        .add(DTOFile.builder()
//                                .fileName(dtoFileName + DEFAULT_JAVA_EXTENSION)
//                                .path(source.getPath() + source.getArtifactName() + source.getDtoPackagePath() + DTO_SUFFIX.toLowerCase() + SLASH_SYMBOL)
//                                .data(codeBuilder.buildDtoCode(source, dtoFileName, entity))
//                                .build()
//                        );
//            }
//        }
//        return this;
//    }
//
//    public FileBuilder addException() {
//        source.getSourceData()
//                .get(EXCEPTION)
//                .add(ExceptionFile.builder()
//                        .fileName("" + DEFAULT_JAVA_EXTENSION)
//                        .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + EXCEPTION_SUFFIX.toLowerCase() + SLASH_SYMBOL)
//                        .data(codeBuilder.buildDefaultExceptionHandler(source))
//                        .build()
//                );
//
//        return this;
//    }
//
//    public FileBuilder addProperties() {
//        source.getSourceData()
//                .get(PROPERTY_FILE)
//                .add(PropertyFile.builder()
//                        .fileName("application.property")
//                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH_SYMBOL)
//                        .data(codeBuilder.buildApplicationProperty())
//                        .build()
//                );
//
//        source.getSourceData()
//                .get(PROPERTY_FILE)
//                .add(PropertyFile.builder()
//                        .fileName("application-dev.property")
//                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH_SYMBOL)
//                        .data(codeBuilder.buildApplicationDevProperty())
//                        .build()
//                );
//
//        source.getSourceData()
//                .get(PROPERTY_FILE)
//                .add(PropertyFile.builder()
//                        .fileName("message.property")
//                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH_SYMBOL)
//                        .data(codeBuilder.buildMessageProperty())
//                        .build()
//                );
//
//        source.getSourceData()
//                .get(PROPERTY_FILE)
//                .add(PropertyFile.builder()
//                        .fileName("db.property")
//                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH_SYMBOL)
//                        .data(codeBuilder.buildDBProperty())
//                        .build()
//                );
//
//        return this;
//    }

    public FileBuilder registerSource(Source source) {
        this.source = source;
        return this;
    }

    public Source generateSource() {
        return source;
    }
}
