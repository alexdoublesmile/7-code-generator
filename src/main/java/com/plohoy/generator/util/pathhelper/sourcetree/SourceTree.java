package com.plohoy.generator.util.pathhelper.sourcetree;

import com.plohoy.generator.view.request.SourceRequest;

import java.util.List;

public interface SourceTree {
    List<String> getRootPathList(SourceRequest request);
    String getPackagePath(SourceRequest request);
    String getDtoPackagePath(SourceRequest request);
}
