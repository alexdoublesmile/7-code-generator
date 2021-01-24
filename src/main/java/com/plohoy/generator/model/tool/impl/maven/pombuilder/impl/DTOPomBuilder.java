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

import static com.plohoy.generator.model.tool.ToolType.MAVEN;
import static com.plohoy.generator.model.tool.ToolType.SPRING_BOOT;
import static com.plohoy.generator.model.tool.impl.maven.MavenTemplate.*;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class DTOPomBuilder implements PomBuilder {
    @Override
    public MavenEntity getPomCode(Source source, String rootPath) {
        if (rootPath.contains(CORE)) {
            return corePomCode(source);

        } else if (rootPath.contains(API)) {
            return apiPomCode(source);

        } else {
            return parentPomCode(source);

        }
    }

    private MavenEntity parentPomCode(Source source) {
        HashMap<ToolType, AbstractTool> tools = source.getTools();

        PropertiesTag properties = new PropertiesTag();
        IndentList<CodeEntity> propertyList = MavenTemplate.getPropertiesFromTools(tools);
        propertyList.add(new PropertyTag(JAVA, source.getJdkVersion()));
        propertyList.add(new PropertyTag("hibernate_validator", "6.1.6.Final"));

        properties.setInnerTags(propertyList);

        return MavenEntity.builder()
                .header(POM_HEADER)
                .projectTag(
                        new ProjectTag(
                                new SimpleTag(POM_MODEL_VERSION, tools.get(MAVEN).getVersion()),
                                new GroupIdTag(source.getGroupName()),
                                new ArtifactIdTag(source.getArtifactName()),
                                new VersionTag(DEFAULT_VERSION),
                                new NameTag(source.getName()),
                                new PackagingTag(POM),
                                new ModulesTag(source.getName()),
                                properties,
                                MavenTemplate.getSpringBootDependencies(
                                        getVersionReference(SPRING_BOOT.name().toLowerCase())),
                                MavenTemplate.getPlugins(
                                        getDefaultCompilerPlugin(getVersionReference(JAVA)))
                        )
                )
                .build();
    }

    private MavenEntity apiPomCode(Source source) {
        HashMap<ToolType, AbstractTool> tools = source.getTools();

        DependenciesTag dependencies = new DependenciesTag();
        IndentList<CodeEntity> dependencyList = getDependenciesFromTools(tools, CORE);

        dependencies.setInnerTags(dependencyList);

        return MavenEntity.builder()
                .header(POM_HEADER)
                .projectTag(
                        new ProjectTag(
                                new SimpleTag(POM_MODEL_VERSION, tools.get(MAVEN).getVersion()),
                                getParent(source),
                                new GroupIdTag(source.getGroupName()),
                                new ArtifactIdTag(source.getArtifactName() + MINUS + API),
                                new PackagingTag(JAR),
                                dependencies,
                                MavenTemplate.getPlugins(
                                        getDefaultCompilerPlugin(getVersionReference(JAVA)))
                        )
                )
                .build();
    }

    private MavenEntity corePomCode(Source source) {
        HashMap<ToolType, AbstractTool> tools = source.getTools();

        DependenciesTag dependencies = new DependenciesTag();
        IndentList<CodeEntity> dependencyList = getDependenciesFromTools(tools, API);
        dependencyList.add(getSpringBootWebStarter());
        dependencyList.add(getSpringBootTestStarter());
        dependencyList.add(getSpringBootDataJpaStarter());
        dependencyList.add(getSpringBootDevTools());
        dependencyList.add(getSpringBootActuator());
        dependencyList.add(getApiDependency(source));
        dependencyList.add(getHibernateValidator());

        dependencies.setInnerTags(dependencyList);

        return MavenEntity.builder()
                .header(POM_HEADER)
                .projectTag(
                        new ProjectTag(
                                new SimpleTag(POM_MODEL_VERSION, tools.get(MAVEN).getVersion()),
                                getParent(source),
                                new GroupIdTag(source.getGroupName()),
                                new ArtifactIdTag(source.getArtifactName() + MINUS + CORE),
                                dependencies,
                                getPlugins(
                                        getSpringBootMavenPlugin(),
                                        getDefaultCompilerPlugin(
                                                getVersionReference(JAVA),
                                                getMapstructProcessorPath(), getLombokProcessorPath())
                                )
                        )
                )
                .build();
    }
}
