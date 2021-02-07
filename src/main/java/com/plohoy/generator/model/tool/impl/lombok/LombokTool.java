package com.plohoy.generator.model.tool.impl.lombok;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ImportEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.method.MethodEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.domainhelper.DomainHelper;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static com.plohoy.generator.model.file.FileType.DTO;
import static com.plohoy.generator.model.file.FileType.ENTITY;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EQUALS;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.HASHCODE;

@Getter
public class LombokTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.projectlombok";
    private static final String DEFAULT_ARTIFACT_ID = "lombok";
    private static final String SCOPE = "";

    private static final String TO_STRING_EXCLUDE = "ToString.Exclude";
    private static final String EQUALS_EXCLUDE = "EqualsAndHashCode.Exclude";

    public LombokTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true);
    }

    public LombokTool() {
    }

    public Source generateCode(Source source) {
        List<AbstractSourceFile> entityFiles = source.getSourceData().get(ENTITY);
        List<AbstractSourceFile> dtoFiles = source.getSourceData().get(DTO);

        addToFilesExcludeAnnotations(entityFiles);
        addToFilesExcludeAnnotations(dtoFiles);

        return source;
    }

    private void addToFilesExcludeAnnotations(List<AbstractSourceFile> files) {
        for (AbstractSourceFile<ClassEntity> file : files) {
            ClassEntity entity = file.getData();

            for (FieldEntity field : entity.getFields()) {
                if (Objects.isNull(field.getAnnotations())) {
                    field.setAnnotations(new IndentList<AnnotationEntity>());
                }

                if ((DomainHelper.isOneToOneBackReference(field)
                            || DomainHelper.hasManyToOneRelation(field))
                        && !hasExcludeAnnotations(entity.getFields())) {
                    field.getAnnotations().add(
                            AnnotationEntity.builder()
                                    .name(TO_STRING_EXCLUDE)
                                    .build()
                                    .setParentEntity(field)
                    );

                    field.getAnnotations().add(
                            AnnotationEntity.builder()
                                    .name(EQUALS_EXCLUDE)
                                    .build()
                                    .setParentEntity(field)
                    );

                    if (Objects.nonNull(entity.getMethods())) {
                        Iterator<MethodEntity> iterator = entity.getMethods().iterator();
                        while(iterator.hasNext()) {
                            MethodEntity method = iterator.next();
                            if (EQUALS.equals(method.getName()) || HASHCODE.equals(method.getName())) {
                                iterator.remove();
                            }
                        }
                    }

                    entity.getImports().add(ImportEntity.builder().value("lombok.ToString").build().setParentEntity(entity));
                    entity.getImports().add(ImportEntity.builder().value("lombok.EqualsAndHashCode").build().setParentEntity(entity));
                }
            }
        }
    }

    private boolean hasExcludeAnnotations(IndentList<FieldEntity> fields) {

        return fields.stream().anyMatch(field ->
                Objects.nonNull(field.getAnnotations()) &&
                field.getAnnotations().stream()
                        .anyMatch(annotation ->
                        TO_STRING_EXCLUDE.equals(annotation.getName())
                                || EQUALS_EXCLUDE.equals(annotation.getName()))
        );
    }
}
