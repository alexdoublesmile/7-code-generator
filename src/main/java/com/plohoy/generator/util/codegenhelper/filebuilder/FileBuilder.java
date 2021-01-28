package com.plohoy.generator.util.codegenhelper.filebuilder;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.file.*;
import com.plohoy.generator.util.codegenhelper.codebuilder.CodeBuilder;
import com.plohoy.generator.util.stringhelper.StringUtil;

import java.util.List;
import java.util.Objects;

import static com.plohoy.generator.model.file.FileType.*;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class FileBuilder {
    private CodeBuilder codeBuilder = new CodeBuilder();
    private Source source;

    public FileBuilder addSpringBootLauncher() {
        String fileName = StringUtil.getLauncherFileName(source.getArtifactName());

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

    public FileBuilder addSimpleController(List<ClassEntity> mainEntities) {
        for (ClassEntity entity : mainEntities) {
            String fileName = StringUtil.getControllerFileName(entity.getName());

            source.getSourceData()
                    .get(CONTROLLER)
                    .add(ControllerFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + CONTROLLER_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildControllerCode(source, fileName, entity, findDtoByEntityName(entity.getName())))
                            .build()
                    );
        }
        return this;
    }

    public FileBuilder addSimpleService(List<ClassEntity> mainEntities) {
        for (ClassEntity entity : mainEntities) {
            String fileName = StringUtil.getServiceFileName(entity.getName());

            source.getSourceData()
                    .get(SERVICE)
                    .add(ServiceFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + SERVICE_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildServiceCode(source, fileName, entity, findDtoByEntityName(entity.getName())))
                            .build()
                    );
        }
        return this;
    }

    public FileBuilder addSimpleRepository(List<ClassEntity> mainEntities) {
        for (ClassEntity entity : mainEntities) {
            String fileName = StringUtil.getRepoFileName(entity.getName());

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

    public FileBuilder addMapper(List<ClassEntity> mainEntities) {
        for (ClassEntity entity : mainEntities) {
            String fileName = StringUtil.getMapperFileName(entity.getName());

            source.getSourceData()
                    .get(MAPPER)
                    .add(MapperFile.builder()
                            .fileName(fileName + DEFAULT_JAVA_EXTENSION)
                            .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath() + MAPPER_SUFFIX.toLowerCase() + SLASH)
                            .data(codeBuilder.buildMapperCode(source, fileName, entity, findDtoByEntityName(entity.getName())))
                            .build()
                    );
        }
        return this;
    }

    public FileBuilder addDomain() {
        for (ClassEntity entity : source.getEntities()) {
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
                                .data(codeBuilder.buildDtoCode(source, dtoFileName, findDtoByEntityName(entity.getName())))
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
                        .fileName("GlobalExceptionHandler" + DEFAULT_JAVA_EXTENSION)
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
                        .fileName("application.properties")
                        .path(source.getPath() + source.getArtifactName() + source.getResourcePath() + SLASH)
                        .data(codeBuilder.buildApplicationProperty())
                        .build()
                );

        source.getSourceData()
                .get(PROPERTY_FILE)
                .add(PropertyFile.builder()
                        .fileName("application-dev.properties")
                        .path(source.getPath() + source.getArtifactName() + source.getResourcePath() + SLASH)
                        .data(codeBuilder.buildApplicationDevProperty())
                        .build()
                );

        source.getSourceData()
                .get(PROPERTY_FILE)
                .add(PropertyFile.builder()
                        .fileName("message.properties")
                        .path(source.getPath() + source.getArtifactName() + source.getResourcePath() + SLASH)
                        .data(codeBuilder.buildMessageProperty())
                        .build()
                );

        source.getSourceData()
                .get(PROPERTY_FILE)
                .add(PropertyFile.builder()
                        .fileName("db.properties")
                        .path(source.getPath() + source.getArtifactName() + source.getResourcePath() + SLASH)
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

    private ClassEntity findDtoByEntityName(String mainEntityName) {
        ClassEntity mainDtoEntity = null;

        for (ClassEntity dtoEntity : source.getDtoEntities()) {
            if ((mainEntityName + DTO_SUFFIX).equals(dtoEntity.getName())) {
                mainDtoEntity = dtoEntity;
            }
        }
        return mainDtoEntity;
    }
}
