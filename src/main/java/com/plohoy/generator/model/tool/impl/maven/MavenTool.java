package com.plohoy.generator.model.tool.impl.maven;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.SimpleSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.impl.maven.tag.TagEntity;

public class MavenTool extends AbstractTool {
    public MavenTool(String version) {
        super(version);
    }

    public MavenTool() {
    }

    private MavenUtil mavenUtil = new MavenUtil();

    @Override
    public Source generateCode(Source source) {
        for (String rootPath : source.getRelativeRootPaths()) {
            source.getSourceData()
                    .get(FileType.EXTERNAL_FILE)
                    .add(SimpleSourceFile.builder()
                            .path(source.getPath() + source.getArtifactName() + rootPath)
                            .fileName("pom.xml")
                            .data(mavenUtil.getPomCode(source, rootPath))
                            .build());
        }
        return source;
    }
}
