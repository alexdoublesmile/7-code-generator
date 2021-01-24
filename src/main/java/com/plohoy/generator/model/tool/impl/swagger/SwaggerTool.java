package com.plohoy.generator.model.tool.impl.swagger;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.ResponseCode;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.ArgumentAnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ImportEntity;
import com.plohoy.generator.model.codeentity.method.ArgumentEntity;
import com.plohoy.generator.model.codeentity.method.MethodEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.model.file.FileType.DTO;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.DTO_SUFFIX;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.ID;

public class SwaggerTool extends AbstractTool {

    private static final String DEFAULT_GROUP_ID = "io.swagger.core.v3";
    private static final String DEFAULT_ARTIFACT_ID = "swagger-annotations";
    private static final String SCOPE = "";

    public SwaggerTool(String version) {
        super(version, DEFAULT_GROUP_ID, DEFAULT_ARTIFACT_ID, SCOPE,
                true);
    }

    public SwaggerTool() {
    }

    public Source generateCode(Source source) {
        List<AbstractSourceFile> controllerFiles = source.getSourceData().get(FileType.CONTROLLER);
        List<AbstractSourceFile> dtoEntityFiles = source.getSourceData().get(DTO);

        for (AbstractSourceFile<ClassEntity> controllerFile : controllerFiles) {
            ClassEntity controller = controllerFile.getData();

            controller.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.annotations.Operation").build().setParentEntity(controller));
            controller.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.annotations.Parameter").build().setParentEntity(controller));
            controller.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.annotations.responses.ApiResponse").build().setParentEntity(controller));
            controller.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.annotations.responses.ApiResponses").build().setParentEntity(controller));
            controller.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.annotations.tags.Tag").build().setParentEntity(controller));

            if (Objects.nonNull(controller.getSchemaDescription())) {
                controller.getAnnotations().add(
                        AnnotationEntity.builder()
                                .name("Tag")
                                .property(PropertyEntity.builder()
                                        .name("name")
                                        .quotedValue(controller.getSchemaDescription())
                                        .build())
                                .build()
                                .setParentEntity(controller)
                );
            }

            for (MethodEntity method : controller.getMethods()) {
                Stream.of(
                        getOperationByEndpointType(method, controller.getSchemaDescription()),
                        getResponseCodesByEndpointType(method)
                ).forEach(annotation ->
                        method.getAnnotations().add(
                                annotation.setParentEntity(method)));

                setParameterDescriptions(method);
            }
        }

        for (AbstractSourceFile<ClassEntity> dtoEntityFile : dtoEntityFiles) {
            ClassEntity dtoEntity = dtoEntityFile.getData();

            dtoEntity.getImports().add(ImportEntity.builder().value("io.swagger.v3.oas.annotations.media.Schema").build().setParentEntity(dtoEntity));

            if (Objects.nonNull(dtoEntity.getSchemaDescription())) {
                dtoEntity.getAnnotations().add(
                        AnnotationEntity.builder()
                                .name("Schema")
                                .property(PropertyEntity.builder()
                                        .name("description")
                                        .quotedValue(dtoEntity.getSchemaDescription())
                                        .build())
                                .build()
                                .setParentEntity(dtoEntity)
                );
            }

            dtoEntity.getFields()
                    .stream()
                    .forEach(field -> {

                        if (Objects.nonNull(field.getSchemaDescription())) {
                            List<AnnotationEntity> oldAnnotations = Optional
                                    .ofNullable(field.getAnnotations())
                                    .orElseGet(() -> new IndentList<AnnotationEntity>())
                                    .stream()
                                    .collect(Collectors.toList());

                            List<AnnotationEntity> swaggerAnnotations = Arrays.asList(
                                    AnnotationEntity.builder()
                                            .name("Schema")
                                            .property(PropertyEntity.builder()
                                                    .name("description")
                                                    .quotedValue(field.getSchemaDescription())
                                                    .build())
                                            .build()
                                            .setParentEntity(field));

                            field.setAnnotations(new IndentList<AnnotationEntity>(DelimiterType.NONE, true,
                                    Stream.of(oldAnnotations, swaggerAnnotations)
                                            .flatMap(Collection::stream)
                                            .collect(Collectors.toList())));
                        }
                    });
        }

        return source;
    }

    private void setParameterDescriptions(MethodEntity method) {
        switch (method.getEndPoint().getType()) {
            case CREATE_END_POINT: addDescriptionToArg(method.getArgs().stream().filter(arg -> DTO_SUFFIX.toLowerCase().equals(arg.getName())).collect(Collectors.toList()));
                break;
            case FIND_END_POINT: addDescriptionToArg(method.getArgs().stream().filter(arg -> ID.equals(arg.getName())).collect(Collectors.toList()));
                break;
            case UPDATE_END_POINT: addDescriptionToArg(method.getArgs().stream().filter(arg -> DTO_SUFFIX.toLowerCase().equals(arg.getName()) || ID.equals(arg.getName())).collect(Collectors.toList()));
                break;
            case DELETE_SOFT_END_POINT: addDescriptionToArg(method.getArgs().stream().filter(arg -> ID.equals(arg.getName())).collect(Collectors.toList()));
                break;
            case DELETE_HARD_END_POINT: addDescriptionToArg(method.getArgs().stream().filter(arg -> ID.equals(arg.getName())).collect(Collectors.toList()));
        }
    }

    private void addDescriptionToArg(List<ArgumentEntity> args) {
        for (ArgumentEntity arg : args) {
            if (Objects.isNull(arg.getAnnotations())) {
                arg.setAnnotations(new EnumerationList<ArgumentAnnotationEntity>());
            }

            arg.getAnnotations().add(
                    ArgumentAnnotationEntity.builder()
                            .name("Parameter")
                            .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                    PropertyEntity.builder()
                                            .name("required")
                                            .simpleValue("true")
                                            .build()))
                            .build()
                            .setParentEntity(arg)
            );
        }
    }

    private List<ImportEntity> getSwaggerImports() {
        return null;
    }

    private AnnotationEntity getOperationByEndpointType(MethodEntity method, String entityName) {
        EndPoint endPoint = method.getEndPoint();
        String operationDescription = Optional.ofNullable(endPoint.getDescription())
                .orElseGet(() ->
                        String.format("%s записи \\\"%s\\\"",
                                getDefaultDescriptionByEndPointType(endPoint.getType()),
                                entityName));

        return AnnotationEntity.builder()
                .name("Operation")
                .property(PropertyEntity.builder()
                                .name("summary")
                                .quotedValue(operationDescription)
                                .build()
                )
                .build();
    }

    private String getDefaultDescriptionByEndPointType(EndPointType endPointType) {
        switch(endPointType) {
            case CREATE_END_POINT: return "Создание в БД";
            case FIND_END_POINT: return "Получение по идентификатору из БД";
            case FIND_ALL_END_POINT: return "Получение полного списка из БД";
            case UPDATE_END_POINT: return "Редактирование по идентификатору в БД";
            case DELETE_SOFT_END_POINT: return "Программное удаление по идентификатору (установка флага deleted)";
            case DELETE_HARD_END_POINT: return "Фактическое удаление из БД по идентификатору";
            default: return null;
        }
    }

    private AnnotationEntity getResponseCodesByEndpointType(MethodEntity method) {
        EndPoint endPoint = method.getEndPoint();
        List<AnnotationEntity> responseAnnotationList = new ArrayList();

        List<ResponseCode> responseCodes = Optional.ofNullable(endPoint.getResponseCodes())
                .orElseGet(() -> getDefaultResponseCodesByEndpointType(endPoint.getType()));

        responseCodes.stream()
                .forEach(responseCode ->
                        responseAnnotationList.add(
                                AnnotationEntity.builder()
                                        .name("ApiResponse")
                                        .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                                PropertyEntity.builder()
                                                        .name("responseCode")
                                                        .quotedValue(responseCode.getCode())
                                                        .build(),
                                                PropertyEntity.builder()
                                                        .name("description")
                                                        .quotedValue(Optional.ofNullable(responseCode.getDescription())
                                                                .orElseGet(() -> getDefaultCodeDescription(responseCode.getCode())))
                                                        .build()
                                        ))
                                        .build()
                        )
                );


        return AnnotationEntity.builder()
                .name("ApiResponses")
                .property(PropertyEntity.builder()
                        .name("value")
                        .annotationList(
                                new IndentList<>(DelimiterType.COMMA, false,
                                        responseAnnotationList
                                )
                        )
                        .build()
                )
                .build();
    }

    private List<ResponseCode> getDefaultResponseCodesByEndpointType(EndPointType endPointType) {
        List<ResponseCode> responseCodes = null;
        switch (endPointType) {
            case CREATE_END_POINT: responseCodes = getMinimalResponseCodeSet();
            case FIND_END_POINT: responseCodes = getStandartResponseCodeSet();
            case FIND_ALL_END_POINT: responseCodes = getMinimalResponseCodeSet();
            case UPDATE_END_POINT: responseCodes = getStandartResponseCodeSet();
            case DELETE_SOFT_END_POINT: responseCodes = getStandartResponseCodeSet();
            case DELETE_HARD_END_POINT: responseCodes = getStandartResponseCodeSet();
        }
        return responseCodes;
    }

    private List<ResponseCode> getStandartResponseCodeSet() {
        List<ResponseCode> codeList = getMinimalResponseCodeSet();
        codeList.add(ResponseCode.builder().code("404").build());

        return codeList;
    }

    private List<ResponseCode> getMinimalResponseCodeSet() {
        List<ResponseCode> codeList = new ArrayList<>();
        codeList.add(ResponseCode.builder().code("200").build());
        codeList.add(ResponseCode.builder().code("500").build());

        return codeList;
    }

    private String getDefaultCodeDescription(String code) {
        switch (code) {
            case "200": return "Операция прошла успешно";
            case "201": return "Запись успешно создана";
            case "400": return "Неверный формат ввода данных";
            case "403": return "Доступ к ресурсу с текущими правами запрещен";
            case "404": return "Запись не найдена";
            case "500": return "Неизвестная ошибка сервера";
            default: return null;
        }
    }
}
