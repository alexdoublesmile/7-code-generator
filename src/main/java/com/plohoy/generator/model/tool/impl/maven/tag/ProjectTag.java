package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class ProjectTag  extends TagEntity {
    public ProjectTag(List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super("project", attrs, body, singleTag, innerTags);
    }

    public ProjectTag(String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        this(null, body, singleTag, innerTags);
    }

    public ProjectTag(IndentList<CodeEntity> innerTags) {
        this(null, null, false, innerTags);
    }

    public ProjectTag(CodeEntity ... innerTags) {
        this(new IndentList(innerTags));
    }

    public ProjectTag(List<String> attrs, IndentList<CodeEntity> innerTags) {
        this(attrs, null, false, innerTags);
    }

    public ProjectTag(List<String> attrs, CodeEntity ... innerTags) {
        this(attrs, new IndentList(innerTags));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
