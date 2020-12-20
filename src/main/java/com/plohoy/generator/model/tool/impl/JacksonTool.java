package com.plohoy.generator.model.tool.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class JacksonTool extends AbstractTool {
    public JacksonTool(String version) {
        super(version);
    }

    public JacksonTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
