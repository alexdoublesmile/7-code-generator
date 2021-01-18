package com.plohoy.generator.model.tool.impl.maven;

import com.plohoy.generator.util.stringhelper.StringUtil;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@UtilityClass
public class TagUtil {
    public String openTag(boolean singleTag, String tagName, List<String> attrs) {
        return singleTag ? getTag(tagName, attrs, "end") : getTag(tagName, attrs, EMPTY);
    }

    public static String closeTag(boolean singleTag, String tagName) {
        return singleTag ? EMPTY : getTag(tagName, null, "start");
    }

    private static String getTag(String tagName, List<String> attrs, String slashPosition) {
        switch (slashPosition) {
            case "start": return OPEN_CORNER_BRACKET + SLASH + tagName + CLOSE_CORNER_BRACKET;
            case "end": return OPEN_CORNER_BRACKET + tagName + StringUtil.checkForNull(attrs, SPACE + attrs) + SLASH + CLOSE_CORNER_BRACKET;
            default: return OPEN_CORNER_BRACKET + tagName + StringUtil.checkForNull(attrs, SPACE + attrs) + CLOSE_CORNER_BRACKET;
        }
    }
}
