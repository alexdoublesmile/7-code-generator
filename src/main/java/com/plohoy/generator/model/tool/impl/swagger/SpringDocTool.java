package com.plohoy.generator.model.tool.impl.swagger;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ImportEntity;
import com.plohoy.generator.model.codeentity.method.MethodEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.CORE;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.MINUS;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SPACE;

public class SpringDocTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "org.springdoc";
    private static final String DEFAULT_ARTIFACT_ID = "springdoc-openapi-ui";
    private static final String SCOPE = "";

    public SpringDocTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true, CORE);
    }

    public SpringDocTool() {
    }

    public Source generateCode(Source source) {
        List<AbstractSourceFile> launchFile = source.getSourceData().get(FileType.LAUNCHER);
        ClassEntity launcher = (ClassEntity) launchFile.get(0).getData();
        launcher.getMethods().add(
                MethodEntity.builder()
                        .annotations(new IndentList<AnnotationEntity>(AnnotationEntity.builder().name("Bean").build()))
                        .modifiers(CodeTemplate.getPublicMod())
                        .returnType("OpenAPI")
                        .name("customOpenAPI")
                        .body(getOpenApiBody(source))
                        .build()
                        .setParentEntity(launcher)
        );

        launcher.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.models.Components").build().setParentEntity(launcher));
        launcher.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.models.OpenAPI").build().setParentEntity(launcher));
        launcher.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.models.info.Info").build().setParentEntity(launcher));
        launcher.getImports().add(ImportEntity.builder().value("org.springframework.context.annotation.Bean").build().setParentEntity(launcher));

        return source;
    }

    private String getOpenApiBody(Source source) {
        return String.format( "return new OpenAPI()\n" +
                "                .components(new Components())\n" +
                "                .info(new Info()\n" +
                "                        .title(\"%s\")", StringUtils.capitalize(source.getName().replace(MINUS, SPACE))) +
                (Objects.nonNull(source.getDescription())
                ? String.format("\n                        .description(\"%s\"));", source.getDescription())
                : ");");
    }
}
