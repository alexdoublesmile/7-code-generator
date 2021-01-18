package com.plohoy.generator.model.codeentity.clazz;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.method.MethodEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

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
    private String idType;
    private IndentList<FieldEntity> fields;
    private IndentList<BlockEntity> blocks;
    private IndentList<ConstructorEntity> constructors;
    private IndentList<MethodEntity> methods;
    private IndentList<ClassEntity> innerClasses;

    @Override
    public String toString() {
        return (
            getTab(getNestLvl()) + packageString
            + StringUtil.checkForNull(imports, imports + getIndent())
            + annotations
            + modifiers
            + StringUtil.checkForNull(classType, classType.getTypeName() + SPACE)
            + name + SPACE
            + StringUtil.checkForNull(extendsClass, EXTENDS + SPACE + extendsClass + SPACE)
            + implInterfaces
            + OPEN_BODY_BRACKET + getIndent()
            + StringUtil.checkForNull(fields, fields + INDENT)
            + blocks
            + constructors
            + methods
            + innerClasses
            + CLOSE_BODY_BRACKET + getIndent()
        ).replace(NULL, EMPTY);
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
        });
    }
}


