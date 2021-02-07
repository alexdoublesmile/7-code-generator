package com.plohoy.generator.model.tool.impl.jackson;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ImportEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.domainhelper.DomainHelper;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.List;
import java.util.Objects;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.API;

public class JacksonTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "com.fasterxml.jackson.core";
    private static final String DEFAULT_ARTIFACT_ID = "jackson-annotations";
    private static final String SCOPE = "";

    public JacksonTool(String version) {
        super(
                version,
                DEFAULT_GROUP_ID,
                DEFAULT_ARTIFACT_ID,
                SCOPE,
                true,
                API
        );
    }

    public JacksonTool() {
    }

    public Source generateCode(Source source) {
        List<AbstractSourceFile> dtoFiles = source.getSourceData().get(FileType.DTO);

        for (AbstractSourceFile<ClassEntity> dtoFile : dtoFiles) {
            ClassEntity dto = dtoFile.getData();

            for (FieldEntity field : dto.getFields()) {
                if (Objects.isNull(field.getAnnotations())) {
                    field.setAnnotations(new IndentList<AnnotationEntity>());
                }

                if ("deleted".equals(field.getName())
                        || DomainHelper.isOneToOneBackReference(field)
                        || DomainHelper.hasManyToOneRelation(field)) {
                    field.getAnnotations().add(
                            AnnotationEntity.builder()
                                    .name("JsonIgnore")
                                    .build()
                                    .setParentEntity(field)
                    );
                } else {
                    field.getAnnotations().add(
                            AnnotationEntity.builder()
                                    .name("JsonProperty")
                                    .property(PropertyEntity.builder().quotedValue(field.getName()).build())
                                    .build()
                                    .setParentEntity(field)
                    );
                }
            }

            dto.getImports().add(ImportEntity.builder().value("com.fasterxml.jackson.annotation.JsonIgnore").build().setParentEntity(dto));
            dto.getImports().add(ImportEntity.builder().value("com.fasterxml.jackson.annotation.JsonProperty").build().setParentEntity(dto));
        }
        return source;
    }
}
