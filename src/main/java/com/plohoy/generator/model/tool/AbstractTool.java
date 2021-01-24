package com.plohoy.generator.model.tool;

import com.plohoy.generator.model.Source;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY;

@Data
public abstract class AbstractTool {
    private String name;
    private String version;
    private boolean dependency;
    private String toolType;

    private String defaultGroupId;
    private String defaultArtifactId;
    private String scope;

    public AbstractTool(String version, String toolType) {
        this.version = version;
        this.toolType = toolType;
    }

    public AbstractTool() {
    }

    public AbstractTool(String version) {
        this(version, EMPTY);
    }

    public AbstractTool(String version, String groupId, String artifactId, String scope, boolean dependency) {
        this(version, groupId, artifactId, scope, dependency, EMPTY);
    }

    public AbstractTool(String version, String groupId, String artifactId, String scope, boolean dependency, String toolType) {
        this.version = version;
        this.defaultGroupId = groupId;
        this.defaultArtifactId = artifactId;
        this.scope = scope;
        this.toolType = toolType;
        setDependency(dependency);
    }

    public abstract Source generateCode(Source source);
}
