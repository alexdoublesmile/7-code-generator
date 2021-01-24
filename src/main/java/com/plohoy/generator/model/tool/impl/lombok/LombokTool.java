package com.plohoy.generator.model.tool.impl.lombok;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;
import lombok.Getter;

@Getter
public class LombokTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.projectlombok";
    private static final String DEFAULT_ARTIFACT_ID = "lombok";
    private static final String SCOPE = "";

    public LombokTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true);
    }

    public LombokTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
