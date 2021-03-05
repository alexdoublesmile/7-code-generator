package com.plohoy.generator.model.codeentity.field;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationProperty {
    private String name;
    private String value;
}
