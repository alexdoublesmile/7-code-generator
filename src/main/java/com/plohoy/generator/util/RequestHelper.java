package com.plohoy.generator.util;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolHelper;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.view.request.RequestEntity;
import com.plohoy.generator.view.request.SourceRequest;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Objects;

@UtilityClass
public class RequestHelper {
    public static SourceRequest normalizeRequestData(SourceRequest request) {

        String entityName = request.getEntities().stream()
                .filter(RequestEntity::hasEndpoints)
                .map(RequestEntity::getName)
                .findFirst()
                .get();

        String author = request.getAuthor();
        if (Objects.isNull(author)) {
            request.setAuthor("Alex Plohoy");
        }

        String description = request.getDescription();
        if (Objects.isNull(description)) {
            request.setDescription(String.format("%s REST Microservice", entityName));
        }

        String sourcePath = request.getSourcePath();
        if (Objects.isNull(sourcePath)) {
            request.setSourcePath("C://generated/");
        }

        String groupName = request.getGroupName();
        if (Objects.isNull(groupName)) {
            request.setGroupName("com.plohoy");
        }

        String artifactName = request.getArtifactName();
        if (Objects.isNull(artifactName)) {
            request.setArtifactName(String.format("%s-service", entityName.toLowerCase()));
        }

        String jdkVersion = request.getJdkVersion();
        if (Objects.isNull(jdkVersion)) {
            request.setJdkVersion("11");
        }

        ArchitectureType architecture = request.getArchitecture();
        if (Objects.isNull(architecture)) {
            request.setArchitecture(ArchitectureType.REST_SIMPLE);
        }

        boolean dtoModuleExists = request.isDtoModuleExists();
        boolean archive = request.isArchive();


        HashMap<ToolType, AbstractTool> tools = request.getTools();
        if (Objects.isNull(tools)) {
            request.setTools(ToolHelper.getDefaultTools());
        }

        return request;
    }
}
