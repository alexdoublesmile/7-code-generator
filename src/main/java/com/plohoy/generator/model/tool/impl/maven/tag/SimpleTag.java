package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class SimpleTag extends TagEntity {
    public SimpleTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public SimpleTag(String name, boolean singleTag, IndentList<CodeEntity> innerTags) {
        this(name, null, null, singleTag, innerTags);
    }

    public SimpleTag(String name, boolean singleTag) {
        this(name, singleTag, null);
    }

    public SimpleTag(String name, String body, IndentList<CodeEntity> innerTags) {
        this(name, null, body, false, innerTags);
    }

    public SimpleTag(String name, CodeEntity ... innerTags) {
        this(name, null, null, false, new IndentList(innerTags));
    }

    public SimpleTag(String name, String body) {
        this(name, null, body, false, null);
    }
    public SimpleTag(String name, String body, List<String> attrs) {
        this(name, attrs, body, false, null);
    }
    public SimpleTag(String name, List<String> attrs, CodeEntity ... innerTags) {
        this(name, attrs, null, false, new IndentList<>(innerTags));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
