package com.plohoy.generator.model.codeentity.annotation;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import com.plohoy.generator.util.stringhelper.wrapper.BodyBracketWrapper;
import com.plohoy.generator.util.stringhelper.wrapper.QuoteWrapper;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.getTab;

@Data
@Builder
public class QuotedValueList extends CodeEntity {
    private IndentList<String> values;

    @Override
    public QuotedValueList setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        List<String> quotedValues = values.stream()
                .map(element -> getTab(getNestLvl() + 1) + new QuoteWrapper<>(DelimiterType.NONE, element).toString())
                .collect(Collectors.toList());

        return new BodyBracketWrapper<>(DelimiterType.INDENT, new IndentList<>(DelimiterType.COMMA, false, quotedValues)).toString();
    }
}
