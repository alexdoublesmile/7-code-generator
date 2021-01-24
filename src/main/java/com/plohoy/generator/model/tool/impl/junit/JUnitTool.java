package com.plohoy.generator.model.tool.impl.junit;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;
import lombok.Getter;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.CORE;

@Getter
public class JUnitTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "junit";
    private static final String DEFAULT_ARTIFACT_ID = "junit";
    private static final String SCOPE = "test";

    public JUnitTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, CORE);
    }

    public JUnitTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
