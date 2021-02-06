package com.plohoy.generator.model.tool;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.impl.lombok.LombokTool;
import lombok.experimental.UtilityClass;

import java.util.HashMap;

@UtilityClass
public class ToolHelper {


    public boolean hasLombokTool(HashMap<ToolType, AbstractTool> tools) {
        return tools.values()
                .stream()
                .anyMatch(tool -> tool instanceof LombokTool);
    }
}
