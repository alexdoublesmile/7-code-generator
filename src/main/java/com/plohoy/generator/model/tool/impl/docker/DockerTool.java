package com.plohoy.generator.model.tool.impl.docker;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class DockerTool extends AbstractTool {
    public DockerTool(String version) {
        super(version);
    }

    public DockerTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
