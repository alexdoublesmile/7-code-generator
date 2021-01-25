package com.plohoy.generator.model.tool.impl.liquibase.changelog;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeLogHeaderEntity extends CodeEntity<ChangeLogHeaderEntity> {
    private String value;

    @Override
    public String toString() {
        return value;
    }

    @Override
    public ChangeLogHeaderEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
