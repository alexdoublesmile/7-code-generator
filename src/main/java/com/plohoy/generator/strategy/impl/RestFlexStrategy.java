package com.plohoy.generator.strategy.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.view.request.SourceRequest;
import com.plohoy.generator.strategy.Strategy;

public class RestFlexStrategy implements Strategy {
    public Source buildSource(SourceRequest request) {

        return Source.builder().build();
    }
}
