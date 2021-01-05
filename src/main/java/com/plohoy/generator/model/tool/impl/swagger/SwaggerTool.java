package com.plohoy.generator.model.tool.impl.swagger;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class SwaggerTool extends AbstractTool {
    public SwaggerTool(String version) {
        super(version);
    }

    public SwaggerTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
