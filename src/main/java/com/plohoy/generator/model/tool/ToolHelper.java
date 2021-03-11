package com.plohoy.generator.model.tool;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.impl.doc.JavaDocTool;
import com.plohoy.generator.model.tool.impl.docker.DockerTool;
import com.plohoy.generator.model.tool.impl.git.GitTool;
import com.plohoy.generator.model.tool.impl.hibernate.HibernateTool;
import com.plohoy.generator.model.tool.impl.jackson.JacksonTool;
import com.plohoy.generator.model.tool.impl.liquibase.LiquibaseTool;
import com.plohoy.generator.model.tool.impl.lombok.LombokTool;
import com.plohoy.generator.model.tool.impl.mapstruct.MapstructTool;
import com.plohoy.generator.model.tool.impl.maven.MavenTool;
import com.plohoy.generator.model.tool.impl.postgres.PostgresTool;
import com.plohoy.generator.model.tool.impl.readme.ReadMeTool;
import com.plohoy.generator.model.tool.impl.spring.SpringBootTool;
import com.plohoy.generator.model.tool.impl.spring.SpringTool;
import com.plohoy.generator.model.tool.impl.swagger.SpringDocTool;
import com.plohoy.generator.model.tool.impl.swagger.SwaggerTool;
import com.plohoy.generator.model.tool.impl.validation.JakartaValidationTool;
import lombok.experimental.UtilityClass;

import java.util.HashMap;

@UtilityClass
public class ToolHelper {


    public boolean hasLombokTool(HashMap<ToolType, AbstractTool> tools) {
        return tools.values()
                .stream()
                .anyMatch(tool -> tool instanceof LombokTool);
    }

    public static HashMap<ToolType, AbstractTool> getDefaultTools() {
        HashMap<ToolType, AbstractTool> tools = new HashMap<>();
        AbstractTool mavenTool = new MavenTool("4.0.0");
        AbstractTool springTool = new SpringTool("5.2.9.RELEASE");
        AbstractTool springBootTool = new SpringBootTool("2.3.4.RELEASE");
        AbstractTool lombokTool = new LombokTool("1.18.12");
        AbstractTool mapstructTool = new MapstructTool("1.3.1.Final");
        AbstractTool jacksonTool = new JacksonTool("2.11.2");
        AbstractTool jakartaValidationTool = new JakartaValidationTool("2.0.2");
        AbstractTool swaggerTool = new SwaggerTool("2.1.2");
        AbstractTool springDocTool = new SpringDocTool("1.3.9");
        AbstractTool javaDocTool = new JavaDocTool();
        AbstractTool postgresTool = new PostgresTool("42.2.18");
        AbstractTool liquibaseTool = new LiquibaseTool(null);
        AbstractTool gitTool = new GitTool();
        AbstractTool dockerTool = new DockerTool();
        AbstractTool readMeTool = new ReadMeTool();
        AbstractTool hibernateTool = new HibernateTool();

        tools.put(ToolType.MAVEN, mavenTool);
        tools.put(ToolType.SPRING, springTool);
        tools.put(ToolType.SPRING_BOOT, springBootTool);
        tools.put(ToolType.SPRING_ACTUATOR, springBootTool);
        tools.put(ToolType.LOMBOK, lombokTool);
        tools.put(ToolType.MAPSTRUCT, mapstructTool);
        tools.put(ToolType.JACKSON, jacksonTool);
        tools.put(ToolType.JAKARTA_VALIDATION, jakartaValidationTool);
        tools.put(ToolType.SWAGGER, swaggerTool);
        tools.put(ToolType.SPRING_DOC, springDocTool);
        tools.put(ToolType.JAVA_DOC, javaDocTool);
        tools.put(ToolType.POSTGRES, postgresTool);
        tools.put(ToolType.LIQUIBASE, liquibaseTool);
        tools.put(ToolType.GIT, gitTool);
        tools.put(ToolType.DOCKER, dockerTool);
        tools.put(ToolType.READ_ME, readMeTool);
        tools.put(ToolType.HIBERNATE, hibernateTool);

        return tools;
    }
}
