package com.plohoy.generator.model.tool.impl.maven;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.impl.maven.entity.MavenEntity;

import java.io.File;
import java.util.HashMap;

public class MavenTool extends AbstractTool {
    private HashMap<String, MavenEntity> pathsOfPomData;

    private String pomData;

    public MavenTool(String version) {
        super(version);
    }

    public MavenTool() {
    }

    @Override
    public Source generateCode(Source source) {
//        for (String rootPath : source.getRelativeRootPaths()) {
//
//            pathsOfPomData.put(
//                    rootPath + "/pom.xml",
//                    buildMavenEntity(rootPath, source));
//        }

        return source;
    }

    private Source buildBasicPomFile(Source source, String rootPath) {
//        File pomFile = new File(rootPath + "/pom.xml");
//        StringBuilder pomData = new StringBuilder();
//
//        pomData.append(MavenUtil.getPomHeader());
//        pomData.append(MavenUtil.getPomFooter());
//
//        outputHelper.writeDataToFile(pomData.toString(), pomFile);

        return source;
    }

    private Source buildPomFile(Source source, String rootPath) {
//        buildMainPomFile(rootDir.getPath() + "/");
//        buildApiPomFile(apiModuleDir.getPath() + "/");
//        buildCorePomFile(coreModuleDir.getPath() + "/");
        return source;
    }
}
