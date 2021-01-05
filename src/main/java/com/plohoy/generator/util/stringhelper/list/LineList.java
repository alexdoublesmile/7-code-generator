package com.plohoy.generator.util.stringhelper.list;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.EMPTY_STRING;

public class LineList<T> extends CustomList<T> {

    public LineList(DelimiterType delimiterType, boolean delimiterAtTheEnd, List<T> elements) {
        super(delimiterType, delimiterAtTheEnd, elements);
    }

    @Override
    protected String getListStyle() {
        return EMPTY_STRING;
    }
}
