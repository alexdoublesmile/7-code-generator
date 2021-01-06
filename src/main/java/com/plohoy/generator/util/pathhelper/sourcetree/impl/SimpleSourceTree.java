package com.plohoy.generator.util.pathhelper.sourcetree.impl;

import com.plohoy.generator.util.pathhelper.sourcetree.AbstractSourceTree;
import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SLASH_SYMBOL;

public class SimpleSourceTree extends AbstractSourceTree {
    @Override
    public List<String> getRootPathList(SourceRequest request) {
        rootPathList.add(SLASH_SYMBOL);

        return rootPathList;
    }

    @Override
    public String  getPackagePath(SourceRequest request) {
        packagePath = SLASH_SYMBOL
                + "/src/main/java/"
                + request.getGroupName().replace(".", "/")
                + SLASH_SYMBOL + request.getArtifactName();

        return packagePath;
    }

    @Override
    public String getDtoPackagePath(SourceRequest request) {
        return getPackagePath(request);
    }
}
