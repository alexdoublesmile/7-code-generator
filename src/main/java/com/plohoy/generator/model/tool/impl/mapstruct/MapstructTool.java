package com.plohoy.generator.model.tool.impl.mapstruct;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class MapstructTool extends AbstractTool {
    public MapstructTool(String version) {
        super(version);
    }

    public MapstructTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
