package com.plohoy.generator.model.tool.impl.spring;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class SpringBootTool extends AbstractTool {
    public SpringBootTool(String version) {
        super(version);
    }

    public SpringBootTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
