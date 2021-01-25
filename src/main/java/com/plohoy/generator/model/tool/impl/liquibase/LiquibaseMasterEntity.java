package com.plohoy.generator.model.tool.impl.liquibase;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiquibaseMasterEntity extends CodeEntity<LiquibaseMasterEntity> {
    private String value;

    @Override
    public String toString() {
        return value;
    }

    @Override
    public LiquibaseMasterEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
