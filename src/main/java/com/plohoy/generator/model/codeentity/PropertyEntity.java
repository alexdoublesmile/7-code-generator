package com.plohoy.generator.model.codeentity;

import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY_STRING;

@Data
@Builder
public class PropertyEntity extends CodeEntity {
    private PropertyNameEntity name;
    private PropertyValueEntity value;

    @Override
    public String toString() {
        return EMPTY_STRING + name + value;
    }

    public void setName(PropertyNameEntity name) {
        this.name = name;
        this.name.setParentEntity(this);
    }

    public void setValue(PropertyValueEntity value) {
        this.value = value;
        this.value.setParentEntity(this);
    }
}
