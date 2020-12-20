package com.plohoy.generator.model.request;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.entity.Entity;
import com.plohoy.generator.model.tool.AbstractTool;
import lombok.Data;

import java.util.List;

@Data
public class SourceRequest {
    private String packageName;
    private String sourceName;
    private ArchitectureType architecture;
    private List<Entity> entities;
    private List<AbstractTool> tools;
    private String version;
}
