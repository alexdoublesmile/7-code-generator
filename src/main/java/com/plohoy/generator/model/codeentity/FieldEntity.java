package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.StringUtil;
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
    private String value;
    private FieldValueEntity values;
    private IndentList<AnnotationEntity> annotations;

    @Override
    public String toString() {
        return StringUtil.checkForNull(annotations,
                    getTab(1, this) + annotations)
                + StringUtil.checkForNull(modifiers,
                    getTab(1, this) + modifiers)
                + type + SPACE_SYMBOL
                + name
                + StringUtil.checkStringForNull(value,
                    SPACE_SYMBOL + EQUALS + SPACE_SYMBOL + value)
                + values;
    }

    public void setValues(FieldValueEntity values) {
        this.values = values;
        this.values.setParentEntity(this);
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }
}
