package com.plohoy.generator.mapper;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.ClassEntity;
import com.plohoy.generator.model.codeentity.FieldEntity;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.IndentList;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.util.pathhelper.PathHelper;
import com.plohoy.generator.view.request.Entity;
import com.plohoy.generator.view.request.Field;
import com.plohoy.generator.view.request.SourceRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestMapper {
    private PathHelper pathHelper = new PathHelper();

    public Source mapRequestToSource(SourceRequest request) {
        return Source.builder()
                .name(request.getArtifactName())
                .path(request.getSourcePath())
                .relativeRootPaths(pathHelper.getRootPathList(request))
                .corePackagePath(pathHelper.getPackagePath(request))
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
                .build();
    }

    private List<ClassEntity> mapRequestEntitiesToSource(List<Entity> requestEntities) {
        List<ClassEntity> entities = new ArrayList<>();

        for (Entity requestEntity : requestEntities) {
            ClassEntity entity = ClassEntity.builder()
                    .name(requestEntity.getName())
                    .fields(mapRequestFieldsToSource(requestEntity.getFields()))
                    .build();

            entities.add(entity);
        }

        return entities;
    }

    private IndentList<FieldEntity> mapRequestFieldsToSource(List<Field> requestFields) {
        List<FieldEntity> fields = new ArrayList<>();

        for (Field requestField : requestFields) {
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
}
