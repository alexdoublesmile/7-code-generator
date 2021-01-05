package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.IndentList;
import com.plohoy.generator.util.stringhelper.wrapper.BodyBracketWrapper;
import com.plohoy.generator.util.stringhelper.wrapper.QuoteWrapper;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PropertyValueEntity extends CodeEntity {
    private IndentList<String> values;

    @Override
    public String toString() {
        if (values.size() > 0 && values.size() < 2) {
            return new QuoteWrapper<>(DelimiterType.NONE, values).toString();
        } else {
            List<String> quotedValues = values.stream()
                    .map(element -> new QuoteWrapper<>(DelimiterType.NONE, element).toString())
                    .collect(Collectors.toList());

            return new BodyBracketWrapper<>(DelimiterType.INDENT, new IndentList<>(DelimiterType.COMMA, false, quotedValues)).toString();
        }
    }
}
