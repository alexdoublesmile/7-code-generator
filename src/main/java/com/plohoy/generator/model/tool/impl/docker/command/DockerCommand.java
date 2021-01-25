package com.plohoy.generator.model.tool.impl.docker.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class DockerCommand {
    protected String commandName;

}
