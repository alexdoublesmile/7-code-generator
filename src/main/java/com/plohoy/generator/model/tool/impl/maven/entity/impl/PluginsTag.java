package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class PluginsTag extends TagEntity {
    public PluginsTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public PluginsTag(String body, IndentList<TagEntity> innerTags) {
        this("plugins", body, false, innerTags);
    }

    public PluginsTag(TagEntity ... innerTags) {
        this(null, new IndentList(innerTags));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
