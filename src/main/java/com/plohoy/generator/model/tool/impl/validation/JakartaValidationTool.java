package com.plohoy.generator.model.tool.impl.validation;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.API;

public class JakartaValidationTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "jakarta.validation";
    private static final String DEFAULT_ARTIFACT_ID = "jakarta.validation-api";
    private static final String SCOPE = "";

    public JakartaValidationTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, API);
    }

    public JakartaValidationTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
