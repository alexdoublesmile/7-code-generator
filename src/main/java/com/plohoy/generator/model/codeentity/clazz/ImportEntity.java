package com.plohoy.generator.model.codeentity.clazz;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class ImportEntity extends CodeEntity<ImportEntity> {
    private String value;

    @Override
    public ImportEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return getTab(getParentNestLvl()) + IMPORT + SPACE + value;
    }
}
