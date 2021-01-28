package com.plohoy.generator.model;

import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolType;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class Source {
    private String author;
    private String description;
    private String path;
    private String groupName;
    private String artifactName;
    private List<String> relativeRootPaths;
    private String corePackagePath;
    private String dtoPackagePath;
    private String resourcePath;
    private String corePackageName;
    private List<ClassEntity> entities;
    private List<ClassEntity> dtoEntities;
    private String jdkVersion;
    private boolean dtoModuleExists;
    private boolean archive;
    private HashMap<FileType, List<AbstractSourceFile>> sourceData;
    private HashMap<ToolType, AbstractTool> tools;
}
