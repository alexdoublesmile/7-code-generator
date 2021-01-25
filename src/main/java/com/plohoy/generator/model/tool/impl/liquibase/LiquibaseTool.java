package com.plohoy.generator.model.tool.impl.liquibase;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.SimpleSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogFooterEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogHeaderEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogSettingsEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.ChangeLogSqlEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity.ConstraintEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity.DBFieldEntity;
import com.plohoy.generator.model.tool.impl.liquibase.changelog.sqlentity.DBTableEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.CORE;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.ID;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SLASH;

public class LiquibaseTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.liquibase";
    private static final String DEFAULT_ARTIFACT_ID = "liquibase-core";
    private static final String SCOPE = "";

    private static final String LIQUIBASE_DIR = "db/changelog/";
    private static final String DEFAULT_HEADER_VALUE = "--liquibase formatted sql\n";

    private List<DBTableEntity> tables = new ArrayList<>();

    public LiquibaseTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, CORE);
    }

    public LiquibaseTool() {
    }

    public Source generateCode(Source source) {
        source.getSourceData()
                .get(FileType.EXTERNAL_FILE)
                .add(SimpleSourceFile.builder()
                        .path(source.getPath() + source.getArtifactName() + source.getResourcePath() + SLASH + LIQUIBASE_DIR)
                        .fileName("db.changelog-master.yml")
                        .data(getMasterCode())
                        .build());

        source.getSourceData()
                .get(FileType.EXTERNAL_FILE)
                .add(SimpleSourceFile.builder()
                        .path(source.getPath() + source.getArtifactName() + source.getResourcePath() + SLASH + LIQUIBASE_DIR)
                        .fileName("db.changelog-0.0.1.sql")
                        .data(getSQLCode(source))
                        .build());
        return source;
    }

    private CodeEntity getMasterCode() {
        return LiquibaseMasterEntity.builder()
                .value("databaseChangeLog:\n" +
                        "  - include:\n" +
                        "      file: db.changelog-0.0.1.sql\n" +
                        "      relativeToChangelogFile: true")
                .build();
    }

    private CodeEntity getSQLCode(Source source) {
        return LiquibaseSqlEntity.builder()
                .header(ChangeLogHeaderEntity.builder().value(DEFAULT_HEADER_VALUE).build())
                .settings(ChangeLogSettingsEntity.builder()
                        .value(getChangeLogSettings(source))
                        .build())
                .sqlCode(ChangeLogSqlEntity.builder()
                        .tables(getSqlCreateTable(source))
                        .constraints(getSqlAddConstraint(source))
                        .build())
                .footer(ChangeLogFooterEntity.builder()
                        .rollbackValues(new IndentList<String>(DelimiterType.SEMICOLON, true, true, getRollbackValues()))
                        .build())
                .build();
    }

    private String getChangeLogSettings(Source source) {
        return "--changeset alexplohoy:01 splitStatements:false failOnError:true dbms:postgresql\n";
    }

    private IndentList<DBTableEntity> getSqlCreateTable(Source source) {
        List<ClassEntity> entities = Stream
                .of(source.getMainEntities(), source.getSecondaryEntities())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (ClassEntity entity : entities) {
            this.tables.add(
                    DBTableEntity.builder()
                            .tableName(StringUtil.toSnakeCase(entity.getName()))
                            .fields(getDBFieldsFromEntity(entity.getFields(), entity.getIdType()))
                            .primaryKey(mapFieldToDB(entity.getFields()
                                    .stream()
                                    .filter(field ->
                                            ID.equals(field.getName())
                                                    && entity.getIdType().equals(field.getType()))
                                    .findFirst()
                                    .orElseGet(() -> {
                                        return FieldEntity.builder()
                                                .type(entity.getIdType())
                                                .name(ID)
                                                .build();
                                    }), null))
                            .build()
            );
        }

        return new IndentList<DBTableEntity>(DelimiterType.SEMICOLON, true, true, tables);
    }

    private IndentList<DBFieldEntity> getDBFieldsFromEntity(IndentList<FieldEntity> fields, String idType) {
        List<DBFieldEntity> dbFields = new ArrayList<>();

        for (FieldEntity fieldEntity : fields) {
            dbFields.add(mapFieldToDB(fieldEntity, idType));
        }
        return new IndentList<DBFieldEntity>(DelimiterType.COMMA, true, true, dbFields);
    }

    private DBFieldEntity mapFieldToDB(FieldEntity fieldEntity, String idType) {
        String fieldType = mapFieldTypeToDB(fieldEntity.getType());
        String fieldName = StringUtil.toSnakeCase(fieldEntity.getName());

        if (Objects.isNull(fieldType)) {
            fieldType = mapFieldTypeToDB(idType);
            fieldName += "_id";
        }

        return DBFieldEntity.builder()
                .fieldType(fieldType)
                .fieldName(fieldName)
                .fieldProperties(setDBProperties(fieldEntity))
                .build();
    }

    private EnumerationList<String> setDBProperties(FieldEntity fieldEntity) {
        return null;
    }

    private String mapFieldTypeToDB(String type) {
        switch (type) {
            case "UUID": return "uuid";
            case "byte":
            case "Byte": return "byte";
            case "short":
            case "Short": return "int2";
            case "int":
            case "Integer": return "int4";
            case "long":
            case "Long": return "int8";
            case "float":
            case "Float": return "float4";
            case "double":
            case "Double": return "float8";
            case "BigInteger": return "bigint";
            case "boolean":
            case "Boolean": return "boolean";
            case "char":
            case "Character": return "varchar(5)";
            case "String": return "varchar(255)";
            case "LocalDate": return "date";
            case "LocalDateTime": return "timestamp";
            default: return null;
        }
    }

    private IndentList<ConstraintEntity> getSqlAddConstraint(Source source) {
        List<ConstraintEntity> constraintList = new ArrayList<>();

        constraintList.add(
                ConstraintEntity.builder()
                        .currentTableName("test_table")
                        .referencedTableName("ref_test_table")
                        .foreignKey("some_id")
                        .build()
        );

        return new IndentList<ConstraintEntity>(DelimiterType.SEMICOLON, true, constraintList);
    }

    private List<String> getRollbackValues() {
        List<String> tableNames = tables.stream()
                .map(table -> table.getTableName())
                .collect(Collectors.toList());

        Collections.reverse(tableNames);
        return tableNames;
    }
}
