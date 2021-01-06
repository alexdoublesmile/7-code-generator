package com.plohoy.generator.model.codeentity;

import lombok.Data;

@Data
public abstract class CodeEntity {
    protected int nestLvl;
    protected CodeEntity parentEntity;
}
