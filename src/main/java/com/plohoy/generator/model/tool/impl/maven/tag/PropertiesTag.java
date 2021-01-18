package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class PropertiesTag extends TagEntity {
    public PropertiesTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public PropertiesTag(IndentList<CodeEntity> innerTags) {
        this("properties", null, null, false, innerTags);
    }

    public PropertiesTag(CodeEntity ... properties) {
        this(new IndentList(properties));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
