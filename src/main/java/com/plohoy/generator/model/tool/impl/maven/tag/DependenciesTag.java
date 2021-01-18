package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class DependenciesTag extends TagEntity {
    public DependenciesTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public DependenciesTag(String body, IndentList<CodeEntity> innerTags) {
        this("dependencies", null, body, false, innerTags);
    }

    public DependenciesTag(CodeEntity ... dependencies) {
        this(null, new IndentList<>(dependencies));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
