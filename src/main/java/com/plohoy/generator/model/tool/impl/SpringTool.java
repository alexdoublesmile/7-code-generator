package com.plohoy.generator.model.tool.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class SpringTool extends AbstractTool {
    public SpringTool(String version) {
        super(version);
    }

    public SpringTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
