package com.plohoy.generator.model.codeentity.clazz;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class BlockEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String body;

    @Override
    public String toString() {
        return getTab(getNestLvl() + 1) + modifiers.toString()
                + OPEN_BODY_BRACKET + getIndent()
                + getTab(getNestLvl() + 2) + body + getIndent()
                + getTab(getNestLvl() + 1) + CLOSE_BODY_BRACKET + getIndent();
    }
}
