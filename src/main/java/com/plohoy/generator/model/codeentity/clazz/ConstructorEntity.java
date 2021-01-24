package com.plohoy.generator.model.codeentity.clazz;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.method.ArgumentEntity;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class ConstructorEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String name;
    private EnumerationList<ArgumentEntity> args;
    private EnumerationList<String> exceptions;
    private IndentList<AnnotationEntity> annotations;
    private String body;

    public ConstructorEntity(
            EnumerationList<String> modifiers,
            String name,
            EnumerationList<ArgumentEntity> args,
            EnumerationList<String> exceptions,
            IndentList<AnnotationEntity> annotations,
            String body
    ) {
        this.modifiers = modifiers;
        this.name = name;
        this.exceptions = exceptions;
        this.body = body;

        if (Objects.nonNull(args)) setArgs(args);
        if (Objects.nonNull(annotations)) setAnnotations(annotations);
    }

    public void setArgs(EnumerationList<ArgumentEntity> args) {
        while (args.remove(null));

        this.args = args;
        this.args.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        while (annotations.remove(null));

        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }

    @Override
    public ConstructorEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return annotations.toString()
                + getTab(getNestLvl()) + modifiers
                + name
                + OPEN_PARAM_BRACKET + args + CLOSE_PARAM_BRACKET + getIndent()
                + exceptions + getIndent()
                + OPEN_BODY_BRACKET + getIndent()
                + getTab(getNestLvl() + 1) + body + getIndent()
                + getTab(getNestLvl()) + CLOSE_BODY_BRACKET + getIndent();
    }
}
