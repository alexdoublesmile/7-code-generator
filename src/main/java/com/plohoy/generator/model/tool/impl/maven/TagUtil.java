package com.plohoy.generator.model.tool.impl.maven;

import lombok.experimental.UtilityClass;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@UtilityClass
public class TagUtil {
    public String openTag(boolean singleTag, String tagName) {
        return singleTag ? getTag(tagName, BACKSLASH) : getTag(tagName);
    }

    public static String closeTag(boolean singleTag, String tagName) {
        return singleTag ? EMPTY : getTag(tagName, SLASH);
    }

    private static String getTag(String tagName, String delimiter) {
        switch (delimiter) {
            case SLASH: return OPEN_CORNER_BRACKET + delimiter + tagName + CLOSE_CORNER_BRACKET;
            case BACKSLASH: return OPEN_CORNER_BRACKET + tagName + delimiter + CLOSE_CORNER_BRACKET;
            default: return OPEN_CORNER_BRACKET + tagName + CLOSE_CORNER_BRACKET;
        }
    }

    private static String getTag(String tagName) {
        return getTag(tagName, EMPTY);
    }
}
