package com.plohoy.generator.model.tool.impl.docker;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.tool.impl.docker.command.DockerCommand;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DockerEntity extends CodeEntity<DockerEntity> {
    private IndentList<DockerCommand> commands;

    @Override
    public String toString() {
        return commands.toString();
    }

    @Override
    public DockerEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }
}
