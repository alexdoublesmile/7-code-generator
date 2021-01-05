package com.plohoy.generator.model.codeentity;

import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.EMPTY_STRING;

@Data
@Builder
public class PropertyEntity extends CodeEntity {
    private PropertyNameEntity name;
    private PropertyValueEntity value;

    @Override
    public String toString() {
        return EMPTY_STRING + name + value;
    }
}
