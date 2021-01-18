package com.plohoy.generator;

import com.plohoy.generator.controller.SourceController;
import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.model.tool.impl.doc.JavaDocTool;
import com.plohoy.generator.model.tool.impl.docker.DockerTool;
import com.plohoy.generator.model.tool.impl.git.GitTool;
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
import com.plohoy.generator.view.request.RequestEntity;
import com.plohoy.generator.view.request.RequestEntityField;
import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
//        ctx.register(AppConfig.class);
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
                .dtoModuleExists(false)
                .archive(false)
                .mainEntities(getTestMainEntities())
                .secondaryEntities(getTestEntities())
                .tools(getTestTools())
                .endPointsPaths(getTestEntryPoint())
                .build();
    }

    private static HashMap<EndPointType, String> getTestEntryPoint() {
        HashMap<EndPointType, String> entryPoints = new HashMap<>();
        entryPoints.put(EndPointType.MAIN_END_POINT, "/person");
        entryPoints.put(EndPointType.CREATE_END_POINT, "");
        entryPoints.put(EndPointType.FIND_ALL_END_POINT, "");
        entryPoints.put(EndPointType.FIND_END_POINT, "/{id}");
        entryPoints.put(EndPointType.UPDATE_END_POINT, "/{id}");
        entryPoints.put(EndPointType.DELETE_HARD_END_POINT, "/{id}/delete_hard");
        entryPoints.put(EndPointType.DELETE_SOFT_END_POINT, "/{id}/delete_soft");

        return entryPoints;
    }

    private static List<RequestEntity> getTestMainEntities() {
        List<RequestEntity> mainEntities = new ArrayList<>();
        RequestEntity personRequestEntity = RequestEntity.builder()
                .name("Person")
                .fields(getTestPersonFields())
                .build();

        mainEntities.add(personRequestEntity);

        return mainEntities;
    }

    private static List<RequestEntity> getTestEntities() {
        List<RequestEntity> entities = new ArrayList<>();

        RequestEntity addressRequestEntity = RequestEntity.builder()
                .name("Address")
                .fields(getTestAddressFields())
                .build();

        entities.add(addressRequestEntity);

        return entities;
    }

    private static List<RequestEntityField> getTestPersonFields() {
        List<RequestEntityField> fields = new ArrayList<>();
        RequestEntityField idField = RequestEntityField.builder()
                .type("UUID")
                .name("id")
                .build();

        RequestEntityField firstNameField = RequestEntityField.builder()
                .type("String")
                .name("firstName")
                .build();

        RequestEntityField lastNameField = RequestEntityField.builder()
                .type("String")
                .name("lastName")
                .build();

        RequestEntityField patronymicField = RequestEntityField.builder()
                .type("String")
                .name("patronymic")
                .build();

        RequestEntityField ageField = RequestEntityField.builder()
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

    private static List<RequestEntityField> getTestAddressFields() {
        List<RequestEntityField> addressFields = new ArrayList<>();
        RequestEntityField idField = RequestEntityField.builder()
                .type("UUID")
                .name("id")
                .build();

        RequestEntityField countryField = RequestEntityField.builder()
                .type("String")
                .name("country")
                .build();

        RequestEntityField cityField = RequestEntityField.builder()
                .type("String")
                .name("city")
                .build();

        RequestEntityField streetField = RequestEntityField.builder()
                .type("String")
                .name("street")
                .build();

        RequestEntityField houseField = RequestEntityField.builder()
                .type("String")
                .name("house")
                .build();

        addressFields.add(idField);
        addressFields.add(countryField);
        addressFields.add(cityField);
        addressFields.add(streetField);
        addressFields.add(houseField);

        return addressFields;
    }

    private static HashMap<ToolType, AbstractTool> getTestTools() {
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
        AbstractTool postgresTool = new PostgresTool();
        AbstractTool liquibaseTool = new LiquibaseTool();
        AbstractTool gitTool = new GitTool();
        AbstractTool dockerTool = new DockerTool();
        AbstractTool readMeTool = new ReadMeTool();

        tools.put(ToolType.MAVEN, mavenTool);
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
