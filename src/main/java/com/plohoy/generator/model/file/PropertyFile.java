package com.plohoy.generator.model.file;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;

public class PropertyFile extends AbstractSourceFile<PropertyEntity> {
    PropertyFile(String path, String fileName, PropertyEntity data) {
        super(path, fileName, data);
    }
}
