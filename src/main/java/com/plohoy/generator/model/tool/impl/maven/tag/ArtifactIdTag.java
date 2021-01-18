package com.plohoy.generator.model.tool.impl.maven.tag;

import lombok.Data;

@Data
public class ArtifactIdTag extends TagEntity {

    public ArtifactIdTag(String body) {
        super(
                "artifactId",
                null,
                body,
                false,
                null
        );
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
