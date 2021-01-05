package com.plohoy.generator.util.stringhelper.list;

import lombok.Getter;

@Getter
public enum DelimiterType {
    NONE(""),
    INDENT("\n"),
    COMMA(","),
    SEMICOLON(";"),
    DOT("."),
    COLON(":"),
    SLASH("/"),
    BACKSLASH("\\");

    private String delimiter;

    DelimiterType(String delimiter) {
        this.delimiter = delimiter;
    }
}
