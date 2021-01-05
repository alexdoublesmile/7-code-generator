package com.plohoy.generator.util.pathhelper.sourcetree.impl;

import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

public class DTOSourceTree extends AbstractSourceTree {
    @Override
    public List<String> getRootPathList(SourceRequest request) {
        rootPathList.add(SLASH);
        rootPathList.add(SLASH + String.format("%s-api", request.getArtifactName()));
        rootPathList.add(SLASH + String.format("%s-core", request.getArtifactName()));

        return rootPathList;
    }

    @Override
    public String getPackagePath(SourceRequest request) {
        packagePath = String.format("/%s-core", request.getArtifactName())
                + "/src/main/java/"
                + request.getGroupName().replace(".", "/")
                + SLASH + request.getArtifactName() + SLASH;

        return packagePath;
    }

//    @Override
//    public List<String> getSourcePathList(SourceRequest request) {
//        addSrcTree(
//                String.format("/%s-core", request.getArtifactName()),
//                request.getGroupName(),
//                request.getArtifactName(),
//                true);
//
//        String apiPackagePath = addSrcTree(
//                String.format("/%s-api", request.getArtifactName()),
//                request.getGroupName(),
//                request.getArtifactName(),
//                false);
//
//        pathList.add(apiPackagePath + SLASH + "dto");
//
//        return pathList;
//    }
}
