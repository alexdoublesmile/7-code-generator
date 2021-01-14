package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NameTag extends TagEntity {
    public NameTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public NameTag(String body, IndentList<TagEntity> innerTags) {
        this("name", body, false, innerTags);
    }

    public NameTag(String body) {
        this(body, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
