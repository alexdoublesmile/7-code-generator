package com.plohoy.generator.model.tool.impl.liquibase.changelog;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.stream.Collectors;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class ChangeLogFooterEntity extends CodeEntity<ChangeLogFooterEntity> {
    private final String ROLLBACK_WORD = "rollback";
    private IndentList<String> rollbackValues;

    public ChangeLogFooterEntity(IndentList<String> rollbackValues) {
        this.rollbackValues = new IndentList<String>(DelimiterType.SEMICOLON, true, true,
                rollbackValues.stream()
                        .map(rollback -> MINUS + MINUS + ROLLBACK_WORD + SPACE + rollback)
                        .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return rollbackValues.toString();
    }

    @Override
    public ChangeLogFooterEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
