package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class PluginsTag extends TagEntity {
    public PluginsTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public PluginsTag(String body, IndentList<CodeEntity> innerTags) {
        this("plugins", null, body, false, innerTags);
    }

    public PluginsTag(CodeEntity ... innerTags) {
        this(null, new IndentList(innerTags));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
