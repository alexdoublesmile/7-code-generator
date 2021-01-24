package com.plohoy.generator.model.tool.impl.maven;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.model.tool.impl.maven.tag.*;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.experimental.UtilityClass;

import java.util.*;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@UtilityClass
public class MavenTemplate {

    public static final String JAR = "jar";
    public static final String POM = "pom";
    public static final String CORE = "core";
    public static final String API = "api";
    public static final String POM_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    public static final String POM_MODEL_VERSION = "modelVersion";

    public static final String PROJECT_ATTRS =
            "xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
                    "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
                    "http://maven.apache.org/xsd/maven-4.0.0.xsd\"";

    public static String getVersionReference(String propertyName) {
        return String.format("${%s}", getVersionName(propertyName));
    }

    public static String getVersionName(String propertyName) {
        return String.format("%s.version", propertyName.toLowerCase());
    }

    public static ParentTag getSpringBootParent(String springBootVersion) {
        return new ParentTag(
                new GroupIdTag(SPRING_MAIN_PACKAGE),
                new ArtifactIdTag("spring-boot-starter-parent"),
                new VersionTag(springBootVersion)
        );
    }

    public static CodeEntity getDefaultCompilerPlugin(String javaVersion, CodeEntity ... annotationProcessorPaths) {
        SimpleTag annotationProcessorTag = null;

        if (annotationProcessorPaths.length > 0) {
            IndentList<CodeEntity> annotationProcessorPathsList = new IndentList<>();
            for (CodeEntity processorPath : annotationProcessorPaths) {
                annotationProcessorPathsList.add(processorPath);
            }

            annotationProcessorTag = new SimpleTag("annotationProcessorPaths");
            annotationProcessorTag.setInnerTags(annotationProcessorPathsList);
        }

        return new PluginTag(
                    new GroupIdTag("org.apache.maven.plugins"),
                    new ArtifactIdTag("maven-compiler-plugin"),
                    new VersionTag("3.8.1"),
                    new SimpleTag(
                            "configuration",
                            new SimpleTag("source", javaVersion),
                            new SimpleTag("target", javaVersion),
                            annotationProcessorTag
                    ));
    }

    public static CodeEntity getLombokProcessorPath() {
        return new SimpleTag(
                "path",
                new GroupIdTag("org.projectlombok"),
                new ArtifactIdTag("lombok"),
                new VersionTag("${lombok.version}")
        );
    }

    public static CodeEntity getMapstructProcessorPath() {
        return new SimpleTag(
                "path",
                new GroupIdTag("org.mapstruct"),
                new ArtifactIdTag("mapstruct-processor"),
                new VersionTag("${mapstruct.version}")
        );
    }

    public static CodeEntity getPlugins(CodeEntity ... plugins) {
        PluginsTag pluginsTag = new PluginsTag();

        IndentList pluginsList = new IndentList();
        for (CodeEntity plugin : plugins) {
            pluginsList.add(plugin);
        }

        pluginsTag.setInnerTags(pluginsList);

        return new BuildTag(pluginsTag);
    }

    public static CodeEntity getSpringBootMavenPlugin() {
        return new PluginTag(
                new GroupIdTag("org.springframework.boot"),
                new ArtifactIdTag("spring-boot-maven-plugin")
        );
    }

    public static CodeEntity getSpringBootWebStarter() {
        return new DependencyTag(
                new GroupIdTag("org.springframework.boot"),
                new ArtifactIdTag("spring-boot-starter-web")
        );
    }

    public static CodeEntity getSpringBootTestStarter() {
        return new DependencyTag(
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
        );
    }

    public static CodeEntity getSpringBootDataJpaStarter() {
        return new DependencyTag(
                new GroupIdTag("org.springframework.boot"),
                new ArtifactIdTag("spring-boot-starter-data-jpa")
        );
    }

    public static CodeEntity getHibernateValidator() {
        return new DependencyTag(
                new GroupIdTag("org.hibernate.validator"),
                new ArtifactIdTag("hibernate-validator"),
                new VersionTag("${hibernate_validator.version}")
        );
    }

    public static IndentList<CodeEntity> getPropertiesFromTools(HashMap<ToolType, AbstractTool> tools) {
        IndentList<CodeEntity> propertyList = new IndentList<CodeEntity>();

        for (Map.Entry<ToolType, AbstractTool> tool : tools.entrySet()) {
            if (Objects.nonNull(tool.getValue().getVersion())) {
                propertyList.add(new PropertyTag(
                        tool.getKey().name().toLowerCase(),
                        tool.getValue().getVersion()
                ));
            }
        }
        return propertyList;
    }

    public static IndentList<CodeEntity> getDependenciesFromTools(HashMap<ToolType, AbstractTool> tools, String excludeDependencies) {
        IndentList<CodeEntity> dependencyList = new IndentList<CodeEntity>();

        for (Map.Entry<ToolType, AbstractTool> toolDefinition : tools.entrySet()) {
            AbstractTool tool = toolDefinition.getValue();

            if (tool.isDependency() && !tool.getToolType().equals(excludeDependencies)) {
                dependencyList.add(new DependencyTag(
                        new GroupIdTag(tool.getDefaultGroupId()),
                        new ArtifactIdTag(tool.getDefaultArtifactId()),
                        new VersionTag(MavenTemplate.getVersionReference(toolDefinition.getKey().name()))
                ));
            }
        }
        return dependencyList;
    }

    public static IndentList<CodeEntity> getDependenciesFromTools(HashMap<ToolType, AbstractTool> tools) {
        return getDependenciesFromTools(tools, null);
    }

    public static CodeEntity getSpringBootDependencies(String springBootVersion) {
        return new SimpleTag("dependencyManagement",
                new DependenciesTag(
                        new DependencyTag(
                                new GroupIdTag(SPRING_BOOT_MAIN_PACKAGE),
                                new ArtifactIdTag("spring-boot-dependencies"),
                                new VersionTag(springBootVersion),
                                new SimpleTag("type", POM),
                                new ScopeTag("import")
                        ))
        );
    }

    public static CodeEntity getParent(Source source) {
        return new ParentTag(
                    new GroupIdTag(source.getGroupName()),
                    new ArtifactIdTag(source.getArtifactName()),
                    new VersionTag(DEFAULT_VERSION)
        );
    }

    public static CodeEntity getApiDependencies(Source source) {
        return null;
    }

    public static CodeEntity getSpringBootDevTools() {
        return new DependencyTag(
                new GroupIdTag("org.springframework.boot"),
                new ArtifactIdTag("spring-boot-devtools")
        );
    }

    public static CodeEntity getSpringBootActuator() {
        return new DependencyTag(
                new GroupIdTag("org.springframework.boot"),
                new ArtifactIdTag("spring-boot-starter-actuator")
        );
    }

    public static CodeEntity getApiDependency(Source source) {
        return new DependencyTag(
                new GroupIdTag(source.getGroupName()),
                new ArtifactIdTag(source.getArtifactName() + MINUS + API),
                new VersionTag(getAppVersionReference())
        );
    }

    public static String getAppVersionReference() {
        return "${version}";
    }
}
