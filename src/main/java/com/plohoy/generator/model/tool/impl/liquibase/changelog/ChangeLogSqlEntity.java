package com.plohoy.generator.model.tool.impl.liquibase.changelog;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity.ConstraintEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity.DBTableEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.NULL;

@Data
@Builder
public class ChangeLogSqlEntity extends CodeEntity<ChangeLogSqlEntity> {

    private IndentList<DBTableEntity> tables;
    private IndentList<ConstraintEntity> constraints;

    @Override
    public String toString() {
        return (tables.toString() + constraints).replace(NULL, EMPTY);
    }

    @Override
    public ChangeLogSqlEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
