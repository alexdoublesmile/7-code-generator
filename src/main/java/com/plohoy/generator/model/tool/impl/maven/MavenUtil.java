package com.plohoy.generator.model.tool.impl.maven;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.impl.maven.pombuilder.PomBuilder;
import com.plohoy.generator.model.tool.impl.maven.pombuilder.impl.DTOPomBuilder;
import com.plohoy.generator.model.tool.impl.maven.pombuilder.impl.SimplePomBuilder;
import lombok.Getter;

import java.util.HashMap;

public class MavenUtil {
    private PomBuilderHandler pomBuilderHandler = new PomBuilderHandler();

    public MavenEntity getPomCode(Source source, String rootPath) {
        return pomBuilderHandler.getPomBuilders()
                .get(source.isDtoModuleExists())
                .getPomCode(source, rootPath);
    }


    @Getter
    private class PomBuilderHandler {
        private HashMap<Boolean, PomBuilder> pomBuilders;

        public PomBuilderHandler() {
            pomBuilders = new HashMap<>();
            pomBuilders.put(true, new DTOPomBuilder());
            pomBuilders.put(false, new SimplePomBuilder());
        }
    }
}
