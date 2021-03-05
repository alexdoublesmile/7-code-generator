package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.ArgumentAnnotationEntity;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SPACE;

@Data
@Builder
public class ArgumentEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String type;
    private String name;
    private String description;
    private EnumerationList<ArgumentAnnotationEntity> annotations;

    public ArgumentEntity(
            EnumerationList<String> modifiers,
            String type,
            String name,
            String description,
            EnumerationList<ArgumentAnnotationEntity> annotations
    ) {
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
        this.description = description;

        if (Objects.nonNull(annotations)) setAnnotations(annotations);
    }

    public void setAnnotations(EnumerationList<ArgumentAnnotationEntity> annotations) {
        while (annotations.remove(null));

        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }

    @Override
    public ArgumentEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return EMPTY + annotations
                + modifiers
                + type + SPACE
                + name;
    }
}
