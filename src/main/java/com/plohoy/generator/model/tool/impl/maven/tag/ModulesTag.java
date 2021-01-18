package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;

@Data
public class ModulesTag extends TagEntity {
    public ModulesTag(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        super(name, attrs, body, singleTag, innerTags);
    }

    public ModulesTag(String body, IndentList<CodeEntity> innerTags) {
        this("modules", null, body, false, innerTags);
    }

    public ModulesTag(IndentList<CodeEntity> innerTags) {
        this(null, innerTags);
    }

    public ModulesTag(CodeEntity ... modules) {
        this(new IndentList(modules));
    }

    public ModulesTag(String coreModuleName, String apiModuleName) {
        this(
                new ModuleTag(coreModuleName),
                new ModuleTag(apiModuleName)
        );
    }

    public ModulesTag(String modulesNameRoot) {
        this(
                new ModuleTag(String.format("%s-api", modulesNameRoot)),
                new ModuleTag(String.format("%s-core", modulesNameRoot))
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
