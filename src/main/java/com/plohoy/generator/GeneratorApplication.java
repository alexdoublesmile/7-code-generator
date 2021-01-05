package com.plohoy.generator;

import com.plohoy.generator.config.AppConfig;
import com.plohoy.generator.controller.SourceController;
import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.model.tool.impl.doc.JavaDocTool;
import com.plohoy.generator.model.tool.impl.docker.DockerTool;
import com.plohoy.generator.model.tool.impl.git.GitTool;
import com.plohoy.generator.model.tool.impl.jackson.JacksonTool;
import com.plohoy.generator.model.tool.impl.liquibase.LiquibaseTool;
import com.plohoy.generator.model.tool.impl.lombok.LombokTool;
import com.plohoy.generator.model.tool.impl.mapstruct.MapstructTool;
import com.plohoy.generator.model.tool.impl.postgres.PostgresTool;
import com.plohoy.generator.model.tool.impl.readme.ReadMeTool;
import com.plohoy.generator.model.tool.impl.spring.SpringBootTool;
import com.plohoy.generator.model.tool.impl.spring.SpringTool;
import com.plohoy.generator.model.tool.impl.swagger.SpringDocTool;
import com.plohoy.generator.model.tool.impl.swagger.SwaggerTool;
import com.plohoy.generator.model.tool.impl.validation.JakartaValidationTool;
import com.plohoy.generator.view.request.Entity;
import com.plohoy.generator.view.request.Field;
import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.scan("com.plohoy.generator");
        ctx.refresh();

        SourceController controller = (SourceController) ctx.getBean("controller");

        System.out.println(controller
                .buildSource(getTestRequest()).getStatusCode());
    }

    private static SourceRequest getTestRequest() {
        return SourceRequest.builder()
                .sourcePath("../uploads/generated/")
                .groupName("com.plohoy")
                .artifactName("person-service")
                .jdkVersion("11")
                .architecture(ArchitectureType.REST_SIMPLE)
                .isMicroService(true)
                .isArchive(false)
                .mainEntities(getTestMainEntities())
                .secondaryEntities(getTestEntities())
                .tools(getTestTools())
                .build();
    }

    private static List<Entity> getTestMainEntities() {
        List<Entity> mainEntities = new ArrayList<>();
        Entity personEntity = Entity.builder()
                .name("Person")
                .fields(getTestPersonFields())
                .build();

        mainEntities.add(personEntity);

        return mainEntities;
    }

    private static List<Entity> getTestEntities() {
        List<Entity> entities = new ArrayList<>();

        Entity addressEntity = Entity.builder()
                .name("Address")
                .fields(getTestAddressFields())
                .build();

        entities.add(addressEntity);

        return entities;
    }

    private static List<Field> getTestPersonFields() {
        List<Field> fields = new ArrayList<>();
        Field idField = Field.builder()
                .type("UUID")
                .name("id")
                .build();

        Field firstNameField = Field.builder()
                .type("String")
                .name("firstName")
                .build();

        Field lastNameField = Field.builder()
                .type("String")
                .name("lastName")
                .build();

        Field patronymicField = Field.builder()
                .type("String")
                .name("patronymic")
                .build();

        Field ageField = Field.builder()
                .type("String")
                .name("age")
                .build();

        fields.add(idField);
        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(patronymicField);
        fields.add(ageField);

        return fields;
    }

    private static List<Field> getTestAddressFields() {
        List<Field> addressFields = new ArrayList<>();

        Field countryField = Field.builder()
                .type("String")
                .name("country")
                .build();

        Field cityField = Field.builder()
                .type("String")
                .name("city")
                .build();

        Field streetField = Field.builder()
                .type("String")
                .name("street")
                .build();

        Field houseField = Field.builder()
                .type("String")
                .name("house")
                .build();

        addressFields.add(countryField);
        addressFields.add(cityField);
        addressFields.add(streetField);
        addressFields.add(houseField);

        return addressFields;
    }

    private static HashMap<ToolType, AbstractTool> getTestTools() {
        HashMap<ToolType, AbstractTool> tools = new HashMap<>();
        AbstractTool springTool = new SpringTool("5.2.9.RELEASE");
        AbstractTool springBootTool = new SpringBootTool("2.3.4.RELEASE");
        AbstractTool lombokTool = new LombokTool("1.18.12");
        AbstractTool mapstructTool = new MapstructTool("1.3.1.Final");
        AbstractTool jacksonTool = new JacksonTool("2.11.2");
        AbstractTool jakartaValidationTool = new JakartaValidationTool("2.0.2");
        AbstractTool swaggerTool = new SwaggerTool("2.1.2");
        AbstractTool springDocTool = new SpringDocTool("1.3.9");
        AbstractTool javaDocTool = new JavaDocTool();
        AbstractTool postgresTool = new PostgresTool();
        AbstractTool liquibaseTool = new LiquibaseTool();
        AbstractTool gitTool = new GitTool();
        AbstractTool dockerTool = new DockerTool();
        AbstractTool readMeTool = new ReadMeTool();

        tools.put(ToolType.SPRING, springTool);
        tools.put(ToolType.SPRING_BOOT, springBootTool);
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

        return tools;
    }
}
