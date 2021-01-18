package com.plohoy.generator.model.tool.impl.maven.pombuilder;

import com.plohoy.generator.model.Source;

public interface PomBuilder {
    String getPomCode(Source source, String rootPath);
}
