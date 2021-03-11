package com.plohoy.generator.model.tool.impl.readme;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadMeEntity extends CodeEntity<ReadMeEntity> {
    private String value;

    @Override
    public String toString() {
        return value;
    }

    @Override
    public ReadMeEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
