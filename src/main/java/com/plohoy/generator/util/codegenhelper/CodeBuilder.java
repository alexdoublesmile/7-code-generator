package com.plohoy.generator.util.codegenhelper;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.ClassEntity;
import com.plohoy.generator.model.codeentity.ClassType;

public class CodeBuilder {
    CodeTemplate codeTemplate = new CodeTemplate();

    public String buildSpringBootLaunchCode(Source source, String fileName) {

        return ClassEntity.builder()
                .packageString(codeTemplate.getPackageString(
                        source.getCorePackageName()))
                .imports(codeTemplate.generateSpringBootLauncherImports())
                .annotations(codeTemplate.generateSpringBootLauncherAnnotations())
                .modifiers(codeTemplate.getPublicModifier())
                .classType(ClassType.CLASS)
                .name(fileName)
                .methods(codeTemplate.generateMainMethod(
                        codeTemplate.generateSpringLauncherBody(fileName)))
                .build()
                .toString();
    }
}
