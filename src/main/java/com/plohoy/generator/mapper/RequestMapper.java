package com.plohoy.generator.mapper;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.field.FieldRelation;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolHelper;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.util.RequestHelper;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.util.pathhelper.PathHelper;
import com.plohoy.generator.view.request.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class RequestMapper {
    private PathHelper pathHelper = new PathHelper();

    private String HAVE_NOT_ONE_ID_MESSAGE = "Entity should have one ID field";

    public Source mapRequestToSource(SourceRequest request) {
        List<RequestEntity> requestEntities = request.getEntities();

        flagFieldsAsEntityIfNeed(requestEntities);

        return Source.builder()
                .author(request.getAuthor())
                .description(request.getDescription())
                .path(request.getSourcePath())
                .groupName(request.getGroupName())
                .artifactName(request.getArtifactName())
                .relativeRootPaths(pathHelper.getRootPathList(request))
                .corePackagePath(pathHelper.getPackagePath(request))
                .dtoPackagePath(pathHelper.getDtoPackagePath(request))
                .resourcePath(pathHelper.getResourcePath(request))
                .corePackageName(buildCorePackageName(request))
                .jdkVersion(request.getJdkVersion())
                .entities(mapRequestEntitiesToSource(requestEntities))
                .dtoEntities(mapRequestDtoToSource(requestEntities))
                .dtoModuleExists(request.isDtoModuleExists())
                .archive(request.isArchive())
                .sourceData(initSourceData())
                .tools(request.getTools())
                .build();
    }

    private void flagFieldsAsEntityIfNeed(List<RequestEntity> requestEntities) {
        List<String> entityNames = requestEntities
                .stream()
                .map(e -> e.getName())
                .collect(Collectors.toList());

        for (RequestEntity requestEntity : requestEntities) {
            requestEntity.getFields()
                    .stream()
                    .forEach(field -> {
                        if (entityNames.contains(field.getType())) {
                            field.setEntity(true);
                        }});
        }
    }

    private String buildCorePackageName(SourceRequest request) {
        return request.getGroupName() + DOT +
                request.getArtifactName().replace(MINUS, EMPTY);
    }

    private List<ClassEntity> mapRequestEntitiesToSource(List<RequestEntity> requestEntities) {
        List<ClassEntity> entities = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntities) {
            ClassEntity entity = ClassEntity.builder()
                    .name(requestEntity.getName())
                    .idType(getIdTypeFromRequestEntity(requestEntity))
                    .fields(mapRequestFieldsToSource(requestEntity, false))
                    .endPoints(requestEntity.getEndPoints())
                    .schemaDescription(requestEntity.getDescription())
                    .pageable(requestEntity.isPageable())
                    .build();

            entities.add(entity);
        }

        return entities;
    }

    private List<ClassEntity> mapRequestDtoToSource(List<RequestEntity> requestEntities) {
        List<ClassEntity> dtoEntities = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntities) {

            ClassEntity dtoEntity = ClassEntity.builder()
                    .name(requestEntity.getName() + DTO_SUFFIX)
                    .idType(getIdTypeFromRequestEntity(requestEntity))
                    .fields(mapRequestFieldsToSource(requestEntity, true))
                    .endPoints(requestEntity.getEndPoints())
//                    .restEntity(Objects.nonNull(requestEntity.getEndPoints()))
                    .schemaDescription(requestEntity.getDescription())
                    .pageable(requestEntity.isPageable())
                    .build();

            dtoEntities.add(dtoEntity);
        }

        return dtoEntities;
    }

    private IndentList<FieldEntity> mapRequestFieldsToSource(RequestEntity requestEntity, boolean isDto) {
        List<FieldEntity> fields = new ArrayList<>();

        List<RequestEntityField> requestFields = Optional
                .ofNullable(
                        isDto ? requestEntity.getDtoFields() : null)
                .orElseGet(() -> requestEntity.getFields());

        for (RequestEntityField requestField : requestFields) {
            String fieldType = requestField.getType();

            if (needMapFieldTypesForDto(isDto, requestEntity)) {
                fieldType += mapFieldTypeToDto(requestField);
            }

            FieldEntity field = FieldEntity.builder()
                    .type(fieldType)
                    .name(requestField.getName())
                    .schemaDescription(requestField.getDescription())
                    .relation(mapRequestRelationToFieldRelation(requestField.getRelation()))
                    .array(requestField.isArray())
                    .filter(requestField.isFilter())
                    .validationList(requestField.getValidationList())
                    .build();

            fields.add(field);
        }

        return new IndentList<>(DelimiterType.SEMICOLON, true, true, fields);
    }

    private String mapFieldTypeToDto(RequestEntityField requestField) {
        return requestField.isEntity() ? DTO_SUFFIX : EMPTY;
    }

    private boolean needMapFieldTypesForDto(boolean isDto, RequestEntity requestEntity) {
        return isDto && Objects.isNull(requestEntity.getDtoFields());
    }

    private String getIdTypeFromRequestEntity(RequestEntity requestEntity) {
        List<RequestEntityField> idFields = requestEntity.getFields()
                .stream()
                .filter(field -> ID.equals(field.getName()))
                .collect(Collectors.toList());

        if (needUniqueIdField(idFields)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HAVE_NOT_ONE_ID_MESSAGE);
        } else {
            return idFields.get(0).getType();
        }
    }

    private boolean needUniqueIdField(List<RequestEntityField> idFields) {
        return idFields.isEmpty() || idFields.size() > 1;
    }

    private FieldRelation mapRequestRelationToFieldRelation(RequestEntityRelation requestRelation) {
        FieldRelation fieldRelation = null;

        if (Objects.nonNull(requestRelation)) {
            EnumerationList<PropertyEntity> relationProperties = null;

            if (Objects.nonNull(requestRelation.getProperties())) {
                relationProperties = new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                        requestRelation.getProperties());
            }

            fieldRelation = FieldRelation.builder()
                    .relationType(requestRelation.getRelationType())
                    .relationName(requestRelation.getRelationName())
                    .relationOwner(requestRelation.isRelationOwner())
                    .properties(relationProperties)
                    .build();
        }

        return fieldRelation;
    }

    private HashMap<FileType, List<AbstractSourceFile>> initSourceData() {
        HashMap<FileType, List<AbstractSourceFile>> sourceData = new HashMap<>();
        for (FileType fileType : FileType.values()) {
            sourceData.put(fileType, new ArrayList<>());
        }

        return sourceData;
    }
}
