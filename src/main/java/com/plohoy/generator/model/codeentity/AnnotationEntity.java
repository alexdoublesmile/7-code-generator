package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import com.plohoy.generator.util.stringhelper.wrapper.RoundBracketWrapper;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

@Data
@Builder
public class AnnotationEntity extends CodeEntity {
    private String name;
    private String value;
    private EnumerationList<PropertyEntity> properties;

    @Override
    public String toString() {
        return getTab(0, this) + ANNOTATION_MARK
                + name
                + new RoundBracketWrapper<>(DelimiterType.NONE, QUOTE + value + QUOTE)
                + new RoundBracketWrapper(DelimiterType.NONE, properties);
    }

    public void setProperties(EnumerationList<PropertyEntity> properties) {
        this.properties = properties;
        this.properties.stream().forEach(element -> element.setParentEntity(this));
    }
}
