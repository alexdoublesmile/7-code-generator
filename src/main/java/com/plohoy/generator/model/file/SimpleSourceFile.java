package com.plohoy.generator.model.file;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;

public class SimpleSourceFile<T extends CodeEntity> extends AbstractSourceFile<T> {
    SimpleSourceFile(String path, String fileName, T data) {
        super(path, fileName, data);
    }
}
