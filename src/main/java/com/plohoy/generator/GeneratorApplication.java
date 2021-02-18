package com.plohoy.generator;

import com.plohoy.generator.controller.SourceController;
import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.ResponseCode;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
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
import com.plohoy.generator.view.request.RequestEntity;
import com.plohoy.generator.view.request.RequestEntityField;
import com.plohoy.generator.view.request.RequestEntityRelation;
import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.plohoy.generator.model.EndPointType.*;
import static com.plohoy.generator.model.codeentity.field.RelationType.*;

public class GeneratorApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
//        ctx.register(AppConfig.class);
        ctx.scan("com.plohoy.generator");
        ctx.refresh();

        test(ctx);
    }

    private static void test(AnnotationConfigApplicationContext ctx) {
        System.out.println(
                ctx.getBean(SourceController.class)
                        .buildSource(getTestRequest())
        );
    }

    private static SourceRequest getTestRequest() {
        return SourceRequest.builder()
                .author("Alex Plohoy")
                .sourcePath("../uploads/generated/")
                .groupName("com.plohoy")
                .artifactName("person-service")
                .description("Микросервис по созданию персон")
                .jdkVersion("11")
                .architecture(ArchitectureType.REST_SIMPLE)
                .dtoModuleExists(true)
                .archive(false)
                .entities(getTestEntities())
                .tools(getTestTools())
                .build();
    }

    private static List<RequestEntity> getTestEntities() {
        List<RequestEntity> entities = new ArrayList<>();

        RequestEntity personRequestEntity = RequestEntity.builder()
                .name("Person")
                .fields(getTestPersonFields())
                .description("Персона")
                .endPoints(getTestPersonEndPoints())
                .build();

        RequestEntity detailsRequestEntity = RequestEntity.builder()
                .name("PersonDetails")
                .fields(getTestDetailsFields())
                .description("Детали персоны")
                .build();

        RequestEntity passportRequestEntity = RequestEntity.builder()
                .name("Passport")
                .fields(getTestPassportFields())
//                .endPoints(getTestPassportEndPoints())
                .build();

        RequestEntity addressRequestEntity = RequestEntity.builder()
                .name("Address")
                .fields(getTestAddressFields())
                .endPoints(getTestEndPoints())
                .build();

        entities.add(personRequestEntity);
        entities.add(detailsRequestEntity);
        entities.add(passportRequestEntity);
        entities.add(addressRequestEntity);

        return entities;
    }

    private static List<RequestEntityField> getTestPersonFields() {
        List<RequestEntityField> fields = new ArrayList<>();
        RequestEntityField idField = RequestEntityField.builder()
                .type("UUID")
                .name("id")
                .description("Идентификатор персоны в БД")
                .build();

        RequestEntityField firstNameField = RequestEntityField.builder()
                .type("String")
                .name("firstName")
                .description("Имя персоны")
                .build();

        RequestEntityField lastNameField = RequestEntityField.builder()
                .type("String")
                .name("lastName")
                .description("Фамилия персоны")
                .build();

        RequestEntityField patronymicField = RequestEntityField.builder()
                .type("String")
                .name("patronymic")
                .description("Отчество персоны")
                .build();

        RequestEntityField detailsField = RequestEntityField.builder()
                .type("PersonDetails")
                .name("details")
                .description("Детали персоны")
                .relation(RequestEntityRelation.builder()
                        .relationType(ONE_TO_ONE)
                        .relationOwner(true)
                        .build())
                .build();

        RequestEntityField passportsField = RequestEntityField.builder()
                .type("Passport")
                .name("passports")
                .description("Паспорта персоны")
                .relation(RequestEntityRelation.builder()
                        .relationType(ONE_TO_MANY)
                        .build())
                .array(true)
                .build();

        RequestEntityField addressesField = RequestEntityField.builder()
                .type("Address")
                .name("addresses")
                .description("Адреса персоны")
                .relation(RequestEntityRelation.builder()
                        .relationType(MANY_TO_MANY)
                        .relationOwner(true)
                        .build())
                .array(true)
                .build();

        RequestEntityField mentorField = RequestEntityField.builder()
                .type("Person")
                .name("mentor")
                .description("Ментор")
                .relation(RequestEntityRelation.builder()
                        .relationType(ONE_TO_ONE)
                        .relationName("student")
                        .build())
                .build();

        RequestEntityField studentField = RequestEntityField.builder()
                .type("Person")
                .name("student")
                .description("Студент")
                .relation(RequestEntityRelation.builder()
                        .relationType(ONE_TO_ONE)
                        .relationOwner(true)
                        .relationName("mentor")
                        .build())
                .build();

        RequestEntityField subscriptionsField = RequestEntityField.builder()
                .type("Person")
                .name("subscriptions")
                .description("Подписки")
                .relation(RequestEntityRelation.builder()
                        .relationType(MANY_TO_MANY)
                        .relationOwner(true)
                        .relationName("subscribers")
                        .build())
                .array(true)
                .build();

        RequestEntityField subscribersField = RequestEntityField.builder()
                .type("Person")
                .name("subscribers")
                .description("Подписчики")
                .relation(RequestEntityRelation.builder()
                        .relationType(MANY_TO_MANY)
                        .relationName("subscriptions")
                        .build())
                .array(true)
                .build();

        RequestEntityField childrenField = RequestEntityField.builder()
                .type("Person")
                .name("childs")
                .description("Потомки")
                .relation(RequestEntityRelation.builder()
                        .relationType(ONE_TO_MANY)
                        .relationName("parent")
                        .build())
                .array(true)
                .build();

        RequestEntityField parentField = RequestEntityField.builder()
                .type("Person")
                .name("parent")
                .description("Предок")
                .relation(RequestEntityRelation.builder()
                        .relationType(MANY_TO_ONE)
                        .relationName("childs")
                        .relationOwner(true)
                        .build())
                .build();

        fields.add(idField);
        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(patronymicField);
        fields.add(detailsField);
        fields.add(passportsField);
        fields.add(addressesField);
        fields.add(childrenField);
        fields.add(parentField);
        fields.add(mentorField);
        fields.add(studentField);
        fields.add(subscribersField);
        fields.add(subscriptionsField);

        return fields;
    }

    private static List<RequestEntityField> getTestDetailsFields() {
        List<RequestEntityField> passportFields = new ArrayList<>();
        RequestEntityField idField = RequestEntityField.builder()
                .type("UUID")
                .name("id")
                .description("Идентификатор записей о деталях персоны в БД")
                .build();

        RequestEntityField sexField = RequestEntityField.builder()
                .type("String")
                .name("sex")
                .description("Пол")
                .build();

        RequestEntityField weightField = RequestEntityField.builder()
                .type("String")
                .name("weight")
                .description("Вес")
                .build();

        RequestEntityField heightField = RequestEntityField.builder()
                .type("String")
                .name("height")
                .description("Рост")
                .build();

        RequestEntityField ageField = RequestEntityField.builder()
                .type("String")
                .name("age")
                .description("Возраст")
                .build();

        RequestEntityField strengthField = RequestEntityField.builder()
                .type("String")
                .name("strength")
                .description("Уровень силы")
                .build();

        RequestEntityField personField = RequestEntityField.builder()
                .type("Person")
                .name("owner")
                .description("Сущность")
                .relation(RequestEntityRelation.builder()
                        .relationType(ONE_TO_ONE)
                        .build())
                .build();

        passportFields.add(idField);
        passportFields.add(sexField);
        passportFields.add(weightField);
        passportFields.add(heightField);
        passportFields.add(ageField);
        passportFields.add(strengthField);
        passportFields.add(personField);

        return passportFields;
    }

    private static List<RequestEntityField> getTestPassportFields() {
        List<RequestEntityField> carFields = new ArrayList<>();
        RequestEntityField idField = RequestEntityField.builder()
                .type("UUID")
                .name("id")
                .description("Идентификатор паспорта в БД")
                .build();

        RequestEntityField typeField = RequestEntityField.builder()
                .type("String")
                .name("type")
                .description("Тип")
                .build();

        RequestEntityField colorField = RequestEntityField.builder()
                .type("String")
                .name("color")
                .description("Цвет")
                .build();

        RequestEntityField regNumberField = RequestEntityField.builder()
                .type("String")
                .name("registrationNumber")
                .description("Регистрационный номер")
                .build();

        RequestEntityField personField = RequestEntityField.builder()
                .type("Person")
                .name("owner")
                .description("Владелец паспорта")
                .relation(RequestEntityRelation.builder()
                        .relationType(MANY_TO_ONE)
                        .relationOwner(true)
                        .build())
                .build();

        carFields.add(idField);
        carFields.add(typeField);
        carFields.add(colorField);
        carFields.add(regNumberField);
        carFields.add(personField);

        return carFields;
    }

    private static List<RequestEntityField> getTestAddressFields() {
        List<RequestEntityField> addressFields = new ArrayList<>();
        RequestEntityField idField = RequestEntityField.builder()
                .type("UUID")
                .name("id")
                .description("Идентификатор адреса в БД")
                .build();

        RequestEntityField countryField = RequestEntityField.builder()
                .type("String")
                .name("country")
                .description("Страна")
                .build();

        RequestEntityField cityField = RequestEntityField.builder()
                .type("String")
                .name("city")
                .description("Город")
                .build();

        RequestEntityField streetField = RequestEntityField.builder()
                .type("String")
                .name("street")
                .description("Улица")
                .build();

        RequestEntityField houseField = RequestEntityField.builder()
                .type("String")
                .name("house")
                .description("Дом")
                .build();

        RequestEntityField personsField = RequestEntityField.builder()
                .type("Person")
                .name("persons")
                .description("Проживающие персоны")
                .relation(RequestEntityRelation.builder()
                        .relationType(MANY_TO_MANY)
                        .build())
                .array(true)
                .build();

        addressFields.add(idField);
        addressFields.add(countryField);
        addressFields.add(cityField);
        addressFields.add(streetField);
        addressFields.add(houseField);
        addressFields.add(personsField);

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
        AbstractTool postgresTool = new PostgresTool("42.2.18");
        AbstractTool liquibaseTool = new LiquibaseTool(null);
        AbstractTool gitTool = new GitTool();
        AbstractTool dockerTool = new DockerTool();
        AbstractTool readMeTool = new ReadMeTool();
        AbstractTool hibernateTool = new HibernateTool();

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
        tools.put(ToolType.HIBERNATE, hibernateTool);

        return tools;
    }

    private static List<EndPoint> getTestPersonEndPoints() {

        List<EndPoint> endPoints = new ArrayList<>();
        endPoints.add(
                EndPoint.builder()
                        .type(CONTROLLER_END_POINT)
                        .path("/person")
                        .description("Персона")
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(CREATE_END_POINT)
                        .path("")
                        .description("Создание записи о новой персоне")
                        .responseCodes(
                                Arrays.asList(
                                        ResponseCode.builder()
                                                .code("200")
                                                .description("Персона успешно создана")
                                                .build(),
                                        ResponseCode.builder()
                                                .code("500")
                                                .description("Ошибка сервера")
                                                .build()
                                )
                        )
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(FIND_ALL_END_POINT)
                        .path("")
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(FIND_END_POINT)
                        .path("/{id}")
                        .description("Получение из БД записи \\\"Персона\\\" по идентификатору")
                        .responseCodes(
                                Arrays.asList(
                                        ResponseCode.builder()
                                                .code("200")
                                                .build(),
                                        ResponseCode.builder()
                                                .code("404")
                                                .build(),
                                        ResponseCode.builder()
                                                .code("500")
                                                .build()
                                )
                        )
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(UPDATE_END_POINT)
                        .path("/{id}")
                        .description("Редактирование записи \\\"Персона\\\" в БД по идентификатору")
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(DELETE_HARDLY_END_POINT)
                        .path("/{id}/delete_hard")
                        .description("Полное удаление записи из БД по идентификатору")
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(DELETE_SOFTLY_END_POINT)
                        .path("/{id}/delete_soft")
                        .description("Програмное удаление записи из БД по идентификатору (установка флага deleted)")
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(RESTORE_END_POINT)
                        .path("/{id}/restore")
                        .description("Восстановление записи в БД по идентификатору")
                        .build());

        return endPoints;

    }

    private static List<EndPoint> getTestEndPoints() {

        List<EndPoint> endPoints = new ArrayList<>();
        endPoints.add(EndPoint.builder()
                        .type(CONTROLLER_END_POINT)
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(CREATE_END_POINT)
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(FIND_ALL_END_POINT)
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(FIND_END_POINT)
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(UPDATE_END_POINT)
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(DELETE_HARDLY_END_POINT)
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(DELETE_SOFTLY_END_POINT)
                        .build());
        endPoints.add(
                EndPoint.builder()
                        .type(RESTORE_END_POINT)
                        .build());

        return endPoints;

    }
}
