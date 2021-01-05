package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import com.plohoy.generator.util.stringhelper.wrapper.RoundBracketWrapper;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.ANNOTATION_MARK;

@Data
@Builder
public class AnnotationEntity extends CodeEntity {
    private String name;
    private EnumerationList<PropertyEntity> properties;

    @Override
    public String toString() {
        return ANNOTATION_MARK
                + name
                + new RoundBracketWrapper(DelimiterType.NONE, properties);
    }
}
