package com.plohoy.generator.model.tool.impl.docker.command;

import lombok.Builder;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SPACE;

@Data
@Builder
public class ExposeCommand extends DockerCommand {
    private String value;

    public ExposeCommand(String value) {
        super("EXPOSE");
        this.value = value;
    }

    @Override
    public String toString() {
        return commandName + SPACE + value;
    }
}
