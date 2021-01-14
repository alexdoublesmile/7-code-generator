package com.plohoy.generator.util.pathhelper;

import com.plohoy.generator.util.pathhelper.sourcetree.SourceTree;
import com.plohoy.generator.util.pathhelper.sourcetree.impl.DTOSourceTree;
import com.plohoy.generator.util.pathhelper.sourcetree.impl.SimpleSourceTree;
import com.plohoy.generator.view.request.SourceRequest;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class PathHelper {
    private SourceTreeHandler sourceTreeHandler = new SourceTreeHandler();

    public List<String> getRootPathList(SourceRequest request) {
        return sourceTreeHandler
                .getSourceTrees()
                .get(request.isDtoModuleExists())
                .getRootPathList(request);
    }

    public String getPackagePath(SourceRequest request) {
        return sourceTreeHandler
                .getSourceTrees()
                .get(request.isDtoModuleExists())
                .getPackagePath(request);
    }

    public String getDtoPackagePath(SourceRequest request) {
        return sourceTreeHandler
                .getSourceTrees()
                .get(request.isDtoModuleExists())
                .getDtoPackagePath(request);
    }

    public String getResourcePath(SourceRequest request) {
        return sourceTreeHandler
                .getSourceTrees()
                .get(request.isDtoModuleExists())
                .getResourcePath(request);
    }

    @Getter
    private class SourceTreeHandler {
        private HashMap<Boolean, SourceTree> sourceTrees;

        public SourceTreeHandler() {
            sourceTrees = new HashMap<>();
            sourceTrees.put(true, new DTOSourceTree());
            sourceTrees.put(false, new SimpleSourceTree());
        }
    }
}
