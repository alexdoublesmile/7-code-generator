package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class BuildTag extends TagEntity {
    public BuildTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public BuildTag(String body, IndentList<CodeEntity> innerTags) {
        this("build", null, body, false, innerTags);
    }

    public BuildTag(CodeEntity ... plugins) {
        this(null, new IndentList<>(plugins));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
