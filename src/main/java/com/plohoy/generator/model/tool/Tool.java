package com.plohoy.generator.model.tool;

import com.plohoy.generator.model.Source;

public abstract class Tool {
    private String version;

    public Tool(String version) {
        this.version = version;
    }

    public Tool() {
    }

    public abstract Source generateCode(Source source);
}
