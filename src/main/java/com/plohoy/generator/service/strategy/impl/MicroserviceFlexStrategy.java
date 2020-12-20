package com.plohoy.generator.service.strategy.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.service.strategy.Strategy;

public class MicroserviceFlexStrategy implements Strategy {
    public Source buildSource(SourceRequest request) {

        return new Source();
    }
}
