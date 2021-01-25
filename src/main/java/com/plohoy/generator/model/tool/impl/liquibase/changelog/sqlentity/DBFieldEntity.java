package com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SPACE;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.getTab;

@Data
@Builder
public class DBFieldEntity extends CodeEntity<DBFieldEntity> {
    private String fieldName;
    private String fieldType;
    private EnumerationList<String> fieldProperties;

    @Override
    public DBFieldEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return getTab(getNestLvl()) + fieldName + SPACE + fieldType + SPACE + fieldProperties;
    }
}
