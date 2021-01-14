package com.plohoy.generator.util.pathhelper.sourcetree;

import com.plohoy.generator.util.pathhelper.sourcetree.SourceTree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSourceTree implements SourceTree {
    protected List<String> rootPathList = new ArrayList<>();
    protected String packagePath;
    protected String dtoPackagePath;
    protected String resourcePath;
}
