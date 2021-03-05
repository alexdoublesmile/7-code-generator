package com.plohoy.generator.model.codeentity.field;

import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationEntity {
    private ValidationEntityType type;
    private String value;
    private List<PropertyEntity> properties;
}
