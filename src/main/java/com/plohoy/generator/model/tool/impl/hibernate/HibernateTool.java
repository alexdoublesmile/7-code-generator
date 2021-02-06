package com.plohoy.generator.model.tool.impl.hibernate;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.field.FieldRelation;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.*;
import java.util.stream.Stream;

import static com.plohoy.generator.model.codeentity.field.RelationType.*;
import static com.plohoy.generator.model.file.FileType.ENTITY;
import static java.util.Collections.emptyList;

public class HibernateTool extends AbstractTool {

//    private static final String DEFAULT_GROUP_ID = "org.hibernate.validator ";
//    private static final String DEFAULT_ARTIFACT_ID = "hibernate";
//    private static final String SCOPE = "runtime";

    private Source source;

    public HibernateTool(String version) {
        super(version);
    }

    public HibernateTool() {
    }

    public Source generateCode(Source source) {
        this.source = source;
        List<AbstractSourceFile> entityFiles = source.getSourceData().get(ENTITY);

        for (AbstractSourceFile<ClassEntity> entityFile : entityFiles) {

            ClassEntity entity = entityFile.getData();

            if (Objects.isNull(entity.getAnnotations())) {
                entity.setAnnotations(new IndentList<AnnotationEntity>());
            }
            entity.getAnnotations().add(
                    AnnotationEntity.builder()
                            .name("Entity")
                            .build()
                            .setParentEntity(entity)
            );

            for (FieldEntity field : entity.getFields()) {

                if (Objects.isNull(field.getAnnotations())) {
                    field.setAnnotations(new IndentList<AnnotationEntity>());
                }
                if (entity.getIdType().equals(field.getType())) {
                    Stream.of(
                            AnnotationEntity.builder()
                                    .name("Id")
                                    .build(),
                            AnnotationEntity.builder()
                                    .name("GeneratedValue")
                                    .build()
                    ).forEach(annotation ->
                            field.getAnnotations().add(
                                annotation.setParentEntity(field)));

                } else if (Objects.isNull(field.getRelation())) {
                    field.getAnnotations().add(
                            AnnotationEntity.builder()
                                    .name("Column")
                                    .build()
                                    .setParentEntity(field)
                    );

                } else {
                    getAnnotationsByRelations(field, entity, entityFiles)
                            .stream()
                            .forEach(annotation ->
                                    field.getAnnotations()
                                            .add(annotation.setParentEntity(field)));
                }
            }
        }
        return source;
    }

    private List<AnnotationEntity> getAnnotationsByRelations(FieldEntity field, ClassEntity entity, List<AbstractSourceFile> entityFiles) {
        FieldRelation relation = field.getRelation();

        switch(relation.getRelationType()) {
            case ONE_TO_ONE:
                return getAnnotationsForOneToOne(field, entity, entityFiles, relation.isRelationOwner());
            case ONE_TO_MANY:
                return getAnnotationsForOneToMany(field, entity, entityFiles);
            case MANY_TO_ONE:
                return getAnnotationsForManyToOne(field, source);
            case MANY_TO_MANY:
                return getAnnotationsForManyToMany(field, source);
            default:
                return emptyList();
        }
    }

    private List<AnnotationEntity> getAnnotationsForOneToOne(FieldEntity field, ClassEntity entity, List<AbstractSourceFile> entityFiles, boolean relationOwner) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        if (relationOwner) {
            annotations.add(
                    AnnotationEntity.builder()
                            .name(StringUtil.toCamelCase(ONE_TO_ONE.name()))
                            .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                    PropertyEntity.builder()
                                            .name("cascade")
                                            .simpleValue("CascadeType.ALL")
                                            .build(),
                                    PropertyEntity.builder()
                                            .name("fetch")
                                            .simpleValue("FetchType.EAGER")
                                            .build()))
                            .build());
            annotations.add(
                    AnnotationEntity.builder()
                            .name("JoinColumn")
                            .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                    PropertyEntity.builder()
                                            .name("name")
                                            .quotedValue(StringUtil.toSnakeCase(field.getName()) + "_id")
                                            .build(),
                                    PropertyEntity.builder()
                                            .name("referencedColumnName")
                                            .quotedValue("id")
                                            .build()))
                            .build());
        } else {
            annotations.add(
                    AnnotationEntity.builder()
                            .name(StringUtil.toCamelCase(ONE_TO_ONE.name()))
                            .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                    PropertyEntity.builder()
                                            .name("mappedBy")
                                            .quotedValue(getMappedByOwner(field.getType(), entity.getName(), entityFiles))
                                            .build()))
                            .build());
        }

        return annotations;
    }

    private String getMappedByOwner(String ownerClassName, String fieldType, List<AbstractSourceFile> entityFiles) {
        String mappedFieldName = "";

        for (AbstractSourceFile<ClassEntity> file : entityFiles) {
            ClassEntity entity = file.getData();

            if (ownerClassName.equals(entity.getName())) {
                mappedFieldName = entity.getFields()
                        .stream()
                        .filter(field -> fieldType.equals(field.getType()))
                        .findFirst()
                        .orElseGet(() -> getAnyMatchFieldType(fieldType, entity.getFields()))
                        .getName();
            }
        }
        return mappedFieldName;
    }

    private FieldEntity getAnyMatchFieldType(String fieldType, IndentList<FieldEntity> fields) {
        return fields.stream()
                .filter(field -> fieldType.contains(field.getType()))
                .findFirst()
                .orElseGet(() -> FieldEntity.builder().name("unknown mapping").build());
    }

    private List<AnnotationEntity> getAnnotationsForOneToMany(FieldEntity field, ClassEntity entity, List<AbstractSourceFile> entityFiles) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        annotations.add(
                AnnotationEntity.builder()
                        .name(StringUtil.toCamelCase(ONE_TO_MANY.name()))
                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                PropertyEntity.builder()
                                        .name("mappedBy")
                                        .quotedValue(getMappedByOwner(field.getType(), entity.getName(), entityFiles))
                                        .build(),
                                PropertyEntity.builder()
                                        .name("fetch")
                                        .simpleValue("FetchType.LAZY")
                                        .build(),
                                PropertyEntity.builder()
                                        .name("cascade")
                                        .simpleValue("CascadeType.ALL")
                                        .build(),
                                PropertyEntity.builder()
                                        .name("orphanRemoval")
                                        .simpleValue("true")
                                        .build()
                        ))
                        .build()
        );

        return annotations;
    }

    private List<AnnotationEntity> getAnnotationsForManyToOne(FieldEntity field, Source source) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        annotations.add(
                AnnotationEntity.builder()
                        .name(StringUtil.toCamelCase(MANY_TO_ONE.name()))
                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                PropertyEntity.builder()
                                        .name("fetch")
                                        .simpleValue("FetchType.EAGER")
                                        .build(),
                                PropertyEntity.builder()
                                        .name("cascade")
                                        .simpleValueList(new IndentList<String>(DelimiterType.COMMA, false,
                                                "CascadeType.MERGE",
                                                "CascadeType.DETACH",
                                                "CascadeType.PERSIST",
                                                "CascadeType.REFRESH"))
                                        .build()))
                        .build());

        annotations.add(
                AnnotationEntity.builder()
                        .name("JoinColumn")
                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                PropertyEntity.builder()
                                        .name("name")
                                        .quotedValue(StringUtil.toSnakeCase(field.getName()) + "_id")
                                        .build()))
                        .build());

        return annotations;
    }

    private List<AnnotationEntity> getAnnotationsForManyToMany(FieldEntity field, Source source) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        return annotations;
    }
}
