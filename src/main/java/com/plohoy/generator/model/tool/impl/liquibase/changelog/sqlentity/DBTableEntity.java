package com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class DBTableEntity extends CodeEntity<DBTableEntity> {
    private static final String CREATE_TABLE_WORD = "create table";
    private static final String PRIMARY_KEY_WORD = "primary key";

    private String tableName;
    private IndentList<DBFieldEntity> fields;
    private EnumerationList<String> primaryKey;

    public DBTableEntity(String tableName, IndentList<DBFieldEntity> fields, EnumerationList<String> primaryKey) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;

        setFields(fields);
    }

    @Override
    public DBTableEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    public void setFields(IndentList<DBFieldEntity> fields) {
        while(fields.remove(null));

        this.fields = fields;
        this.fields.stream().forEach(field -> field.setParentEntity(this));
    }

    @Override
    public String toString() {
        return CREATE_TABLE_WORD + SPACE + tableName + SPACE + OPEN_PARAM_BRACKET + INDENT
                + fields
                + getTab(getNestLvl() + 1) + PRIMARY_KEY_WORD + SPACE
                + OPEN_PARAM_BRACKET + primaryKey + CLOSE_PARAM_BRACKET + INDENT
                + CLOSE_PARAM_BRACKET;
    }

}
