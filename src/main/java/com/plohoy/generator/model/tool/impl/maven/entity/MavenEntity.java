package com.plohoy.generator.model.tool.impl.maven.entity;

import com.plohoy.generator.model.tool.impl.maven.entity.buildsection.MavenPlugin;
import com.plohoy.generator.model.tool.impl.maven.entity.dependency.MavenDependency;
import com.plohoy.generator.model.tool.impl.maven.entity.module.MavenModule;
import com.plohoy.generator.model.tool.impl.maven.entity.property.MavenProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MavenEntity {
    private String groupId;
    private String artifactId;
    private String name;
    private String description;
    private String packagingType;
    private MavenDependency parent;
    private List<MavenModule> modules;
    private List<MavenProperty> properties;
    private List<MavenDependency> dependencies;
    private List<MavenPlugin> plugins;
}
