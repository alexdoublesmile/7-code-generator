package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
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
public class MethodEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String returnType;
    private String name;
    private EnumerationList<ArgumentEntity> args;
    private EnumerationList<String> exceptions;
    private IndentList<AnnotationEntity> annotations;
    private String body;
    private EndPoint endPoint;

    public MethodEntity(
            EnumerationList<String> modifiers,
            String returnType,
            String name,
            EnumerationList<ArgumentEntity> args,
            EnumerationList<String> exceptions,
            IndentList<AnnotationEntity> annotations,
            String body,
            EndPoint endPoint
    ) {
        this.modifiers = modifiers;
        this.returnType = returnType;
        this.name = name;
        this.exceptions = exceptions;
        this.body = body;
        this.endPoint = endPoint;

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
    public MethodEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return annotations
                + getTab(getNestLvl()) + modifiers + returnType + SPACE + name
                + OPEN_PARAM_BRACKET + args + CLOSE_PARAM_BRACKET
                + exceptions
                + StringUtil.checkForNull(body,
                (SPACE + OPEN_BODY_BRACKET + getIndent()
                        + getTab(getNestLvl() + 1) + body + getIndent()
                        + getTab(getNestLvl()) + CLOSE_BODY_BRACKET)
        );
    }
}
