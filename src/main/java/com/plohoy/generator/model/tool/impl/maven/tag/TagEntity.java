package com.plohoy.generator.model.tool.impl.maven.tag;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.impl.maven.TagUtil;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.List;
import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
public class TagEntity extends CodeEntity<TagEntity> {
    private String name;
    private List<String> attrs;
    private String body;
    private boolean singleTag;
    private IndentList<CodeEntity> innerTags;

    public TagEntity(String name, List<String> attrs, String body, boolean singleTag, IndentList<CodeEntity> innerTags) {
        this.name = name;
        this.attrs = attrs;
        this.body = body;
        this.singleTag = singleTag;
        if (Objects.nonNull(innerTags)) setInnerTags(innerTags);
    }

    @Override
    public String toString() {
        return
                Objects.isNull(innerTags) && Objects.isNull(body) ? EMPTY
                    : (getTab(getNestLvl()) + TagUtil.openTag(singleTag, name,  attrs)
                        + body
                        + (Objects.nonNull(innerTags)
                            ? getIndent() + innerTags + getTab(getNestLvl()) + TagUtil.closeTag(singleTag, name)
                            : TagUtil.closeTag(singleTag, name))
                    ).replace(NULL, EMPTY);
    }

    public void setInnerTags(IndentList<CodeEntity> innerTags) {
        while (innerTags.remove(null));

        this.innerTags = innerTags;
        this.innerTags.stream().forEach(innerTag -> innerTag.setParentEntity(this));
    }

    @Override
    public TagEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
