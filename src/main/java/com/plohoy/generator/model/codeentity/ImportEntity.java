package com.plohoy.generator.model.codeentity;

import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

@Data
@Builder
public class ImportEntity extends CodeEntity {
    private String value;

    @Override
    public String toString() {
        return IMPORT_WORD + SPACE_SYMBOL + value;
    }
}
