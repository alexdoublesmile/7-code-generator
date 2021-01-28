package com.plohoy.generator.model.tool.impl.maven.pombuilder.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.model.tool.impl.maven.MavenEntity;
import com.plohoy.generator.model.tool.impl.maven.MavenTemplate;
import com.plohoy.generator.model.tool.impl.maven.pombuilder.PomBuilder;
import com.plohoy.generator.model.tool.impl.maven.tag.*;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.HashMap;
import java.util.Objects;

import static com.plohoy.generator.model.tool.ToolType.MAVEN;
import static com.plohoy.generator.model.tool.ToolType.SPRING_BOOT;
import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.*;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.DEFAULT_VERSION;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.JAVA;

public class SimplePomBuilder implements PomBuilder {
    @Override
    public MavenEntity getPomCode(Source source, String rootPath) {
        HashMap<ToolType, AbstractTool> tools = source.getTools();

        ParentTag parent = null;
        if (Objects.nonNull(tools.get(SPRING_BOOT))) {
            parent = getSpringBootParent(tools.get(SPRING_BOOT).getVersion());
        }

        PropertiesTag properties = new PropertiesTag();
        DependenciesTag dependencies = new DependenciesTag();

        IndentList<CodeEntity> propertyList = getPropertiesFromTools(tools);
        propertyList.add(new PropertyTag(JAVA, source.getJdkVersion()));
        propertyList.add(new PropertyTag("hibernate_validator", "6.1.6.Final"));

        IndentList<CodeEntity> dependencyList = getDependenciesFromTools(tools);
        dependencyList.add(getSpringBootWebStarter());
        dependencyList.add(getSpringBootTestStarter());
        dependencyList.add(getSpringBootDataJpaStarter());
        dependencyList.add(getHibernateValidator());

        properties.setInnerTags(propertyList);

        dependencies.setInnerTags(dependencyList);

        return MavenEntity.builder()
                .header(POM_HEADER)
                .projectTag(
                        new ProjectTag(
                                new SimpleTag(POM_MODEL_VERSION, tools.get(MAVEN).getVersion()),
                                parent,
                                new GroupIdTag(source.getGroupName()),
                                new ArtifactIdTag(source.getArtifactName()),
                                new VersionTag(DEFAULT_VERSION),
                                new NameTag(source.getArtifactName()),
                                properties,
                                dependencies,
                                getPlugins(
                                        getSpringBootMavenPlugin(),
                                        getDefaultCompilerPlugin(
                                                getVersionReference(JAVA),
                                                getMapstructProcessorPath(), getLombokProcessorPath()))
                        )
                )
                .build();
    }
}
