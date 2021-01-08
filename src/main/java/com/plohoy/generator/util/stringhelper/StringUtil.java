package com.plohoy.generator.util.stringhelper;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY_STRING;

public class StringUtil {

    public static String toCamelCase(String string) {
        return toCamelCase(string, false);
    }

    public static String toCamelCase(String string, boolean firstWordToLowerCase) {
        boolean isPrevLowerCase = false;
        boolean isNextUpperCase = !firstWordToLowerCase;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);

            if (!Character.isLetterOrDigit(currentChar)) {
                isNextUpperCase = result.length() > 0
                                || isNextUpperCase;

            } else {
                result.append(
                        isNextUpperCase
                                ? Character.toUpperCase(currentChar)
                                : isPrevLowerCase
                                    ? currentChar
                                    : Character.toLowerCase(currentChar));
                isNextUpperCase = false;
            }
            isPrevLowerCase = result.length() > 0
                            && Character.isLowerCase(currentChar);
        }
        return result.toString();
    }

    public static String checkStringForNull(String value, String resultValue) {
        return Objects.nonNull(value) && !value.isEmpty() ? resultValue : EMPTY_STRING;
    }

    public static String checkForNull(Object object, String resultString) {
        return Objects.nonNull(object) ? resultString : EMPTY_STRING;
    }
}
