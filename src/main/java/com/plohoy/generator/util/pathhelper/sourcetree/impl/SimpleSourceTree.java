package com.plohoy.generator.util.pathhelper.sourcetree.impl;

import com.plohoy.generator.util.pathhelper.sourcetree.AbstractSourceTree;
import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SLASH;

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
                + request.getGroupName().replace(".", "/")
                + SLASH + request.getArtifactName();

        return packagePath;
    }

    @Override
    public String getDtoPackagePath(SourceRequest request) {
        return getPackagePath(request);
    }
}
