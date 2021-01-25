package com.plohoy.generator.model.tool.impl.docker;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.SimpleSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.impl.docker.command.*;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SLASH;

public class DockerTool extends AbstractTool {
    public DockerTool(String version) {
        super(version);
    }

    public DockerTool() {
    }

    public Source generateCode(Source source) {
        source.getSourceData()
                .get(FileType.EXTERNAL_FILE)
                .add(SimpleSourceFile.builder()
                        .path(getDockerFilePath(source.isDtoModuleExists(), source))
                        .fileName("Dockerfile")
                        .data(getDockerFileCode(source))
                        .build());
        return source;
    }

    private String getDockerFilePath(boolean dtoModuleExists, Source source) {
        String path = source.getPath() + source.getArtifactName() + SLASH;

        if (dtoModuleExists) {
            path += source.getArtifactName() + "-core" + SLASH;
        }
        return path;
    }

    private DockerEntity getDockerFileCode(Source source) {
        return DockerEntity.builder()
                .commands(new IndentList<DockerCommand>(
                        FromCommand.builder()
                                .value("openjdk:11.0.8-jre-slim")
                                .build(),
                        ExposeCommand.builder()
                                .value("8080")
                                .build(),
                        CopyCommand.builder()
                                .value(String.format(
                                        "target/%s-*.jar %s.jar",
                                        source.getArtifactName(),
                                        source.getArtifactName()
                                ))
                                .build(),
                        EntryPointCommand.builder()
                                .value(String.format(
                                        "exec java $JAVA_OPTS -jar %s.jar",
                                        source.getArtifactName()
                                ))
                                .build()
                ))
                .build();
    }
}
