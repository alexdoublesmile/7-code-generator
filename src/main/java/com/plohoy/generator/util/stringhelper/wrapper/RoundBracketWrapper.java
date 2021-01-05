package com.plohoy.generator.util.stringhelper.wrapper;

import com.plohoy.generator.util.stringhelper.list.DelimiterType;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.*;

public class RoundBracketWrapper<E> {
    private DelimiterType delimiter;
    private E element;

    public RoundBracketWrapper(DelimiterType delimiter, E element) {
        this.element = element;
        this.delimiter = delimiter;
    }

    @Override
    public String toString() {

        return Objects.nonNull(element) ? OPEN_PARAM_BRACKET + delimiter.getDelimiter() + element + delimiter.getDelimiter() + CLOSE_PARAM_BRACKET : EMPTY_STRING;
    }
}
