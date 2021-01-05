package com.plohoy.generator.model.codeentity;

public enum ClassType {
    CLASS("class"),
    INTERFACE("interface");

    private String typeName;

    ClassType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
