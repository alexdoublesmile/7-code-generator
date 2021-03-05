package com.plohoy.generator.model.tool.impl.validation;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.field.ValidationEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.domainhelper.EntityHelper;
import com.plohoy.generator.util.domainhelper.FieldHelper;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.model.file.FileType.DTO;
import static com.plohoy.generator.model.file.FileType.ENTITY;
import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.API;

public class JakartaValidationTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "jakarta.validation";
    private static final String DEFAULT_ARTIFACT_ID = "jakarta.validation-api";
    private static final String SCOPE = "";

    public JakartaValidationTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, API);
    }

    private Source source;

    @Override
    public Source generateCode(Source source) {
        this.source = source;
        List<AbstractSourceFile> dtoFiles = source.getSourceData().get(DTO);

        for (AbstractSourceFile<ClassEntity> dtoFile : dtoFiles) {
//            List<ClassEntity> markedValidatedEntities = new ArrayList<>();

            ClassEntity dto = dtoFile.getData();

            List<FieldEntity> needValidFields = dto.getFields()
                    .stream()
                    .filter(FieldHelper::needValidation)
                    .collect(Collectors.toList());

            List<FieldEntity> needValidInnerEntities = dto.getFields()
                    .stream()
                    .filter(field -> {
                        ClassEntity entityByType = EntityHelper.getEntityByType(field.getType(), dtoFiles);
                        return Objects.nonNull(entityByType)
                                && EntityHelper.needValidation(entityByType)
                                && !entityByType.hasAnyEndPoint();
                    })
                    .collect(Collectors.toList());

            for (FieldEntity field : needValidFields) {
                if (Objects.isNull(field.getAnnotations())) {
                    field.setAnnotations(new IndentList<AnnotationEntity>());
                }

                getValidateAnnotations(field, dtoFiles)
                        .stream()
                        .forEach(annotation ->
                                field.getAnnotations()
                                        .add(annotation.setParentEntity(field)));
            }

            for (FieldEntity field : needValidInnerEntities) {
                if (Objects.isNull(field.getAnnotations())) {
                    field.setAnnotations(new IndentList<AnnotationEntity>());
                }

                field.getAnnotations()
                        .add(AnnotationEntity.builder()
                                .name("Valid")
                                .build()
                                .setParentEntity(field));
            }
        }

        return source;
    }

    private List<AnnotationEntity> getValidateAnnotations(FieldEntity requestField, List<AbstractSourceFile> dtoFiles) {
        List<AnnotationEntity> validateAnnotations = new ArrayList<>();

            requestField.getValidationList()
                    .stream()
                    .forEach(validationEntity -> validateAnnotations.add(getValidateAnnotation(validationEntity)));

        return validateAnnotations;
    }

    private AnnotationEntity getValidateAnnotation(ValidationEntity validationEntity) {
        List<PropertyEntity> validationProperties = validationEntity.getProperties();
        PropertyEntity singleProperty = null;
        EnumerationList<PropertyEntity> annotationProperties = null;

        if (Objects.nonNull(validationProperties) && validationProperties.size() > 0) {
            if (validationProperties.size() == 1) {
                singleProperty = validationProperties.get(0);
            } else {
                annotationProperties = new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false, validationProperties);
            }
        }

        return AnnotationEntity.builder()
                .name(StringUtil.toCamelCase(validationEntity.getType().name()))
                .value(validationEntity.getValue())
                .property(singleProperty)
                .properties(annotationProperties)
                .build();
    }
}
