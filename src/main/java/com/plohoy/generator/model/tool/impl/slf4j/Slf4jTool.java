package com.plohoy.generator.model.tool.impl.slf4j;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class Slf4jTool extends AbstractTool {
    public Slf4jTool(String version) {
        super(version);
    }

    public Slf4jTool() {
    }

    @Override
    public Source generateCode(Source source) {
        return source;
    }
}
