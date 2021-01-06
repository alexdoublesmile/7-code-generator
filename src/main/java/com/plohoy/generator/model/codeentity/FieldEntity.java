package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class FieldEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String type;
    private String name;
    private FieldValueEntity value;
    private IndentList<AnnotationEntity> annotations;

    @Override
    public String toString() {
        return getTab(1, this) + annotations
                + getTab(1, this) + modifiers
                + type + SPACE_SYMBOL
                + name
                + value;
    }

    public void setValue(FieldValueEntity value) {
        this.value = value;
        this.value.setParentEntity(this);
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }
}
