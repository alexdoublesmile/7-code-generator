package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyTag extends TagEntity {
    public PropertyTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public PropertyTag(String toolName, String toolVersion) {
        this(String.format("%s.version", toolName), toolVersion, false, null);
    }

    public PropertyTag(String name) {
        this(name, "0.0.1");
    }
}
