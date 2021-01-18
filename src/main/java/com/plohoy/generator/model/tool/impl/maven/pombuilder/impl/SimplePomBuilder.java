package com.plohoy.generator.model.tool.impl.maven.pombuilder.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.model.tool.impl.maven.MavenTemplate;
import com.plohoy.generator.model.tool.impl.maven.pombuilder.PomBuilder;
import com.plohoy.generator.model.tool.impl.maven.tag.*;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.plohoy.generator.model.tool.ToolType.MAVEN;
import static com.plohoy.generator.model.tool.ToolType.SPRING_BOOT;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.INDENT;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SPRING_MAIN_PACKAGE;

public class SimplePomBuilder implements PomBuilder {
    @Override
    public String getPomCode(Source source, String rootPath) {
        HashMap<ToolType, AbstractTool> tools = source.getTools();

        ParentTag parent = null;
        if (Objects.nonNull(tools.get(SPRING_BOOT))) {
            parent = new ParentTag(
                    new GroupIdTag(SPRING_MAIN_PACKAGE),
                    new ArtifactIdTag("spring-boot-starter-parent"),
                    new VersionTag(tools.get(SPRING_BOOT).getVersion())
            );
        }

        PropertiesTag properties = new PropertiesTag();
        IndentList<CodeEntity> propertyList = new IndentList<CodeEntity>();
        propertyList.add(new PropertyTag("java", source.getJdkVersion()));
        for (Map.Entry<ToolType, AbstractTool> tool : tools.entrySet()) {
            if (Objects.nonNull(tool.getValue().getVersion())) {
                propertyList.add(new PropertyTag(
                        tool.getKey().name().toLowerCase(),
                        tool.getValue().getVersion()
                ));
            }
        }
        properties.setInnerTags(propertyList);

        return
            MavenTemplate.getPomHeader() + INDENT +
            new ProjectTag(
                    new EnumerationList<String>(false, MavenTemplate.getProjectAttrs()),
                    new SimpleTag("modelVersion", tools.get(MAVEN).getVersion()),
                    parent,
                    new GroupIdTag(source.getGroupName()),
                    new ArtifactIdTag(source.getArtifactName()),
                    new VersionTag("0.0.1"),
                    new NameTag(source.getName()),
                    properties,
                    new DependenciesTag(
                            new DependencyTag(
                                    new GroupIdTag("org.springframework.boot"),
                                    new ArtifactIdTag("spring-boot-starter-web")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("org.projectlombok"),
                                    new ArtifactIdTag("lombok"),
                                    new VersionTag("${lombok.version}"),
                                    new ScopeTag("provided")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("org.mapstruct"),
                                    new ArtifactIdTag("mapstruct"),
                                    new VersionTag("${org.mapstruct.version}"),
                                    new ScopeTag("provided")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("org.springframework.boot"),
                                    new ArtifactIdTag("spring-boot-starter-test"),
                                    new ScopeTag("test"),
                                    new SimpleTag(
                                            "exclusions",
                                            new SimpleTag(
                                                    "exclusion",
                                                    new GroupIdTag("org.junit.vintage"),
                                                    new ArtifactIdTag("junit-vintage-engine")
                                            )
                                    )
                            ),
                            new DependencyTag(
                                    new GroupIdTag("org.springframework.boot"),
                                    new ArtifactIdTag("spring-boot-starter-data-jpa")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("org.postgresql"),
                                    new ArtifactIdTag("postgresql")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("io.swagger.core.v3"),
                                    new ArtifactIdTag("swagger-annotations"),
                                    new VersionTag("${swagger.core.version}")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("org.springdoc"),
                                    new ArtifactIdTag("springdoc-openapi-ui"),
                                    new VersionTag("${springdoc.version}")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("com.vladmihalcea"),
                                    new ArtifactIdTag("hibernate-types-52"),
                                    new VersionTag("2.9.13")
                            ),
                            new DependencyTag(
                                    new GroupIdTag("junit"),
                                    new ArtifactIdTag("junit"),
                                    new ScopeTag("test")
                            )
                    ),
                    new BuildTag(
                            new PluginsTag(
                                    new PluginTag(
                                            new GroupIdTag("org.springframework.boot"),
                                            new ArtifactIdTag("spring-boot-maven-plugin")
                                    ),
                                    new PluginTag(
                                            new GroupIdTag("org.apache.maven.plugins"),
                                            new ArtifactIdTag("maven-compiler-plugin"),
                                            new VersionTag("3.5.1"),
                                            new SimpleTag(
                                                    "configuration",
                                                    new SimpleTag("source", "11"),
                                                    new SimpleTag("target", "11"),
                                                    new SimpleTag("annotationProcessorPaths",
                                                            new SimpleTag(
                                                                    "path",
                                                                    new GroupIdTag("org.mapstruct"),
                                                                    new ArtifactIdTag("mapstruct-processor"),
                                                                    new VersionTag("${org.mapstruct.version}")
                                                            ),
                                                            new SimpleTag(
                                                                    "path",
                                                                    new GroupIdTag("org.projectlombok"),
                                                                    new ArtifactIdTag("lombok"),
                                                                    new VersionTag("${lombok.version}")
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            ).toString();
    }
}
