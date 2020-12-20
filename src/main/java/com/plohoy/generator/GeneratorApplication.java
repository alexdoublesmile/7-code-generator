package com.plohoy.generator;

import com.plohoy.generator.controller.SourceController;
import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.entity.Entity;
import com.plohoy.generator.model.entity.Field;
import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.model.tool.*;
import com.plohoy.generator.service.SourceService;

import java.util.ArrayList;
import java.util.List;

public class GeneratorApplication {
    public static void main(String[] args) {
        SourceService service = new SourceService();
        SourceController controller = new SourceController(service);

        SourceRequest request = new SourceRequest();
        request.setArchitecture(ArchitectureType.MICROSERVICES_SIMPLE);

        List<Entity> entities = new ArrayList<Entity>();

        Entity entity = new Entity();

        entity.setName("Person");

        List<Field> fields = new ArrayList<Field>();
        Field firstNameField = new Field();
        firstNameField.setType("String");
        firstNameField.setName("firstName");

        Field lastNameField = new Field();
        lastNameField.setType("String");
        lastNameField.setName("lastName");

        Field patronymicField = new Field();
        patronymicField.setType("String");
        patronymicField.setName("patronymic");

        Field ageField = new Field();
        ageField.setType("String");
        ageField.setName("age");

        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(patronymicField);
        fields.add(ageField);

        entity.setFields(fields);

        entities.add(entity);

        request.setEntities(entities);

        List<Tool> tools = new ArrayList<Tool>();
        Tool springTool = new SpringTool("5.2.9.RELEASE");
        Tool springBootTool = new SpringBootTool("2.3.4.RELEASE");
        Tool lombokTool = new LombokTool("1.18.12");
        Tool mapstructTool = new MapstructTool("1.3.1.Final");
        Tool jacksonTool = new JacksonTool("2.11.2");
        Tool jakartaValidationTool = new JakartaValidationTool("2.0.2");
        Tool swaggerTool = new SwaggerTool("2.1.2");
        Tool springDocTool = new SpringDocTool("1.3.9");
        Tool javaDocTool = new JavaDocTool();
        Tool postgresTool = new PostgresTool();
        Tool liquibaseTool = new LiquibaseTool();
        Tool gitTool = new GitTool();
        Tool dockerTool = new DockerTool();
        Tool readMeTool = new ReadMeTool();

        tools.add(springTool);
        tools.add(springBootTool);
        tools.add(lombokTool);
        tools.add(mapstructTool);
        tools.add(jacksonTool);
        tools.add(jakartaValidationTool);
        tools.add(swaggerTool);
        tools.add(springDocTool);
        tools.add(javaDocTool);
        tools.add(postgresTool);
        tools.add(liquibaseTool);
        tools.add(gitTool);
        tools.add(dockerTool);
        tools.add(readMeTool);

        request.setTools(tools);

        request.setVersion("11");

        controller.buildSource(request);
    }
}
