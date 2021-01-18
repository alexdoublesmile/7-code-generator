package com.plohoy.generator.util.pathhelper.sourcetree.impl;

import com.plohoy.generator.util.pathhelper.sourcetree.AbstractSourceTree;
import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY;

public class SimpleSourceTree extends AbstractSourceTree {
    @Override
    public List<String> getRootPathList(SourceRequest request) {
        rootPathList.add(SLASH);

        return rootPathList;
    }

    @Override
    public String  getPackagePath(SourceRequest request) {
        packagePath = SLASH
                + "/src/main/java/"
                + request.getGroupName().replace(DOT, SLASH)
                + SLASH + request.getArtifactName().replace(MINUS, EMPTY) + SLASH;

        return packagePath;
    }

    @Override
    public String getDtoPackagePath(SourceRequest request) {
        return getPackagePath(request);
    }

    @Override
    public String getResourcePath(SourceRequest request) {
        resourcePath = "/src/main/resources/";

        return resourcePath;
    }
}
