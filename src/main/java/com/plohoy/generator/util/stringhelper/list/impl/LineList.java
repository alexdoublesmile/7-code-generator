package com.plohoy.generator.util.stringhelper.list.impl;

import com.plohoy.generator.util.stringhelper.list.CustomList;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;

import java.util.Arrays;
import java.util.List;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.EMPTY;

public class LineList<T> extends CustomList<T> {

    public LineList(DelimiterType delimiterType, boolean delimiterAtTheEnd, List<T> elements) {
        super(delimiterType, false, delimiterAtTheEnd, elements);
    }

    public LineList(DelimiterType delimiterType, boolean delimiterAtTheEnd, T ... elements) {
        super(delimiterType, false, delimiterAtTheEnd, Arrays.asList(elements));
    }

    public LineList(DelimiterType delimiterType, List<T> elements) {
        super(delimiterType, false, false, elements);
    }

    public LineList(DelimiterType delimiterType, T ... elements) {
        super(delimiterType, false, false, Arrays.asList(elements));
    }

    public LineList(T ... elements) {
        super(DelimiterType.NONE, false, false, Arrays.asList(elements));
    }

    @Override
    protected String getListStyle() {
        return EMPTY;
    }
}
