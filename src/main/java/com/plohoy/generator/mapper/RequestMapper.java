package com.plohoy.generator.mapper;

import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.util.pathhelper.PathHelper;
import com.plohoy.generator.view.request.RequestEntity;
import com.plohoy.generator.view.request.RequestEntityField;
import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class RequestMapper {
    private PathHelper pathHelper = new PathHelper();

    private String HAVE_NOT_ONE_ID_MESSAGE = "Entity should have one ID field";

    public Source mapRequestToSource(SourceRequest request) {
        return Source.builder()
                .name(request.getArtifactName())
                .path(request.getSourcePath())
                .relativeRootPaths(pathHelper.getRootPathList(request))
                .corePackagePath(pathHelper.getPackagePath(request))
                .dtoPackagePath(pathHelper.getDtoPackagePath(request))
                .corePackageName(request.getGroupName() + DOT +
                        request.getArtifactName().replace(MINUS, EMPTY))
                .groupName(request.getGroupName())
                .artifactName(request.getArtifactName())
                .jdkVersion(request.getJdkVersion())
                .mainEntities(mapRequestEntitiesToSource(request.getMainEntities(), EMPTY))
                .secondaryEntities(mapRequestEntitiesToSource(request.getSecondaryEntities(), EMPTY))
                .mainDtoEntities(mapRequestEntitiesToSource(request.getMainEntities(), DTO_SUFFIX))
                .secondaryDtoEntities(mapRequestEntitiesToSource(request.getSecondaryEntities(), DTO_SUFFIX))
                .isArchive(request.isArchive())
                .sourceData(initSourceData())
                .endPoints(initEndPoints(request.getEndPointsPaths()))
                .build();
    }

    private List<ClassEntity> mapRequestEntitiesToSource(List<RequestEntity> requestEntities, String suffix) {
        List<ClassEntity> entities = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntities) {
            ClassEntity entity = ClassEntity.builder()
                    .name(requestEntity.getName() + suffix)
                    .idType(getIdFromRequestEntity(requestEntity))
                    .fields(mapRequestFieldsToSource(requestEntity.getFields()))
                    .build();

            entities.add(entity);
        }

        return entities;
    }

    private String getIdFromRequestEntity(RequestEntity requestEntity) {
        List<RequestEntityField> idFields = requestEntity.getFields()
                .stream()
                .filter(field -> ID.equals(field.getName()))
                .collect(Collectors.toList());
        if (idFields.isEmpty() || idFields.size() > 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HAVE_NOT_ONE_ID_MESSAGE);
        } else {
            return idFields.get(0).getType();
        }
    }

    private IndentList<FieldEntity> mapRequestFieldsToSource(List<RequestEntityField> requestFields) {
        List<FieldEntity> fields = new ArrayList<>();

        for (RequestEntityField requestField : requestFields) {
            FieldEntity field = FieldEntity.builder()
                    .type(requestField.getType())
                    .name(requestField.getName())
                    .build();

            fields.add(field);
        }

        return new IndentList<>(DelimiterType.SEMICOLON, true, true, fields);
    }

    private HashMap<FileType, List<AbstractSourceFile>> initSourceData() {
        HashMap<FileType, List<AbstractSourceFile>> sourceData = new HashMap<>();
        for (FileType fileType : FileType.values()) {
            sourceData.put(fileType, new ArrayList<>());
        }

        return sourceData;
    }

    private HashMap<EndPointType, String> initEndPoints(HashMap<EndPointType, String> requestEndPoints) {
        HashMap<EndPointType, String> endPoints = new HashMap<>();
        for (Map.Entry<EndPointType, String> requestEndPointDefinition : requestEndPoints.entrySet()) {
            endPoints.put(requestEndPointDefinition.getKey(), requestEndPointDefinition.getValue());
        }
        return endPoints;
    }
}
