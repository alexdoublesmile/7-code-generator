package com.plohoy.generator.util.stringhelper.wrapper;

import com.plohoy.generator.util.stringhelper.list.DelimiterType;

import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY_STRING;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.QUOTE;

public class QuoteWrapper<E> {
    private DelimiterType delimiter;
    private E element;

    public QuoteWrapper(DelimiterType delimiter, E element) {
        this.element = element;
        this.delimiter = delimiter;
    }

    @Override
    public String toString() {
        return Objects.nonNull(element) ? QUOTE + delimiter.getDelimiter() + element + delimiter.getDelimiter() + QUOTE : EMPTY_STRING;
    }
}
