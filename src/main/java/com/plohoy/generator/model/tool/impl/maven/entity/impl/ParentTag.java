package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class ParentTag extends TagEntity {

    public ParentTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public ParentTag(IndentList<TagEntity> innerTags) {
        this("parent", null, false, innerTags);
    }

    public ParentTag(TagEntity ... innerTags) {
        this(new IndentList(innerTags));
    }

    public ParentTag(String groupId, String artifactId, String version, TagEntity ... innerTags) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId),
                new VersionTag(version));

        setInnerTags(new IndentList(
                Stream.of(getInnerTags(), Arrays.asList(innerTags))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())));
    }

    public ParentTag(String groupId, String artifactId, String version) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId),
                new VersionTag(version));
    }

    public ParentTag(String groupId, String artifactId) {
        this(
                new GroupIdTag(groupId),
                new ArtifactIdTag(artifactId));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
