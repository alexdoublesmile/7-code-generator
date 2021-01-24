package com.plohoy.generator.model.tool.impl.liquibase;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.CORE;

public class LiquibaseTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.liquibase";
    private static final String DEFAULT_ARTIFACT_ID = "liquibase-core";
    private static final String SCOPE = "";

    public LiquibaseTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, CORE);
    }

    public LiquibaseTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
