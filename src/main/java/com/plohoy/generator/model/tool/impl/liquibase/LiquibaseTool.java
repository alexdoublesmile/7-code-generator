package com.plohoy.generator.model.tool.impl.liquibase;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
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
import com.plohoy.generator.util.domainhelper.FieldHelper;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.model.file.FileType.ENTITY;
import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.CORE;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class LiquibaseTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.liquibase";
    private static final String DEFAULT_ARTIFACT_ID = "liquibase-core";
    private static final String SCOPE = "";

    private static final String LIQUIBASE_DIR = "db/changelog/";
    private static final String DEFAULT_HEADER_VALUE = "--liquibase formatted sql\n";

    private List<DBTableEntity> tables = new ArrayList<>();
    private List<ConstraintEntity> manyToManyConstraints = new ArrayList<>();

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
//        addMigrationDescription(field, currentSourceField.getRelation(), source);

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
                        .rollbackValues(new EnumerationList<String>(DelimiterType.COMMA, false,
                                getRollbackValues()))
                        .build())
                .build();
    }

    private String getChangeLogSettings(Source source) {
        return "--changeset alexplohoy:01 splitStatements:false failOnError:true dbms:postgresql\n";
    }

    private IndentList<DBTableEntity> getSqlCreateTable(Source source) {
        List<AbstractSourceFile> entityFiles = source.getSourceData().get(ENTITY);

        for (AbstractSourceFile<ClassEntity> entityFile : entityFiles) {
            ClassEntity entity = entityFile.getData();

            this.tables.add(
                    DBTableEntity.builder()
                            .tableName(StringUtil.toSnakeCase(entity.getName()))
                            .fields(getDBFieldsFromEntity(entity.getFields(), entity.getIdType()))
                            .primaryKey(getPrimaryKeyByName(entity, ID))
                            .build()
            );

            List<FieldEntity> manyToManyFields = entity.getFields().stream()
                    .filter(FieldHelper::hasManyToManyRelation)
                    .filter(FieldHelper::isRelationOwner)
                    .collect(Collectors.toList());

            for (FieldEntity manyToManyField : manyToManyFields) {
                String manyToManyOwnerName = StringUtil.toSnakeCase(manyToManyField.getName());
                String manyToManyInverseEndName = StringUtil.toSnakeCase(
                        FieldHelper.getMappedFieldFromFiles(manyToManyField, entity.getName(), entityFiles)
                                .getName());

                String manyToManyTableName = getManyToManyTableName(manyToManyOwnerName, manyToManyInverseEndName);

                if (!hasSameTable(manyToManyTableName)) {
                    tables.add(
                            DBTableEntity.builder()
                                .tableName(manyToManyTableName)
                                .fields(new IndentList<DBFieldEntity>(DelimiterType.COMMA, true, true,
                                        DBFieldEntity.builder()
                                                .fieldType(StringUtil.toSnakeCase(entity.getIdType()))
                                                .fieldName(getDBEntityKey(StringUtil.toSingle(manyToManyOwnerName)))
                                                .fieldProperties(setNullDBProperty())
                                                .build(),
                                        DBFieldEntity.builder()
                                                .fieldType(StringUtil.toSnakeCase(entity.getIdType()))
                                                .fieldName(getDBEntityKey(StringUtil.toSingle(manyToManyInverseEndName)))
                                                .fieldProperties(setNullDBProperty())
                                                .build()))
                                .primaryKey(getPrimaryKeysByName(
                                        getDBEntityKey(StringUtil.toSingle(manyToManyOwnerName)),
                                        getDBEntityKey(StringUtil.toSingle(manyToManyInverseEndName))))
                                .build());
                } else {
                    manyToManyTableName = getManyToManyTableName(manyToManyInverseEndName, manyToManyOwnerName);
                }

                manyToManyConstraints.add(
                        ConstraintEntity.builder()
                                .currentTableName(manyToManyTableName)
                                .referencedTableName(StringUtil.toSnakeCase(entity.getName()))
                                .foreignKey(getDBEntityKey(StringUtil.toSingle(manyToManyOwnerName)))
                                .build()
                );

                manyToManyConstraints.add(
                        ConstraintEntity.builder()
                                .currentTableName(manyToManyTableName)
                                .referencedTableName(StringUtil.toSnakeCase(manyToManyField.getType()))
                                .foreignKey(getDBEntityKey(StringUtil.toSingle(manyToManyInverseEndName)))
                                .build()
                );
            }
        }

        return new IndentList<DBTableEntity>(DelimiterType.SEMICOLON, true, true, tables);
    }

    private EnumerationList<String> getPrimaryKeysByName(String ... primaryKeyNames) {
        return new EnumerationList<String>(DelimiterType.COMMA, false, Arrays.asList(primaryKeyNames));
    }

    private EnumerationList<String> getPrimaryKeyByName(ClassEntity entity, String primaryKeyName) {
        FieldEntity primaryKeyField = entity.getFields()
                .stream()
                .filter(field ->
                        primaryKeyName.equals(field.getName())
                                && entity.getIdType().equals(field.getType()))
                .findFirst()
                .orElseGet(() -> FieldEntity.builder()
                        .type(entity.getIdType())
                        .name(primaryKeyName)
                        .build());

        return new EnumerationList<String>(false, primaryKeyField.getName());
    }

    private String getDBEntityKey(String entityName) {
        return StringUtil.toSnakeCase(entityName) + "_id";
    }

    private String getManyToManyTableName(String ownerName, String inverseName) {
        return StringUtil.toSnakeCase(ownerName)
                + "_"
                + StringUtil.toSnakeCase(inverseName);
    }

    private boolean hasSameTable(String manyToManyTableName) {
        return tables.stream()
                .anyMatch(table -> manyToManyTableName.equals(table.getTableName())
                        || checkInverseTableName(manyToManyTableName, table.getTableName()));
    }

    private boolean checkInverseTableName(String manyToManyTableName, String tableName) {
        return Stream.of(manyToManyTableName.split("_"))
                .allMatch(tableName::contains);
    }

    private IndentList<DBFieldEntity> getDBFieldsFromEntity(IndentList<FieldEntity> fields, String idType) {
        List<DBFieldEntity> dbFields = new ArrayList<>();

        for (FieldEntity fieldEntity : fields) {
            if (!FieldHelper.isOneToBackReference(fieldEntity)
                    && !FieldHelper.hasManyToManyRelation(fieldEntity)) {
                dbFields.add(mapFieldToDB(fieldEntity, idType));
            }
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
        return ID.equals(fieldEntity.getName()) ? new EnumerationList<String>(false, "NOT NULL") : null;
    }

    private EnumerationList<String> setNullDBProperty() {
        return new EnumerationList<String>(false, "NOT NULL");
    }

    private String mapFieldTypeToDB(String type) {
        switch (type) {
            case "UUID": return "UUID";
            case "byte":
            case "Byte": return "BYTE";
            case "short":
            case "Short": return "SMALLINT";
            case "int":
            case "Integer": return "INT";
            case "long":
            case "Long": return "BIGINT";
            case "float":
            case "Float": return "REAL";
            case "double":
            case "Double": return "DOUBLE PRECISION";
            case "BigInteger": return "BIGINT";
            case "boolean":
            case "Boolean": return "BOOLEAN";
            case "char":
            case "Character": return "CHAR(1)";
            case "String": return "TEXT";
            case "LocalDate": return "DATE";
            case "LocalDateTime": return "TIMESTAMP";
            case "LocalTime": return "TIME";
            default: return null;
        }
    }

    private IndentList<ConstraintEntity> getSqlAddConstraint(Source source) {
        List<ConstraintEntity> constraintList = new ArrayList<>();

        for (ClassEntity entity : source.getEntities()) {
            List<FieldEntity> needConstraintFields = entity.getFields().stream()
                    .filter(FieldHelper::hasOneToRelation)
                    .filter(FieldHelper::isRelationOwner)
                    .collect(Collectors.toList());

            for (FieldEntity field : needConstraintFields) {
                constraintList.add(
                        ConstraintEntity.builder()
                                .currentTableName(entity.getName().toLowerCase())
                                .referencedTableName(StringUtil.toSnakeCase(field.getType()))
                                .foreignKey(getDBEntityKey(field.getName()))
                                .build()
                );
            }
        }

        constraintList.addAll(manyToManyConstraints);

        return new IndentList<ConstraintEntity>(DelimiterType.SEMICOLON, true, true, constraintList);
    }

    private List<String> getRollbackValues() {
        List<String> tableNames = tables.stream()
                .map(table -> table.getTableName())
                .collect(Collectors.toList());

        Collections.reverse(tableNames);
        return tableNames;
    }
}
