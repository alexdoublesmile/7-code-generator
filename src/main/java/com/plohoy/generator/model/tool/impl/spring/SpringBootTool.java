package com.plohoy.generator.model.tool.impl.spring;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;

public class SpringBootTool extends AbstractTool {

    private final String PARENT_ARTIFACT_ID = "spring-boot-starter-parent";
    private final String DEPENDENCIES_ARTIFACT_ID = "spring-boot-starter-dependencies";
    private final String DEFAULT_GROUP_ID = "org.springframework.boot";
    private final String STARTER_ARTIFACT_ID = "spring-boot-starter";
    private final String WEB_ARTIFACT_ID = "spring-boot-starter-web";
    private final String DATA_JPA_ARTIFACT_ID = "spring-boot-starter-data-jpa";
    private final String TEST_ARTIFACT_ID = "spring-boot-starter-test";

    private final String SCOPE = "";

    public SpringBootTool(String version) {
        super(version);
    }

    public SpringBootTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
