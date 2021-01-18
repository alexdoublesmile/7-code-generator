package com.plohoy.generator.model.codeentity;

import lombok.Data;

import java.util.Objects;

@Data
public abstract class CodeEntity {
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
}
