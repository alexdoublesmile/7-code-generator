package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

@Data
@Builder
public class ConstructorEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String name;
    private EnumerationList<FieldEntity> args;
    private EnumerationList<String> exceptions;
    private IndentList<AnnotationEntity> annotations;
    private String body;

    @Override
    public String toString() {
        return annotations.toString()
                + modifiers
                + name
                + OPEN_PARAM_BRACKET
                + args
                + CLOSE_PARAM_BRACKET + getIndent()
                + exceptions + getIndent()
                + OPEN_BODY_BRACKET + getIndent()
                + body + getIndent()
                + CLOSE_BODY_BRACKET + getIndent();
    }
}
