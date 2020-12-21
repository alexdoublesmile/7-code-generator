package com.plohoy.generator;

import com.plohoy.generator.config.AppConfig;
import com.plohoy.generator.controller.SourceController;
import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.entity.Entity;
import com.plohoy.generator.model.entity.Field;
import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.impl.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
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
                .buildSource(getTestRequest())
                .getResponseMessage());
    }

    private static SourceRequest getTestRequest() {
        SourceRequest request = new SourceRequest();
        request.setSourceName("person-service");
        request.setPackageName("com/plohoy/personservice");

        request.setArchitecture(ArchitectureType.MICROSERVICE_SIMPLE);

        List<Entity> entities = new ArrayList<Entity>();

        Entity personEntity = new Entity();
//        Entity addressEntity = new Entity();

        personEntity.setName("Person");

        List<Field> fields = new ArrayList<Field>();
        Field idField = new Field();
        idField.setType("UUID");
        idField.setName("id");

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

        fields.add(idField);
        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(patronymicField);
        fields.add(ageField);

        personEntity.setFields(fields);


//        addressEntity.setName("Address");
//
//        List<Field> addressFields = new ArrayList<Field>();
//        Field countryField = new Field();
//        countryField.setType("String");
//        countryField.setName("country");
//
//        Field cityField = new Field();
//        cityField.setType("String");
//        cityField.setName("city");
//
//        Field streetField = new Field();
//        streetField.setType("String");
//        streetField.setName("street");
//
//        Field houseField = new Field();
//        houseField.setType("String");
//        houseField.setName("house");
//
//        addressFields.add(countryField);
//        addressFields.add(cityField);
//        addressFields.add(streetField);
//        addressFields.add(houseField);
//
//        addressEntity.setFields(addressFields);

        entities.add(personEntity);
//        entities.add(addressEntity);

        request.setEntities(entities);

        List<AbstractTool> tools = new ArrayList<AbstractTool>();
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

        return request;
    }
}
