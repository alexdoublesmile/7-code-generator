package com.plohoy.generator.model.codeentity.clazz;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.method.MethodEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class ClassEntity extends CodeEntity<ClassEntity> {
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
    private String schemaDescription;
    private List<EndPoint> endPoints;
    private boolean restEntity;

    public ClassEntity(
            String packageString,
            IndentList<ImportEntity> imports,
            IndentList<AnnotationEntity> annotations,
            EnumerationList<String> modifiers,
            ClassType classType,
            String name,
            String extendsClass,
            EnumerationList<ClassEntity> implInterfaces,
            String idType,
            IndentList<FieldEntity> fields,
            IndentList<BlockEntity> blocks,
            IndentList<ConstructorEntity> constructors,
            IndentList<MethodEntity> methods,
            IndentList<ClassEntity> innerClasses,
            String schemaDescription,
            List<EndPoint> endPoints,
            boolean restEntity
    ) {
        this.packageString = packageString;
        this.modifiers = modifiers;
        this.classType = classType;
        this.name = name;
        this.extendsClass = extendsClass;
        this.implInterfaces = implInterfaces;
        this.idType = idType;
        this.schemaDescription = schemaDescription;
        this.endPoints = endPoints;
        this.restEntity = restEntity;

        if (Objects.nonNull(imports)) setImports(imports);
        if (Objects.nonNull(annotations)) setAnnotations(annotations);
        if (Objects.nonNull(fields)) setFields(fields);
        if (Objects.nonNull(blocks)) setBlocks(blocks);
        if (Objects.nonNull(constructors)) setConstructors(constructors);
        if (Objects.nonNull(methods)) setMethods(methods);
        if (Objects.nonNull(innerClasses)) setInnerClasses(innerClasses);
    }

    public void setImports(IndentList<ImportEntity> imports) {
        while (imports.remove(null));

        this.imports = imports;
        this.imports.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        while (annotations.remove(null));

        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setFields(IndentList<FieldEntity> fields) {
        while (fields.remove(null));

        this.fields = fields;
        this.fields.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setBlocks(IndentList<BlockEntity> blocks) {
        while (blocks.remove(null));

        this.blocks = blocks;
        this.blocks.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setConstructors(IndentList<ConstructorEntity> constructors) {
        while (constructors.remove(null));

        this.constructors = constructors;
        this.constructors.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setMethods(IndentList<MethodEntity> methods) {
        while (methods.remove(null));

        this.methods = methods;
        this.methods.stream().forEach(element -> element.setParentEntity(this));
    }

    public void setInnerClasses(IndentList<ClassEntity> innerClasses) {
        while (innerClasses.remove(null));

        this.innerClasses = innerClasses;
        this.innerClasses.stream().forEach(element -> {
            element.setParentEntity(this);
        });
    }

    @Override
    public ClassEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

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
}


