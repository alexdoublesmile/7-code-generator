package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.EQUALS;

@Data
@Builder
public class FieldValueEntity extends CodeEntity {
    private IndentList<String> values;

    @Override
    public String toString() {
        return EQUALS + values;
    }
}
