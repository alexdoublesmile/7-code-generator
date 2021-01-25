package com.plohoy.generator.model.tool.impl.liquibase.changelog;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeLogSettingsEntity extends CodeEntity<ChangeLogSettingsEntity> {
    private final String DEFAULT_VALUE = "liquibase formatted sql";
    private String value;

    @Override
    public String toString() {
        return value;
    }

    @Override
    public ChangeLogSettingsEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
