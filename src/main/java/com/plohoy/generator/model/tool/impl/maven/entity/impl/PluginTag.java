package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Data
public class PluginTag extends TagEntity {
    public PluginTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public PluginTag(String body, IndentList<TagEntity> innerTags) {
        this("plugin", body, false, innerTags);
    }

    public PluginTag(TagEntity ... innerTags) {
        this(null, new IndentList(innerTags));
    }

    public PluginTag(String groupId, String artifactId, String version, TagEntity ... innerTags) {
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
