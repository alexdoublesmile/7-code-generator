package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.StringUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class PropertyEntity extends CodeEntity {
    private String name;
    private String value;
    private PropertyValueEntity values;

    @Override
    public String toString() {
        return EMPTY_STRING
                + StringUtil.checkStringForNull(name, name + SPACE_SYMBOL + EQUALS + SPACE_SYMBOL)
                + StringUtil.checkStringForNull(value, QUOTE + value + QUOTE)
                + values;
    }

    public void setValues(PropertyValueEntity values) {
        this.values = values;
        this.values.setParentEntity(this);
    }
}
