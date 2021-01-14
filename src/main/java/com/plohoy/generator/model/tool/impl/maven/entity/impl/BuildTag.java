package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BuildTag extends TagEntity {
    public BuildTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public BuildTag(String body, IndentList<TagEntity> innerTags) {
        this("version", body, false, innerTags);
    }

    public BuildTag(String body) {
        this(body, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
