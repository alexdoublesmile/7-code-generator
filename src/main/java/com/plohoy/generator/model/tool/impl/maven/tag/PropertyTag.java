package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class PropertyTag extends TagEntity {
    public PropertyTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public PropertyTag(String toolName, String toolVersion) {
        this(String.format("%s.version", toolName), null, toolVersion, false, null);
    }

    public PropertyTag(String name) {
        this(name, "0.0.1");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
