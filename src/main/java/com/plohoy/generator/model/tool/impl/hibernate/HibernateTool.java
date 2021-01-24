package com.plohoy.generator.model.tool.impl.hibernate;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.file.EntityFile;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.SimpleSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

                } else {
                    field.getAnnotations().add(
                            AnnotationEntity.builder()
                                    .name("Column")
                                    .build()
                                    .setParentEntity(field)
                    );
                }
            }
        }
        return source;
    }
}
