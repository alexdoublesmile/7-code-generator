package com.plohoy.generator.util.pathhelper.sourcetree.impl;

import com.plohoy.generator.util.pathhelper.sourcetree.SourceTree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSourceTree implements SourceTree {
    protected List<String> rootPathList = new ArrayList<>();
    protected String packagePath;
//    protected List<String> pathList = new ArrayList<>();

//    protected String addSrcTree(String rootPath, String groupName, String artifactName, boolean isCore) {
//        String corePackagePath = rootPath
//                        + "/src/main/java/"
//                        + groupName.replace(".", "/")
//                        + SLASH + artifactName;
//        pathList.add(corePackagePath);
//        pathList.add(rootPath + "/src/main/java");
//        pathList.add(rootPath + "/src/main/resources");
//        pathList.add(rootPath + "/src/test");
//
//        if (isCore) {
//            packagePath = corePackagePath;
//
//            pathList.add(corePackagePath + "/codeentity");
//            pathList.add(corePackagePath + "/controller");
//            pathList.add(corePackagePath + "/service");
//            pathList.add(corePackagePath + "/repository");
//            pathList.add(corePackagePath + "/mapper");
//            pathList.add(corePackagePath + "/exception");
//        }
//        return corePackagePath;
//    }
//
//    @Override
//    public String getPackagePath(SourceRequest request) {
//        return packagePath;
//    }
}
