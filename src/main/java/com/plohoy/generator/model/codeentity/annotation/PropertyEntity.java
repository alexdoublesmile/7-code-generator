package com.plohoy.generator.model.codeentity.annotation;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import com.plohoy.generator.util.stringhelper.wrapper.BodyBracketWrapper;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class PropertyEntity extends CodeEntity {
    private String name;
    private String simpleValue;
    private String quotedValue;
    private QuotedValueList quotedValueList;
    private IndentList<String> simpleValueList;
    private IndentList<AnnotationEntity> annotationList;

    public PropertyEntity(
            String name,
            String simpleValue,
            String quotedValue,
            QuotedValueList quotedValueList,
            IndentList<String> simpleValueList,
            IndentList<AnnotationEntity> annotationList
    ) {
        this.name = name;
        this.simpleValue = simpleValue;
        this.quotedValue = quotedValue;
        this.simpleValueList = simpleValueList;

        if (Objects.nonNull(quotedValueList)) setQuotedValueList(quotedValueList);
        if (Objects.nonNull(annotationList)) setAnnotationList(annotationList);
    }

    public void setQuotedValueList(QuotedValueList quotedValueList) {
        this.quotedValueList = quotedValueList;
        this.quotedValueList.setParentEntity(this);
    }

    public void setAnnotationList(IndentList<AnnotationEntity> annotationList) {
        this.annotationList = annotationList;
        this.annotationList.stream().forEach(annotation -> annotation.setParentEntity(this));
    }

    @Override
    public PropertyEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return EMPTY
                + StringUtil.checkStringForNull(name, name + SPACE + EQUAL + SPACE)
                + simpleValue
                + StringUtil.checkForNull(quotedValue, QUOTE + quotedValue + QUOTE)
                + quotedValueList
                + new BodyBracketWrapper<>(DelimiterType.NONE, simpleValueList, getNestLvl())
                + new BodyBracketWrapper<>(DelimiterType.INDENT, annotationList, getParentNestLvl() - 1);
    }
}
