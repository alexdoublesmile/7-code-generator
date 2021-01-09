package com.plohoy.generator.model.codeentity.field;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EQUAL;

@Data
@Builder
public class FieldValueEntity extends CodeEntity {
    private IndentList<String> values;

    @Override
    public String toString() {
        return EQUAL + values;
    }
}
