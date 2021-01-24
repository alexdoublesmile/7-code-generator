package com.plohoy.generator.model.file;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;

public class ConfigFile extends AbstractSourceFile<ClassEntity> {
    ConfigFile(String path, String fileName, ClassEntity data) {
        super(path, fileName, data);
    }
}
