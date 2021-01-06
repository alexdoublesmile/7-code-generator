package com.plohoy.generator.util.pathhelper.sourcetree.impl;

import com.plohoy.generator.util.pathhelper.sourcetree.AbstractSourceTree;
import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SLASH_SYMBOL;

public class DTOSourceTree extends AbstractSourceTree {

    @Override
    public List<String> getRootPathList(SourceRequest request) {
        rootPathList.add(SLASH_SYMBOL);
        rootPathList.add(SLASH_SYMBOL + String.format("%s-api", request.getArtifactName()));
        rootPathList.add(SLASH_SYMBOL + String.format("%s-core", request.getArtifactName()));

        return rootPathList;
    }

    @Override
    public String getPackagePath(SourceRequest request) {
        packagePath = String.format("/%s-core", request.getArtifactName())
                + "/src/main/java/"
                + request.getGroupName().replace(".", "/")
                + SLASH_SYMBOL + request.getArtifactName() + SLASH_SYMBOL;

        return packagePath;
    }

    @Override
    public String getDtoPackagePath(SourceRequest request) {
        dtoPackagePath = String.format("/%s-dto", request.getArtifactName())
                + "/src/main/java/"
                + request.getGroupName().replace(".", "/")
                + SLASH_SYMBOL + request.getArtifactName() + SLASH_SYMBOL;

        return null;
    }
}
