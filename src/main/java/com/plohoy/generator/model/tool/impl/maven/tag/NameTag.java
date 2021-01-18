package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class NameTag extends TagEntity {
    public NameTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public NameTag(String body, IndentList<CodeEntity> innerTags) {
        this("name", null, body, false, innerTags);
    }

    public NameTag(String body) {
        this(body, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
