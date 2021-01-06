package com.plohoy.generator.view.request;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.EntryPointType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class SourceRequest {
    private String sourcePath;
    private String groupName;
    private String artifactName;
    private String jdkVersion;
    private ArchitectureType architecture;
    private boolean isMicroService;
    private boolean isArchive;
    private List<RequestEntity> mainEntities;
    private List<RequestEntity> secondaryEntities;
    private HashMap<ToolType, AbstractTool> tools;
    private HashMap<EntryPointType, String> entryPoints;
}
