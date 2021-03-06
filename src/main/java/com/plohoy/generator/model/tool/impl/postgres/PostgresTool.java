package com.plohoy.generator.model.tool.impl.postgres;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.CORE;

public class PostgresTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.postgresql";
    private static final String DEFAULT_ARTIFACT_ID = "postgresql";
    private static final String SCOPE = "runtime";

    public PostgresTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, CORE);
    }

    public PostgresTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
