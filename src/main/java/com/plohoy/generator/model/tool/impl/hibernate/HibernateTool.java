package com.plohoy.generator.model.tool.impl.hibernate;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.annotation.SimpleValueList;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.field.FieldRelation;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.domainhelper.DomainHelper;
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
                return getAnnotationsForManyToOne(field);
            case MANY_TO_MANY:
                return getAnnotationsForManyToMany(field, entity, entityFiles);
            default:
                return emptyList();
        }
    }

    private List<AnnotationEntity> getAnnotationsForOneToOne(
            FieldEntity field,
            ClassEntity entity,
            List<AbstractSourceFile> entityFiles,
            boolean relationOwner
    ) {
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
                                            .quotedValue(
                                                    DomainHelper
                                                        .getMappedFieldFromFiles(field, entity.getName(), entityFiles)
                                                        .getName())
                                            .build()))
                            .build());
        }

        return annotations;
    }

    private List<AnnotationEntity> getAnnotationsForOneToMany(FieldEntity field, ClassEntity entity, List<AbstractSourceFile> entityFiles) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        annotations.add(
                AnnotationEntity.builder()
                        .name(StringUtil.toCamelCase(ONE_TO_MANY.name()))
                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                PropertyEntity.builder()
                                        .name("mappedBy")
                                        .quotedValue(
                                                DomainHelper
                                                        .getMappedFieldFromFiles(field, entity.getName(), entityFiles)
                                                        .getName())
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

    private List<AnnotationEntity> getAnnotationsForManyToOne(FieldEntity field) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        annotations.add(
                AnnotationEntity.builder()
                        .name(StringUtil.toCamelCase(MANY_TO_ONE.name()))
                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
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
                                        .build()))
                        .build());

        return annotations;
    }

    private List<AnnotationEntity> getAnnotationsForManyToMany(FieldEntity field, ClassEntity entity, List<AbstractSourceFile> entityFiles) {
        FieldEntity mappedField = DomainHelper
                .getMappedFieldFromFiles(field, entity.getName(), entityFiles);

        boolean isRelationOwner = field.getRelation().isRelationOwner();

        return isRelationOwner ? setManyToManyOwnerAnnotations(entity, field, mappedField)
                : setManyToManyInverseAnnotations(entity, field, mappedField);
    }

    private List<AnnotationEntity> setManyToManyInverseAnnotations(ClassEntity entity, FieldEntity field, FieldEntity mappedField) {
        List<AnnotationEntity> annotations = new ArrayList<>();
        annotations.add(
                AnnotationEntity.builder()
                        .name(StringUtil.toCamelCase(MANY_TO_MANY.name()))
                        .property(PropertyEntity.builder()
                                .name("mappedBy")
                                .quotedValue(mappedField.getName())
                                .build())
                        .build());

        return annotations;
    }

    private List<AnnotationEntity> setManyToManyOwnerAnnotations(ClassEntity entity, FieldEntity field, FieldEntity mappedField) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        annotations.add(
                AnnotationEntity.builder()
                        .name(StringUtil.toCamelCase(MANY_TO_MANY.name()))
                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                PropertyEntity.builder()
                                        .name("fetch")
                                        .simpleValue("FetchType.LAZY")
                                        .build(),
                                PropertyEntity.builder()
                                        .name("cascade")
                                        .simpleValueList(
                                                SimpleValueList.builder()
                                                        .values(new IndentList<String>(DelimiterType.COMMA, false,
                                                                "CascadeType.PERSIST",
                                                                "CascadeType.REFRESH"))
                                                        .build())
                                        .build()))
                        .build());

        annotations.add(
                AnnotationEntity.builder()
                        .name("JoinTable")
                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                PropertyEntity.builder()
                                        .name("name")
                                        .quotedValue(getManyToManyTable(field, mappedField))
                                        .build(),
                                PropertyEntity.builder()
                                        .name("joinColumns")
                                        .annotationList(new IndentList<AnnotationEntity>(false,
                                                AnnotationEntity.builder()
                                                        .name("JoinColumn")
                                                        .property(
                                                                PropertyEntity.builder()
                                                                        .name("name")
                                                                        .quotedValue(StringUtil.toSnakeCase(StringUtil.toSingle(field.getName())) + "_id")
                                                                        .build())
                                                        .build()
                                        ))
                                        .build(),
                                PropertyEntity.builder()
                                        .name("inverseJoinColumns")
                                        .annotationList(new IndentList<AnnotationEntity>(false,
                                                AnnotationEntity.builder()
                                                        .name("JoinColumn")
                                                        .property(
                                                                PropertyEntity.builder()
                                                                        .name("name")
                                                                        .quotedValue(StringUtil.toSnakeCase(StringUtil.toSingle(mappedField.getName())) + "_id")
                                                                        .build())
                                                        .build()))
                                        .build()))
                        .build());
        return annotations;
    }

    private String getManyToManyTable(FieldEntity field, FieldEntity mappedField) {
        PropertyEntity nameProperty = null;
        EnumerationList<PropertyEntity> joinTableAnnotationProperties = null;

        if (Objects.nonNull(mappedField.getAnnotations())) {
            joinTableAnnotationProperties = mappedField.getAnnotations()
                    .stream()
                    .filter(annotation -> "JoinTable".equals(annotation.getName()))
                    .findFirst()
                    .orElseGet(() -> AnnotationEntity.builder().build())
                    .getProperties();
        }

        if (Objects.nonNull(joinTableAnnotationProperties)) {
            nameProperty = joinTableAnnotationProperties.stream()
                    .filter(property -> "name".equals(property.getName()))
                    .findFirst()
                    .get();
        }

        if (Objects.nonNull(nameProperty)) {
            return nameProperty.getQuotedValue();
        } else {
            return String.format(
                    "%s_%s",
                    StringUtil.toSnakeCase(field.getName()),
                    StringUtil.toSnakeCase(mappedField.getName())
            );
        }
    }
}
