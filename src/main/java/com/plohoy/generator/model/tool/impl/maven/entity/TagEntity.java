package com.plohoy.generator.model.tool.impl.maven.entity;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.impl.maven.TagUtil;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.getIndent;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.getTab;

@Data
@Builder
public abstract class TagEntity extends CodeEntity {
    private TagName nameWithAttrs;
    private String name;
    private String body;
    private boolean singleTag;
    private IndentList<TagEntity> innerTags;

    public TagEntity(String name, String body, boolean singleTag, IndentList<TagEntity> innerTags) {
        this.name = name;
        this.body = body;
        this.singleTag = singleTag;
        this.innerTags = innerTags;
    }

    @Override
    public String toString() {
        return
                StringUtil.checkForNull(nameWithAttrs, getTab(1, this) + TagUtil.openTag(singleTag, nameWithAttrs.toString()))
                + StringUtil.checkStringForNull(name, getTab(1, this) + TagUtil.openTag(singleTag, name))
                + body
                + StringUtil.checkForNull(innerTags, getIndent() + innerTags)
                + getTab(1, this) + TagUtil.closeTag(singleTag, name);
    }

    public void setInnerTags(IndentList<TagEntity> innerTags) {
        this.innerTags = innerTags;
        this.innerTags.stream().forEach(tag -> tag.setParentEntity(this));
    }
}
