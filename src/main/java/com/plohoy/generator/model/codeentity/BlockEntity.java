package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

@Data
@Builder
public class BlockEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String body;

    @Override
    public String toString() {
        return modifiers.toString()
                + OPEN_BODY_BRACKET + getIndent()
                + body + getIndent()
                + CLOSE_BODY_BRACKET + getIndent();
    }
}
