package com.plohoy.generator.model.tool.impl.liquibase;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogFooterEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogHeaderEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogSettingsEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogSqlEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiquibaseSqlEntity extends CodeEntity<LiquibaseSqlEntity> {
    private ChangeLogHeaderEntity header;
    private ChangeLogSettingsEntity settings;
    private ChangeLogSqlEntity sqlCode;
    private ChangeLogFooterEntity footer;

    @Override
    public String toString() {
        return header.toString() + settings + sqlCode + footer;
    }

    @Override
    public LiquibaseSqlEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
