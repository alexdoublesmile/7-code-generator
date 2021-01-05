package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

@Data
@Builder
public class MethodEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String returnType;
    private String name;
    private EnumerationList<FieldEntity> args;
    private EnumerationList<String> exceptions;
    private IndentList<AnnotationEntity> annotations;
    private String body;

    @Override
    public String toString() {
        return EMPTY_STRING + annotations
                + modifiers
                + returnType + SPACE_SYMBOL
                + name
                + OPEN_PARAM_BRACKET
                + args
                + CLOSE_PARAM_BRACKET + SPACE_SYMBOL
                + exceptions
                + OPEN_BODY_BRACKET + getIndent()
                + body + getIndent()
                + CLOSE_BODY_BRACKET + getIndent();
    }
}
