package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PackagingTag extends TagEntity {
    public PackagingTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public PackagingTag(String body, IndentList<TagEntity> innerTags) {
        this("packaging", body, false, innerTags);
    }

    public PackagingTag(String body) {
        this(body, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
