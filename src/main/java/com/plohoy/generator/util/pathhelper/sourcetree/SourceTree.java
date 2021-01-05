package com.plohoy.generator.util.pathhelper.sourcetree;

import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

public interface SourceTree {
    String SLASH = "/";

    List<String> getRootPathList(SourceRequest request);

//    List<String> getSourcePathList(SourceRequest request);

    String getPackagePath(SourceRequest request);
}
