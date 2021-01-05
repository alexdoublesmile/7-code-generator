package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

@Data
@Builder
public class ClassEntity extends CodeEntity {
    private String packageString;
    private IndentList<ImportEntity> imports;
    private IndentList<AnnotationEntity> annotations;
    private EnumerationList<String> modifiers;
    private ClassType classType;
    private String name;
    private ClassEntity parent;
    private EnumerationList<ClassEntity> interfaces;
    private IndentList<FieldEntity> fields;
    private IndentList<BlockEntity> blocks;
    private IndentList<ConstructorEntity> constructors;
    private IndentList<MethodEntity> methods;
    private int nestLvl;

    @Override
    public String toString() {
        String result = packageString
                + imports + getIndent()
                + annotations
                + modifiers
                + classType.getTypeName() + SPACE_SYMBOL
                + name + SPACE_SYMBOL
                + (Objects.nonNull(parent) ? EXTEND_WORD + SPACE_SYMBOL + parent.getName() + SPACE_SYMBOL : NULL_STRING)
                + interfaces
                + OPEN_BODY_BRACKET + getIndent()
                + fields
                + blocks
                + constructors
                + methods
                + CLOSE_BODY_BRACKET + getIndent();

        return result.replace(NULL_STRING, EMPTY_STRING);
    }
}


