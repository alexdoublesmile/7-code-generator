package com.plohoy.generator.model.tool.impl.readme;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.SimpleSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SLASH;

public class ReadMeTool extends AbstractTool {
    public ReadMeTool(String version) {
        super(version);
    }

    public ReadMeTool() {
    }

    public Source generateCode(Source source) {
        source.getSourceData()
                .get(FileType.EXTERNAL_FILE)
                .add(SimpleSourceFile.builder()
                        .path(source.getPath() + source.getArtifactName() + SLASH)
                        .fileName("README.md")
                        .data(ReadMeEntity.builder()
                                .value("")
                                .build())
                        .build());
        return source;
    }
}
