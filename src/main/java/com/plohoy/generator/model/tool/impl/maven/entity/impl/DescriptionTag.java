package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DescriptionTag extends TagEntity {
    public DescriptionTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public DescriptionTag(String body, IndentList<TagEntity> innerTags) {
        this("description", body, false, innerTags);
    }

    public DescriptionTag(String body) {
        this(body, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
