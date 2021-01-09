package com.plohoy.generator.util.stringhelper.list.impl;

import com.plohoy.generator.util.stringhelper.list.CustomList;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;

import java.util.Arrays;
import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.INDENT;

public class IndentList<T> extends CustomList<T> {

    public IndentList(DelimiterType delimiterType, boolean listStyleAtTheEnd, boolean delimiterAtTheEnd, List<T> elements) {
        super(delimiterType, listStyleAtTheEnd, delimiterAtTheEnd, elements);
    }

    public IndentList(DelimiterType delimiterType, boolean listStyleAtTheEnd, boolean delimiterAtTheEnd, T ... elements) {
        super(delimiterType, listStyleAtTheEnd, delimiterAtTheEnd, Arrays.asList(elements));
    }

    public IndentList(DelimiterType delimiterType, boolean listStyleAtTheEnd, List<T> elements) {
        super(delimiterType, listStyleAtTheEnd, false, elements);
    }

    public IndentList(DelimiterType delimiterType, boolean listStyleAtTheEnd, T ... elements) {
        super(delimiterType, listStyleAtTheEnd, false, Arrays.asList(elements));
    }

    public IndentList(boolean listStyleAtTheEnd, T ... elements) {
        super(DelimiterType.NONE, listStyleAtTheEnd, false, Arrays.asList(elements));
    }

    public IndentList(T ... elements) {
        super(DelimiterType.NONE, true, false, Arrays.asList(elements));
    }

    @Override
    protected String getListStyle() {
        return INDENT;
    }
}
