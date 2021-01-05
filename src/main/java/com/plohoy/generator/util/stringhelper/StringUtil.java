package com.plohoy.generator.util.stringhelper;

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
}
