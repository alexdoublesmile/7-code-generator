package com.plohoy.generator.view.request;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.Builder;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class SourceRequest {
    @JsonProperty(value = "author", defaultValue = "Alex")
    @JsonAlias(value = "Alex")
    @JsonValue(value = false)
    @JsonPropertyDescription(value = "Alex")
    @JsonView
    @Parameter(example = "Alex")
    private String author;
//    @JsonProperty(value = "generated code path", defaultValue = "C://generated/")
    private String sourcePath;
//    @JsonProperty(value = "your group name", defaultValue = "com.plohoy")
    private String groupName;
//    @JsonProperty(value = "your artifact name", defaultValue = "null")
    private String artifactName;
//    @JsonProperty(value = "description of your service", defaultValue = "null")
    private String description;
    private List<RequestEntity> entities;
    private HashMap<ToolType, AbstractTool> tools;
//    @JsonProperty(value = "java version", defaultValue = "11")
    private String jdkVersion;
//    @JsonProperty(value = "architecture type", defaultValue = "REST_SIMPLE")
    private ArchitectureType architecture;
//    @JsonProperty(value = "need DTO module", defaultValue = "true")
    private boolean dtoModuleExists;
//    @JsonProperty(value = "need archive", defaultValue = "false")
    private boolean archive;
}
