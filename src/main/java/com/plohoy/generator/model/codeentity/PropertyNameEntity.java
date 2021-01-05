package com.plohoy.generator.model.codeentity;

import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.EQUALS;
import static com.plohoy.generator.util.codegenhelper.CodeTemplate.SPACE_SYMBOL;

@Data
@Builder
public class PropertyNameEntity extends CodeEntity {
    private String name;

    @Override
    public String toString() {
        return name + SPACE_SYMBOL + EQUALS + SPACE_SYMBOL;
    }
}
