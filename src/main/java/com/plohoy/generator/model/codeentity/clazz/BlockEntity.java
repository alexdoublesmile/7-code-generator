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
    public BlockEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return getTab(getNestLvl()) + modifiers.toString()
                + OPEN_BODY_BRACKET + getIndent()
                + getTab(getNestLvl() + 1) + body + getIndent()
                + getTab(getNestLvl()) + CLOSE_BODY_BRACKET + getIndent();
    }
}
