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
public class DependencyTag extends TagEntity {
    public DependencyTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public DependencyTag(IndentList<CodeEntity> innerTags) {
        this("dependency", null, null, false, innerTags);
    }

    public DependencyTag(TagEntity ... innerTags) {
        this(new IndentList(innerTags));
    }

    public DependencyTag(String groupId, String artifactId, String version, String scope, CodeEntity ... innerTags) {
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
