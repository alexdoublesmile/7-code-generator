package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class MethodEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String returnType;
    private String name;
    private EnumerationList<ArgumentEntity> args;
    private EnumerationList<String> exceptions;
    private IndentList<AnnotationEntity> annotations;
    private String body;

    @Override
    public String toString() {
        return Objects.nonNull(annotations)
                    ? getTab(1, this) + annotations
                    : EMPTY_STRING
                + (Objects.nonNull(modifiers)
                    ? getTab(1, this) + modifiers
                    : getTab(1, this) + EMPTY_STRING)
                + returnType + SPACE_SYMBOL
                + name
                + OPEN_PARAM_BRACKET
                + args
                + CLOSE_PARAM_BRACKET
                + exceptions
                + (Objects.nonNull(body)
                    ? (SPACE_SYMBOL + OPEN_BODY_BRACKET + getIndent()
                        + getTab(2, this) + body + getIndent()
                        + getTab(1, this) + CLOSE_BODY_BRACKET + getIndent())
                    : CODE_DELIMITER
        );
    }

    public void setArgs(EnumerationList<ArgumentEntity> args) {
        this.args = args;
        this.args.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }
}
