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
public class ParentTag extends TagEntity {
    public ParentTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public ParentTag(IndentList<CodeEntity> innerTags) {
        this("parent", null, null, false, innerTags);
    }

    public ParentTag(CodeEntity ... innerTags) {
        this(new IndentList(innerTags));
    }

    public ParentTag(String groupId, String artifactId, String version, CodeEntity... innerTags) {
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
