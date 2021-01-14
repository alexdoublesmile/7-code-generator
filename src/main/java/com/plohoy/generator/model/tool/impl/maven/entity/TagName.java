package com.plohoy.generator.model.tool.impl.maven.entity;

import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SPACE;

@Data
@Builder
public class TagName {
    private String name;
    private EnumerationList<String> attrs;

    public TagName(String name, String ... attrs) {
        this.name = name;
        this.attrs = new EnumerationList<>(DelimiterType.COMMA, false, attrs);
    }

    @Override
    public String toString() {
        return name + SPACE + attrs;
    }
}
