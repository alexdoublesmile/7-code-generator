package com.plohoy.generator.model.tool.impl.git;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GitIgnoreEntity extends CodeEntity<GitIgnoreEntity> {
    private String value;

    @Override
    public String toString() {
        return value;
    }

    @Override
    public GitIgnoreEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
