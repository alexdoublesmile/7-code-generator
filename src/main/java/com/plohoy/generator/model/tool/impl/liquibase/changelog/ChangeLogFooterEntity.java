package com.plohoy.generator.model.tool.impl.liquibase.changelog;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class ChangeLogFooterEntity extends CodeEntity<ChangeLogFooterEntity> {
    private final String ROLLBACK_WORD = "rollback drop table";
    private EnumerationList<String> rollbackValues;

    public ChangeLogFooterEntity(EnumerationList<String> rollbackValues) {
        this.rollbackValues = rollbackValues;
    }

    @Override
    public String toString() {
        return MINUS + MINUS + ROLLBACK_WORD + SPACE + rollbackValues.toString() + SEMICOLON;
    }

    @Override
    public ChangeLogFooterEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
