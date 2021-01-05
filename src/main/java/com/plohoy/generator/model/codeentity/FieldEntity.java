package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

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
        return EMPTY_STRING + annotations
                + modifiers
                + type + SPACE_SYMBOL
                + name
                + value;
    }
}
