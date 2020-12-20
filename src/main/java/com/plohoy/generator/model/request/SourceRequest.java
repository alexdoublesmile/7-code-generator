package com.plohoy.generator.model.request;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.entity.Entity;
import com.plohoy.generator.model.tool.AbstractTool;

import java.util.List;

public class SourceRequest {
    private ArchitectureType architecture;
    private List<Entity> entities;
    private List<AbstractTool> tools;
    private String version;

    public ArchitectureType getArchitecture() {
        return architecture;
    }

    public void setArchitecture(ArchitectureType architecture) {
        this.architecture = architecture;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<AbstractTool> getTools() {
        return tools;
    }

    public void setTools(List<AbstractTool> tools) {
        this.tools = tools;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
