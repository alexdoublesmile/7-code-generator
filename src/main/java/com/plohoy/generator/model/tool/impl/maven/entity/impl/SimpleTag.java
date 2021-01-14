package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SimpleTag extends TagEntity {
    public SimpleTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public SimpleTag(String name, String body, IndentList<TagEntity> innerTags) {
        this(name, body, false, innerTags);
    }

    public SimpleTag(String name, TagEntity ... innerTags) {
        this(name, null, false, new IndentList(innerTags));
    }

    public SimpleTag(String name, String body) {
        this(name, body, false, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
