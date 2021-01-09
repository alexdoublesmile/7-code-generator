package com.plohoy.generator.util.stringhelper.list;

import java.util.*;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

public abstract class CustomList<T> extends ArrayList<T> {

    private DelimiterType delimiter;
    private boolean listStyleAtTheEnd;
    private boolean delimiterAtTheEnd;

    public CustomList(DelimiterType delimiter, boolean listStyleAtTheEnd, boolean delimiterAtTheEnd, List<T> elements) {
        super(elements);
        this.delimiter = delimiter;
        this.listStyleAtTheEnd = listStyleAtTheEnd;
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

            String delimiterString = isFirstElement ? EMPTY : delimiter.getDelimiter() + getListStyle();

            result.append(element == this ? "(this Collection)" : delimiterString + element);
            isFirstElement = false;

            if (!iterator.hasNext()) {
                result.append(delimiterAtTheEnd ? delimiter.getDelimiter() : EMPTY);
                result.append(listStyleAtTheEnd ? getListStyle() : EMPTY);
                return result.toString();
            }
        }
    }

    protected abstract String getListStyle();
}

