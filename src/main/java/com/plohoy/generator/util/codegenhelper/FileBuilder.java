package com.plohoy.generator.util.codegenhelper;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.file.LaunchFile;
import com.plohoy.generator.util.stringhelper.StringUtil;

import static com.plohoy.generator.model.file.FileType.LAUNCHER;

public class FileBuilder {
    private CodeBuilder codeBuilder = new CodeBuilder();
    private Source source;


    public FileBuilder addSpringBootLauncher() {
        String fileName = StringUtil.toCamelCase(source.getArtifactName()) + "Application";

        source.getSourceData()
                .get(LAUNCHER)
                .add(LaunchFile.builder()
                        .fileName(fileName + ".java")
                        .path(source.getPath() + source.getArtifactName() + source.getCorePackagePath())
                        .data(codeBuilder.buildSpringBootLaunchCode(source, fileName))
                        .build()
                );

        return this;
    }

    public FileBuilder addSimpleController() {


        return this;
    }

    public FileBuilder addSimpleService() {


        return this;
    }

    public FileBuilder addSimpleRepository() {


        return this;
    }

    public FileBuilder addMapper() {


        return this;
    }

    public FileBuilder addDomain() {


        return this;
    }

    public FileBuilder addException() {


        return this;
    }

    public FileBuilder registerSource(Source source) {
        this.source = source;
        return this;
    }

    public Source generateSource() {
        return source;
    }
}
