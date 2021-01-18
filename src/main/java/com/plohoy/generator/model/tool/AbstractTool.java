package com.plohoy.generator.model.tool;

import com.plohoy.generator.model.Source;
import lombok.Data;

@Data
public abstract class AbstractTool {
    private String name;
    private String version;

    public AbstractTool(String version) {
        this.version = version;
    }

    public AbstractTool() {
    }

    public abstract Source generateCode(Source source);
}
