package com.plohoy.generator.model.codeentity.field;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class FieldEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String type;
    private String name;
    private String value;
    private FieldValueEntity values;
    private IndentList<AnnotationEntity> annotations;
    private String schemaDescription;

    public FieldEntity(
            EnumerationList<String> modifiers,
            String type,
            String name,
            String value,
            FieldValueEntity values,
            IndentList<AnnotationEntity> annotations,
            String schemaDescription
    ) {
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
        this.value = value;
        this.schemaDescription = schemaDescription;

        if (Objects.nonNull(values)) setValues(values);
        if (Objects.nonNull(annotations)) setAnnotations(annotations);
    }

    public void setValues(FieldValueEntity values) {
        this.values = values;
        this.values.setParentEntity(this);
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        while (annotations.remove(null));

        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }

    @Override
    public FieldEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return annotations
                + StringUtil.checkForNull(modifiers,
                getTab(getNestLvl()) + modifiers)
                + type + SPACE + name
                + StringUtil.checkStringForNull(value,
                SPACE + EQUAL + SPACE + value)
                + values;
    }
}
