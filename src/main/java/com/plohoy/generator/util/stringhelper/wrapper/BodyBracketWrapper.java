package com.plohoy.generator.util.stringhelper.wrapper;

import com.plohoy.generator.util.stringhelper.list.DelimiterType;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public class BodyBracketWrapper<E> {
    private DelimiterType delimiter;
    private E element;

    public BodyBracketWrapper(DelimiterType delimiter, E element) {
        this.element = element;
        this.delimiter = delimiter;
    }

    @Override
    public String toString() {
        return Objects.nonNull(element)
                ? OPEN_BODY_BRACKET + delimiter.getDelimiter() + element + delimiter.getDelimiter() + CLOSE_BODY_BRACKET
                : EMPTY;
    }
}
