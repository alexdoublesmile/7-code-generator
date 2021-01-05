package com.plohoy.generator.model.tool.impl.maven.entity.configuration;

import java.util.List;

public class MavenConfiguration {
    private String source;
    private String target;
    private List<MavenPath> annotationProcessorPaths;
    private List<MavenCompilerArg> compilerArgs;
    private MavenAdProps additionalProperties;
    private String propertyFile;
}
