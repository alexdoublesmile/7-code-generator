package com.plohoy.generator.model.codeentity.annotation;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class PropertyEntity extends CodeEntity {
    private String name;
    private String simpleValue;
    private String quotedValue;
    private QuotedValueList quotedValueList;

    @Override
    public String toString() {
        return EMPTY
                + StringUtil.checkStringForNull(name, name + SPACE + EQUAL + SPACE)
                + simpleValue
                + StringUtil.checkForNull(quotedValue, QUOTE + quotedValue + QUOTE)
                + quotedValueList;
    }

    public void setQuotedValueList(QuotedValueList quotedValueList) {
        this.quotedValueList = quotedValueList;
        this.quotedValueList.setParentEntity(this);
    }
}
