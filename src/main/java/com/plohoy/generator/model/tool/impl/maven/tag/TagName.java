package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SPACE;

@Data
public class TagName {
    private String name;
    private List<String> attrs;

    public TagName(String name, String ... attrs) {
        this.name = name;
        this.attrs = new IndentList<>(false, attrs);
    }

    @Override
    public String toString() {
        return name + SPACE + attrs;
    }
}
