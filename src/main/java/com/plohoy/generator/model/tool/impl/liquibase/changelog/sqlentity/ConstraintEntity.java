package com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class ConstraintEntity extends CodeEntity<ConstraintEntity> {
    private static final String ALTER_TABLE_WORD = "alter table";
    private static final String ADD_CONSTRAINT_WORD = "add constraint";
    private static final String FOREIGN_KEY_WORD = "foreign key";
    private static final String REFERENCES_WORD = "references";
    private static final String FOREIGN_KEY_PREFIX = "fk_";
    private String currentTableName;
    private String referencedTableName;
    private String foreignKey;

    @Override
    public String toString() {
        return ALTER_TABLE_WORD + SPACE + currentTableName + INDENT
                + getTab() + ADD_CONSTRAINT_WORD + SPACE + FOREIGN_KEY_PREFIX + foreignKey + INDENT
                + getTab(2) + FOREIGN_KEY_WORD + SPACE + OPEN_PARAM_BRACKET + foreignKey + CLOSE_PARAM_BRACKET
                + SPACE +  REFERENCES_WORD + SPACE + referencedTableName;
    }

    @Override
    public ConstraintEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
