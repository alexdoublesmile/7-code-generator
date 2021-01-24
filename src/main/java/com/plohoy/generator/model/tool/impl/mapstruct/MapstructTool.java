package com.plohoy.generator.model.tool.impl.mapstruct;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;
import lombok.Getter;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.CORE;

@Getter
public class MapstructTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.mapstruct";
    private static final String DEFAULT_ARTIFACT_ID = "mapstruct";
    private static final String SCOPE = "";

    public MapstructTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, CORE);
    }

    public MapstructTool() {
    }

    public Source generateCode(Source source) {
        return source;
    }
}
