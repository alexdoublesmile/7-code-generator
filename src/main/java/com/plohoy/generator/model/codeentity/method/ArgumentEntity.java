package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.codeentity.AnnotationEntity;
import com.plohoy.generator.model.codeentity.CodeEntity;
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
    private EnumerationList<AnnotationEntity> annotations;

    @Override
    public String toString() {
        return EMPTY_STRING + annotations
                + modifiers
                + type + SPACE_SYMBOL
                + name;
    }

    public void setAnnotations(EnumerationList<AnnotationEntity> annotations) {
        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }
}
