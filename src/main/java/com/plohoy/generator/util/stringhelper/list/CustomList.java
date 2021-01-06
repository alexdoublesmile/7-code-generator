package com.plohoy.generator.util.stringhelper.list;

import java.util.*;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public abstract class CustomList<T> extends ArrayList<T> {

    private DelimiterType delimiter;
    private boolean delimiterAtTheEnd;

    public CustomList(DelimiterType delimiter, boolean delimiterAtTheEnd, List<T> elements) {
        super(elements);
        this.delimiter = delimiter;
        this.delimiterAtTheEnd = delimiterAtTheEnd;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        Iterator<T> iterator = iterator();
        if (!iterator.hasNext())
            return "";

        boolean isFirstElement = true;
        for (;;) {
            T element = iterator.next();

            String delimiterString = isFirstElement ? EMPTY_STRING : delimiter.getDelimiter() + getListStyle();

            result.append(element == this ? "(this Collection)" : delimiterString + element);
            isFirstElement = false;

            if (!iterator.hasNext()) {
                result.append(delimiterAtTheEnd ? delimiter.getDelimiter() + getListStyle() : EMPTY_STRING);
                return result.toString();
            }
        }
    }

    protected abstract String getListStyle();
}

