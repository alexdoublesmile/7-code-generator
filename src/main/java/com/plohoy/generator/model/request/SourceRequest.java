package com.plohoy.generator.model.request;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.Entity;
import com.plohoy.generator.model.Tool;

import java.util.List;

public class SourceRequest {
    private ArchitectureType architecture;
    private List<Entity> entities;
    private List<Tool> tools;

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

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }
}
