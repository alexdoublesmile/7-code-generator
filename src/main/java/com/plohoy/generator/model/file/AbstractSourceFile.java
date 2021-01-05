package com.plohoy.generator.model.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AbstractSourceFile {
    private String path;
    private String fileName;
    private String data;
}
