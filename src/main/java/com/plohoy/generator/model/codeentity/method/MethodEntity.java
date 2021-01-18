package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        return StringUtil.checkForNull(annotations,
                    getTab(getNestLvl() + 1) + annotations)
                + getTab(getNestLvl() + 1) + modifiers
                + returnType + SPACE
                + name
                + OPEN_PARAM_BRACKET
                + args
                + CLOSE_PARAM_BRACKET
                + exceptions
                + StringUtil.checkForNull(body,
                    (SPACE + OPEN_BODY_BRACKET + getIndent()
                            + getTab(getNestLvl() + 2) + body + getIndent()
                            + getTab(getNestLvl() + 1) + CLOSE_BODY_BRACKET)
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
