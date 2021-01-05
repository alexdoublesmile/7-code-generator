package com.plohoy.generator.model.tool.impl.maven.entity.buildsection;

import com.plohoy.generator.model.tool.impl.maven.entity.configuration.MavenConfiguration;

import java.util.List;

public class MavenPlugin {
    private String groupId;
    private String artifactId;
    private String version;
    private List<MavenExecution> executions;
    private MavenConfiguration configuration;
}
