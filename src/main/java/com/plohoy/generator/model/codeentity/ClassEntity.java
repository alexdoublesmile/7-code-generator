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
    private String extendsClass;
    private EnumerationList<ClassEntity> implInterfaces;
    private IndentList<FieldEntity> fields;
    private IndentList<BlockEntity> blocks;
    private IndentList<ConstructorEntity> constructors;
    private IndentList<MethodEntity> methods;
    private IndentList<ClassEntity> innerClasses;

    @Override
    public String toString() {
        return (
            getTab(0, this) + packageString
            + (Objects.nonNull(imports) ? imports + getIndent() : NULL_STRING)
            + annotations
            + modifiers
            + classType.getTypeName() + SPACE_SYMBOL
            + name + SPACE_SYMBOL
            + (Objects.nonNull(extendsClass) ? EXTEND_WORD + SPACE_SYMBOL + extendsClass + SPACE_SYMBOL : NULL_STRING)
            + implInterfaces
            + OPEN_BODY_BRACKET + getIndent()
            + fields
            + blocks
            + constructors
            + methods
            + innerClasses
            + CLOSE_BODY_BRACKET + getIndent()
        ).replace(NULL_STRING, EMPTY_STRING);
    }

    public void setImports(IndentList<ImportEntity> imports) {
        this.imports = imports;
        this.imports.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setFields(IndentList<FieldEntity> fields) {
        this.fields = fields;
        this.fields.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setBlocks(IndentList<BlockEntity> blocks) {
        this.blocks = blocks;
        this.blocks.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setConstructors(IndentList<ConstructorEntity> constructors) {
        this.constructors = constructors;
        this.constructors.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setMethods(IndentList<MethodEntity> methods) {
        this.methods = methods;
        this.methods.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setInnerClasses(IndentList<ClassEntity> innerClasses) {
        this.innerClasses = innerClasses;
        this.innerClasses.stream().forEach(element -> {
            element.setParentEntity(this);
            element.setNestLvl(1);
        });
    }
}


