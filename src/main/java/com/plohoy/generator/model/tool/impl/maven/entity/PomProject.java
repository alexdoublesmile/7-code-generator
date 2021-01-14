package com.plohoy.generator.model.tool.impl.maven.entity;

import com.plohoy.generator.model.tool.impl.maven.MavenTemplate;
import com.plohoy.generator.model.tool.impl.maven.entity.impl.*;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.NULL;

@Builder
@Data
public class PomProject extends TagEntity {
    private String xmlHeader;

    private GroupIdTag groupId;
    private ArtifactIdTag artifactId;
    private VersionTag version;
    private NameTag projectName;
    private DescriptionTag description;
    private ParentTag parent;
    private PackagingTag packagingType;
    private ModuleTag modules;
    private PropertiesTag properties;
    private DependenciesTag dependencies;
    private BuildTag build;
    private List<SimpleTag> otherTags;

    public PomProject(
            IndentList<TagEntity> innerTags,
            GroupIdTag groupId,
            ArtifactIdTag artifactId,
            VersionTag version,
            NameTag projectName,
            DescriptionTag description,
            PackagingTag packagingType,
            ModuleTag modules,
            PropertiesTag properties,
            DependenciesTag dependencies,
            BuildTag build,
            List<SimpleTag> otherTags) {
        super(MavenTemplate.getProjectTag, null, false, null);
        this.xmlHeader = MavenTemplate.getPomHeader();
//        this.groupId = groupId;
//        this.artifactId = artifactId;
//        this.version = version;
//        this.projectName = projectName;
//        this.description = description;
//        this.packagingType = packagingType;
//        this.modules = modules;
//        this.properties = properties;
//        this.dependencies = dependencies;
//        this.build = build;
//        this.otherTags = otherTags;


        setInnerTags(new IndentList<>(
                groupId,
                artifactId,
                version,
                projectName,
                description,
                packagingType,
                modules,
                properties,
                dependencies,
                build
        ));

        setInnerTags(new IndentList(
                Stream.of(getInnerTags(), otherTags)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())));
    }

    @Override
    public String toString() {
        return (
                xmlHeader
                +
        ).replace(NULL, EMPTY);
    }
}