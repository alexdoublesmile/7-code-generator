package com.plohoy.generator.util.pathhelper.sourcetree.impl;

import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

public class SimpleSourceTree extends AbstractSourceTree {
    @Override
    public List<String> getRootPathList(SourceRequest request) {
        rootPathList.add(SLASH);

        return rootPathList;
    }

    @Override
    public String getPackagePath(SourceRequest request) {
        packagePath = SLASH
                + "/src/main/java/"
                + request.getGroupName().replace(".", "/")
                + SLASH + request.getArtifactName();

        return packagePath;
    }

//    @Override
//    public List<String> getSourcePathList(SourceRequest request) {
//        addSrcTree(
//                SLASH,
//                request.getGroupName(),
//                request.getArtifactName(),
//                true);
//
//        return pathList;
//    }
}
