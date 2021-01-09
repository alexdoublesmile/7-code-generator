package com.plohoy.generator.model;

import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.AbstractSourceFile;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class Source {
    private String name;
    private String path;
    private String groupName;
    private String artifactName;
    private List<String> relativeRootPaths;
    private String corePackagePath;
    private String dtoPackagePath;
    private String corePackageName;
    private List<ClassEntity> mainEntities;
    private List<ClassEntity> secondaryEntities;
    private List<ClassEntity> mainDtoEntities;
    private List<ClassEntity> secondaryDtoEntities;
    private String jdkVersion;
    private boolean isArchive;
    private HashMap<FileType, List<AbstractSourceFile>> sourceData;
    private HashMap<EndPointType, String> endPoints;
}
