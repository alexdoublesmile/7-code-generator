package com.plohoy.generator.model.file;

import com.plohoy.generator.model.codeentity.CodeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AbstractSourceFile<T extends CodeEntity> {
    private String path;
    private String fileName;
    private T data;
}
