package com.plohoy.generator.util.stringhelper;

import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@UtilityClass
public class StringUtil {

    public String toCamelCase(String string) {
        return toCamelCase(string, false);
    }

    public String toCamelCase(String string, boolean firstWordToLowerCase) {
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

    public String toSnakeCase(String string) {
        return toSnakeCase(string, true);
    }

    public String toSnakeCase(String string, boolean lower) {
        string = toCamelCase(string, true);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                result.append("_");
                result.append(currentChar);
            } else {
                result.append(currentChar);
            }
        }

        return lower ? result.toString().toLowerCase() : result.toString();
    }

    public String checkStringForNull(String value, String resultValue) {
        return Objects.nonNull(value) && !value.isEmpty() ? resultValue : EMPTY;
    }

    public String checkForNull(Object object, String resultString) {
        return Objects.nonNull(object) ? resultString : EMPTY;
    }

    public String checkForNull(Object object) {
        return checkForNull(object, object.toString());
    }

    public String getControllerFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + CONTROLLER_SUFFIX);
    }

    public String getServiceFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + SERVICE_SUFFIX);
    }

    public String getMapperFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + MAPPER_SUFFIX);
    }

    public String getRepoFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + REPO_SUFFIX);
    }

    public String getStrategyFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + STRATEGY_SUFFIX);
    }

    public String getEntityFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + ENTITY_SUFFIX);
    }

    public String getDtoFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + DTO_SUFFIX);
    }

    public String getDaoFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + DAO_SUFFIX);
    }

    public String getUtilFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + UTIL_SUFFIX);
    }

    public String getHelperFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + HELPER_SUFFIX);
    }

    public String getBuilderFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + BUILDER_SUFFIX);
    }

    public String getTemplateFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + TEMPLATE_SUFFIX);
    }

    public String getLauncherFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + LAUNCHER_SUFFIX);
    }

    public String getRequestFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + REQUEST_SUFFIX);
    }

    public String getResponseFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + RESPONSE_SUFFIX);
    }

    public String getConfigFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + CONFIG_SUFFIX);
    }

    public String getExceptionFileName(String rootName) {
        return StringUtil.toCamelCase(rootName + EXCEPTION_SUFFIX);
    }

}
