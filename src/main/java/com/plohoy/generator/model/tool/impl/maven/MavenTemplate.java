package com.plohoy.generator.model.tool.impl.maven;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MavenTemplate {

    private final String POM_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    private final String POM_FOOTER =
            "</project>";

    private final String APP_MAIN_INFO =
            "\t<groupId></groupId>\n" +
            "\t<artifactId></artifactId>\n" +
            "\t<version>0.0.1</version>\n" +
            "\t<name></name>\n" +
            "\t<packaging></packaging>\n\n";

    private final String MODULES_SECTION =
            "\t<modules>\n" +
            "\t\t<module></module>\n" +
            "\t\t<module></module>\n" +
            "\t</modules>\n\n";

    private final String PROPERTIES_SECTION =
            "\t<properties>\n" +
            "\t\t<java.version></java.version>\n" +
            "\t</properties>\n\n";

    private final String DEPENDENCIES_SECTION =
            "\t<dependencyManagement>\n" +
            "\t\t<dependencies>\n" +
            "\t\t</dependencies>\n" +
            "\t</dependencyManagement>\n\n";

    private final String DEPENDENCY_INFO =
            "\t\t\t<dependency>\n" +
            "\t\t\t\t<groupId></groupId>\n" +
            "\t\t\t\t<artifactId></artifactId>\n" +
            "\t\t\t\t<version></version>\n" +
            "\t\t\t\t<type></type>\n" +
            "\t\t\t\t<scope></scope>\n" +
            "\t\t\t</dependency>\n";

    private final String PLUGINS_SECTION =
            "\t<buildsection>\n" +
            "\t\t<plugins>\n" +
            "\t\t</plugins>\n" +
            "\t</buildsection>\n\n";

    private final String PLUGIN_INFO =
            "\t\t\t<buildsection>\n" +
            "\t\t\t\t<groupId></groupId>\n" +
            "\t\t\t\t<artifactId></artifactId>\n" +
            "\t\t\t\t<version></version>\n" +
            "\t\t\t\t<configuration>\n" +
            "\t\t\t\t\t<source>${java.version}</source>\n" +
            "\t\t\t\t\t<target>${java.version}</target>\n" +
            "\t\t\t\t</configuration>\n" +
            "\t\t\t</buildsection>\n";

    private final String CONFIGURATION_INFO =
            "\t\t\t\t<configuration>\n" +
            "\t\t\t\t\t<source>${java.version}</source>\n" +
            "\t\t\t\t\t<target>${java.version}</target>\n" +
            "\t\t\t\t</configuration>\n";

    private final String EXECUTIONS_SECTION =
            "\t\t\t\t<configuration>\n" +
                    "\t\t\t\t\t<source>${java.version}</source>\n" +
                    "\t\t\t\t\t<target>${java.version}</target>\n" +
                    "\t\t\t\t</configuration>\n";
    private static final String PROJECT_ATTRS =
            "xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
                    "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
                    "http://maven.apache.org/xsd/maven-4.0.0.xsd\"";

    public static String getPomHeader() {
        return POM_HEADER;
    }

    public static String getPomFooter() {
        return POM_FOOTER;
    }

    public static String getAppMainInfo() {
        return APP_MAIN_INFO;
    }

    public static String getModulesSection() {
        return MODULES_SECTION;
    }

    public static String getPropertiesSection() {
        return PROPERTIES_SECTION;
    }

    public static String getDependenciesSection() {
        return DEPENDENCIES_SECTION;
    }

    public static String getDependencyInfo() {
        return DEPENDENCY_INFO;
    }

    public static String getPluginsSection() {
        return PLUGINS_SECTION;
    }

    public static String getPluginInfo() {
        return PLUGIN_INFO;
    }

    public static String getProjectAttrs() {
        return PROJECT_ATTRS;
    }
}
