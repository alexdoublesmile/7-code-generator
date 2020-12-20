package com.plohoy.generator.model.tool.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class JavaDocTool extends AbstractTool {
    public JavaDocTool(String version) {
        super(version);
    }

    public JavaDocTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
