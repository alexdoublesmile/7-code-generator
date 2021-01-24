package com.plohoy.generator.model.file;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;

public class EntityFile extends AbstractSourceFile<ClassEntity> {
    EntityFile(String path, String fileName, ClassEntity data) {
        super(path, fileName, data);
    }
}
