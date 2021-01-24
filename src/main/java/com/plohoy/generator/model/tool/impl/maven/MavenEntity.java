package com.plohoy.generator.model.tool.impl.maven;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.impl.maven.tag.ProjectTag;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MavenEntity extends CodeEntity<MavenEntity> {
    private String header;
    private ProjectTag projectTag;

    @Override
    public String toString() {
        return header + projectTag;
    }

    @Override
    public MavenEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
