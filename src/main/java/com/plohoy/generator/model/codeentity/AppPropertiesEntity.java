package com.plohoy.generator.model.codeentity;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class AppPropertiesEntity extends CodeEntity {
    private HashMap<String, String> propertiesMap;

    @Override
    public AppPropertiesEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        String result = EMPTY;
        for (Map.Entry<String, String> pair : propertiesMap.entrySet()) {
            result += pair.getKey();
            result += EQUAL;
            result += pair.getValue();
            result += INDENT;
        }
        return result;
    }
}
