package com.plohoy.generator.util.codegenhelper.filebuilder;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.file.*;
import com.plohoy.generator.util.codegenhelper.codebuilder.CodeBuilder;
import com.plohoy.generator.util.stringhelper.StringUtil;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.model.file.FileType.*;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class FileBuilder {
    private CodeBuilder codeBuilder = new CodeBuilder();
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
        for (ClassEntity entity : source.getMainEntities()) {
            String fileName = StringUtil.getControllerFileName(entity.getName());

            source.getSourceData()
                    .get(CONTROLLER)
                    .add(ControllerFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + CONTROLLER_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildControllerCode(source, fileName, entity, mapEntityToDto(entity)))
                            .build()
                    );
        }
        return this;
    }

    public FileBuilder addSimpleService() {
        for (ClassEntity entity : source.getMainEntities()) {
            String fileName = StringUtil.getServiceFileName(entity.getName());

            source.getSourceData()
                    .get(SERVICE)
                    .add(ServiceFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + SERVICE_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildServiceCode(source, fileName, entity, mapEntityToDto(entity)))
                            .build()
                    );
        }
        return this;
    }

    public FileBuilder addSimpleRepository() {
        for (ClassEntity entity : source.getMainEntities()) {
            String fileName = StringUtil.toCamelCase(entity.getName()) + REPO_SUFFIX;

            source.getSourceData()
                    .get(REPOSITORY)
                    .add(RepositoryFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + REPO_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildRepositoryCode(source, fileName, entity))
                            .build()
                    );
        }
        return this;
    }

    public FileBuilder addMapper() {
        for (ClassEntity entity : source.getMainEntities()) {
            String fileName = StringUtil.toCamelCase(entity.getName()) + MAPPER_SUFFIX;

            source.getSourceData()
                    .get(MAPPER)
                    .add(MapperFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + MAPPER_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildMapperCode(source, fileName, entity, mapEntityToDto(entity)))
                            .build()
                    );
        }
        return this;
    }

    public FileBuilder addDomain() {
        List<ClassEntity> allEntities = Stream.of(
                    source.getMainEntities(), source.getSecondaryEntities())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (ClassEntity entity : allEntities) {
            String fileName = StringUtil.toCamelCase(entity.getName());

            source.getSourceData()
                    .get(ENTITY)
                    .add(EntityFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + ENTITY_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildEntityCode(source, fileName, entity))
                            .build()
                    );

            if (Objects.nonNull(source.getDtoPackagePath()) && !source.getDtoPackagePath().isEmpty()) {
                String dtoFileName = fileName + DTO_SUFFIX;

                source.getSourceData()
                        .get(DTO)
                        .add(DTOFile.builder()
                                .fileName(dtoFileName + DEFAULT_JAVA_EXTENSION)
                                .path(source.getPath() + source.getArtifactName() + source.getDtoPackagePath() + DTO_SUFFIX.toLowerCase() + SLASH)
                                .data(codeBuilder.buildDtoCode(source, dtoFileName, mapEntityToDto(entity)))
                                .build()
                        );
            }
        }
        return this;
    }

    public FileBuilder addException() {
        source.getSourceData()
                .get(EXCEPTION)
                .add(ExceptionFile.builder()
                        .fileName("" + DEFAULT_JAVA_EXTENSION)
                        .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + EXCEPTION_SUFFIX.toLowerCase() + SLASH)
                        .data(codeBuilder.buildDefaultExceptionHandler(source))
                        .build()
                );

        return this;
    }

    public FileBuilder addProperties() {
        source.getSourceData()
                .get(PROPERTY_FILE)
                .add(PropertyFile.builder()
                        .fileName("application.property")
                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH)
                        .data(codeBuilder.buildApplicationProperty())
                        .build()
                );

        source.getSourceData()
                .get(PROPERTY_FILE)
                .add(PropertyFile.builder()
                        .fileName("application-dev.property")
                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH)
                        .data(codeBuilder.buildApplicationDevProperty())
                        .build()
                );

        source.getSourceData()
                .get(PROPERTY_FILE)
                .add(PropertyFile.builder()
                        .fileName("message.property")
                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH)
                        .data(codeBuilder.buildMessageProperty())
                        .build()
                );

        source.getSourceData()
                .get(PROPERTY_FILE)
                .add(PropertyFile.builder()
                        .fileName("db.property")
                        .path(source.getPath() + source.getArtifactName() + pathHelper.getResourcePath() + SLASH)
                        .data(codeBuilder.buildDBProperty())
                        .build()
                );

        return this;
    }

    public FileBuilder registerSource(Source source) {
        this.source = source;
        return this;
    }

    public Source generateSource() {
        return source;
    }

    private ClassEntity mapEntityToDto(ClassEntity mainEntity) {
        ClassEntity mainDtoEntity = null;
        List<ClassEntity> allDtoEntities = Stream.of(
                source.getMainDtoEntities(), source.getSecondaryDtoEntities())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (ClassEntity dtoEntity : allDtoEntities) {
            if ((mainEntity.getName() + DTO_SUFFIX).equals(dtoEntity.getName())) {
                mainDtoEntity = dtoEntity;
            }
        }
        return mainDtoEntity;
    }
}
