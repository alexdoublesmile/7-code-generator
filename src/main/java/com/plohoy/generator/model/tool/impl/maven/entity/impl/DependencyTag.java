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
public class DependencyTag extends TagEntity {

    public DependencyTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public DependencyTag(IndentList<TagEntity> innerTags) {
        this("dependency", null, false, innerTags);
    }

    public DependencyTag(TagEntity ... innerTags) {
        this(new IndentList(innerTags));
    }

    public DependencyTag(String groupId, String artifactId, String version, String scope, TagEntity ... innerTags) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId),
                new VersionTag(version),
                new ScopeTag(scope));

        setInnerTags(new IndentList(
                Stream.of(getInnerTags(), Arrays.asList(innerTags))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())));
    }

    public DependencyTag(String groupId, String artifactId, String version, String scope) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId),
                new VersionTag(version),
                new ScopeTag(scope));
    }

    public DependencyTag(String groupId, String artifactId, String version) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId),
                new VersionTag(version));
    }

    public DependencyTag(String groupId, String artifactId) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
