package com.plohoy.generator.mapper;

import com.plohoy.generator.model.EntryPointType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.ClassEntity;
import com.plohoy.generator.model.codeentity.FieldEntity;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.util.pathhelper.PathHelper;
import com.plohoy.generator.view.request.RequestEntity;
import com.plohoy.generator.view.request.RequestEntityField;
import com.plohoy.generator.view.request.SourceRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RequestMapper {
    private PathHelper pathHelper = new PathHelper();

    public Source mapRequestToSource(SourceRequest request) {
        return Source.builder()
                .name(request.getArtifactName())
                .path(request.getSourcePath())
                .relativeRootPaths(pathHelper.getRootPathList(request))
                .corePackagePath(pathHelper.getPackagePath(request))
                .dtoPackagePath(pathHelper.getDtoPackagePath(request))
                .corePackageName(String.format(
                        "%s.%s",
                        request.getGroupName(),
                        request.getArtifactName().replace("-", "")))
                .groupName(request.getGroupName())
                .artifactName(request.getArtifactName())
                .jdkVersion(request.getJdkVersion())
                .mainEntities(mapRequestEntitiesToSource(request.getMainEntities()))
                .secondaryEntities(mapRequestEntitiesToSource(request.getSecondaryEntities()))
                .isArchive(request.isArchive())
                .sourceData(initSourceData())
                .entryPoints(initEntryPoints(request.getEntryPoints()))
                .build();
    }

    private List<ClassEntity> mapRequestEntitiesToSource(List<RequestEntity> requestEntities) {
        List<ClassEntity> entities = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntities) {
            ClassEntity entity = ClassEntity.builder()
                    .name(requestEntity.getName())
                    .fields(mapRequestFieldsToSource(requestEntity.getFields()))
                    .build();

            entities.add(entity);
        }

        return entities;
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

        return new IndentList<>(DelimiterType.SEMICOLON, true, fields);
    }

    private HashMap<FileType, List<AbstractSourceFile>> initSourceData() {
        HashMap<FileType, List<AbstractSourceFile>> sourceData = new HashMap<>();
        for (FileType fileType : FileType.values()) {
            sourceData.put(fileType, new ArrayList<>());
        }

        return sourceData;
    }

    private HashMap<EntryPointType, String> initEntryPoints(HashMap<EntryPointType, String> requestEntryPoints) {
        HashMap<EntryPointType, String> entryPoints = new HashMap<>();
        for (EntryPointType entryPointType : EntryPointType.values()) {
            if (Objects.nonNull(requestEntryPoints.get(entryPointType))) {
                entryPoints.put(entryPointType, requestEntryPoints.get(entryPointType));
            }
        }

        return entryPoints;
    }
}
