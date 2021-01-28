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
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.model.file.FileType.ENTITY;

public class HibernateTool extends AbstractTool {

//    private static final String DEFAULT_GROUP_ID = "org.hibernate.validator ";
//    private static final String DEFAULT_ARTIFACT_ID = "hibernate";
//    private static final String SCOPE = "runtime";
    public HibernateTool(String version) {
        super(version);
    }

    public HibernateTool() {
    }

    public Source generateCode(Source source) {
        List<ClassEntity> sourceEntities = source.getEntities();

        List<AbstractSourceFile> entityFiles = source.getSourceData().get(ENTITY);

        for (AbstractSourceFile<ClassEntity> entityFile : entityFiles) {
            ClassEntity entity = entityFile.getData();

            ClassEntity currentSourceEntity = sourceEntities.stream()
                    .filter(sourceEntity -> sourceEntity.getName().equals(entity.getName()))
                    .findFirst().get();

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

                FieldEntity currentSourceField = currentSourceEntity.getFields().stream()
                        .filter(sourceField -> sourceField.getName().equals(field.getName()))
                        .findFirst()
                        .orElseGet(() -> FieldEntity.builder().build());

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

                } else if (Objects.isNull(currentSourceField.getRelation())) {
                    field.getAnnotations().add(
                            AnnotationEntity.builder()
                                    .name("Column")
                                    .build()
                                    .setParentEntity(field)
                    );
                } else {
                    getAnnotationsByRelations(field, currentSourceField.getRelation(), entity.getName())
                            .stream()
                            .forEach(annotation ->
                                    field.getAnnotations()
                                            .add(annotation.setParentEntity(field)));
                }
            }
        }
        return source;
    }

    private List<AnnotationEntity> getAnnotationsByRelations(FieldEntity field, FieldRelation relation, String entityName) {
        List<AnnotationEntity> annotations = new ArrayList<>();

        if (relation.isRelationOwner()) {
            annotations.add(
                    AnnotationEntity.builder()
                        .name(StringUtil.toCamelCase(relation.getRelationType().name()))
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
            annotations.add(getJoinByRelation(field, relation));

        } else {
            annotations.add(
                    AnnotationEntity.builder()
                            .name(StringUtil.toCamelCase(relation.getRelationType().name()))
                            .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                    PropertyEntity.builder()
                                        .name("mappedBy")
                                        .quotedValue(StringUtil.toSnakeCase(entityName))
                                        .build()))
                            .build());
        }

        return annotations;
    }

    private AnnotationEntity getJoinByRelation(FieldEntity field, FieldRelation relation) {
        switch (relation.getRelationType()) {
            case ONE_TO_ONE: return AnnotationEntity.builder()
                    .name("JoinColumn")
                    .property(PropertyEntity.builder()
                            .name("name")
                            .quotedValue(StringUtil.toSnakeCase(field.getName()) + "_id")
                            .build())
                    .build();
            case ONE_TO_MANY: return null;
            case MANY_TO_ONE: return null;
            case MANY_TO_MANY: return null;
            default: return null;
        }
    }
}
