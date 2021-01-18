package com.plohoy.generator.model.codeentity.annotation;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.wrapper.RoundBracketWrapper;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class AnnotationEntity extends CodeEntity {
    private String name;
    private String value;
    private PropertyEntity property;
    private EnumerationList<PropertyEntity> properties;

    @Override
    public String toString() {
        return getTab(getNestLvl()) + ANNOTATION_MARK
                + name
                + StringUtil.checkStringForNull(value,
                    OPEN_PARAM_BRACKET + QUOTE + value + QUOTE + CLOSE_PARAM_BRACKET)
                + new RoundBracketWrapper(DelimiterType.NONE, property)
                + new RoundBracketWrapper(DelimiterType.NONE, properties);
    }

    public void setProperties(EnumerationList<PropertyEntity> properties) {
        this.properties = properties;
        this.properties.stream().forEach(element -> element.setParentEntity(this));
    }
}
