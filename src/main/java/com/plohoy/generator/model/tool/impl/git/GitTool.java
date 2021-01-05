package com.plohoy.generator.model.tool.impl.git;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class GitTool extends AbstractTool {
    public GitTool(String version) {
        super(version);
    }

    public GitTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
