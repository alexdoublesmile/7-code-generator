package com.plohoy.generator.model.tool.impl.maven.tag;

import lombok.Data;

@Data
public class ModuleTag extends TagEntity {

    public ModuleTag(String body) {
        super("module", null, body, false, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
