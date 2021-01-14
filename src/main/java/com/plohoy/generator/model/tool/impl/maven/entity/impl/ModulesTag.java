package com.plohoy.generator.model.tool.impl.maven.entity.impl;

import com.plohoy.generator.model.tool.impl.maven.entity.TagEntity;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ModulesTag extends TagEntity {

    public ModulesTag(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        super(name, body, singleTag, innerTags);
    }

    public ModulesTag(String body, IndentList<TagEntity> innerTags) {
        this("modules", body, false, innerTags);
    }

    public ModulesTag(IndentList<TagEntity> innerTags) {
        this("modules", null, false, innerTags);
    }

    public ModulesTag(TagEntity ... modules) {
        this("modules", null, false, new IndentList(modules));
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
