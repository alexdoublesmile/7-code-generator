package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModuleTag extends TagEntity {
    public ModuleTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public ModuleTag(String body, IndentList<TagEntity> innerTags) {
        this("module", body, false, innerTags);
    }

    public ModuleTag(String body) {
        this(body, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
