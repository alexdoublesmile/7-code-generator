package com.plohoy.generator.model.tool.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class SpringDocTool extends AbstractTool {
    public SpringDocTool(String version) {
        super(version);
    }

    public SpringDocTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
