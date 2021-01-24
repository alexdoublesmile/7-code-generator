package com.plohoy.generator.model.codeentity;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class CodeEntity<T> {
    protected CodeEntity parentEntity;

    protected int getNestLvl() {
        int nestLvl = 0;
        CodeEntity parentEntity = this.parentEntity;

        while (Objects.nonNull(parentEntity)) {
            nestLvl++;
            parentEntity = parentEntity.getParentEntity();
        }

        return nestLvl;
    }

    protected int getParentNestLvl() {
        return getNestLvl() - 1;
    }

    public abstract T setParentEntity(CodeEntity parentEntity);
}
