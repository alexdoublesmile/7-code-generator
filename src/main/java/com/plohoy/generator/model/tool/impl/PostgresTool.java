package com.plohoy.generator.model.tool.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class PostgresTool extends AbstractTool {
    public PostgresTool(String version) {
        super(version);
    }

    public PostgresTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
