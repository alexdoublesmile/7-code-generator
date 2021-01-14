package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PropertiesTag extends TagEntity {
    public PropertiesTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public PropertiesTag(IndentList<TagEntity> innerTags) {
        this("properties", null, false, innerTags);
    }

    public PropertiesTag(TagEntity ... properties) {
        this(new IndentList(properties));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
