package com.plohoy.generator.view.request;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class SourceRequest {
    private String author;
    private String sourcePath;
    private String groupName;
    private String artifactName;
    private String description;
    private List<RequestEntity> entities;
    private HashMap<ToolType, AbstractTool> tools;
    private String jdkVersion;
    private ArchitectureType architecture;
    private boolean dtoModuleExists;
    private boolean archive;
}
