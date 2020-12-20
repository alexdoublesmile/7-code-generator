package com.plohoy.generator.model.tool.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class ReadMeTool extends AbstractTool {
    public ReadMeTool(String version) {
        super(version);
    }

    public ReadMeTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
