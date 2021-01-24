package com.plohoy.generator.model.tool.impl.maven.pombuilder;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.impl.maven.MavenEntity;

public interface PomBuilder {
    MavenEntity getPomCode(Source source, String rootPath);
}
