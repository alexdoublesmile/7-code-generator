package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class PluginTag extends TagEntity {
    public PluginTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public PluginTag(String body, IndentList<CodeEntity> innerTags) {
        this("plugin", null, body, false, innerTags);
    }

    public PluginTag(CodeEntity ... innerTags) {
        this(null, new IndentList(innerTags));
    }

    public PluginTag(String groupId, String artifactId, String version, CodeEntity ... innerTags) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId),
                new VersionTag(version));

        setInnerTags(new IndentList(
                Stream.of(getInnerTags(), Arrays.asList(innerTags))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList())));
    }

    public PluginTag(String groupId, String artifactId, String version) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId),
                new VersionTag(version));
    }

    public PluginTag(String groupId, String artifactId) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
