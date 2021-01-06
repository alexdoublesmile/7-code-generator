package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class ArgumentEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String type;
    private String name;
    private IndentList<AnnotationEntity> annotations;

    @Override
    public String toString() {
        return EMPTY_STRING + annotations
                + modifiers
                + type + SPACE_SYMBOL
                + name;
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }
}
