package com.plohoy.generator.util.stringhelper.list;

import java.util.List;

import static com.plohoy.generator.util.codegenhelper.CodeTemplate.INDENT;

public class IndentList<T> extends CustomList<T> {

    public IndentList(DelimiterType delimiterType, boolean delimiterAtTheEnd, List<T> elements) {
        super(delimiterType, delimiterAtTheEnd, elements);
    }

    @Override
    protected String getListStyle() {
        return INDENT;
    }
}
