package com.plohoy.generator.service.strategy.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.entity.Entity;
import com.plohoy.generator.service.strategy.Strategy;

import java.util.List;

public class MicroserviceSimpleStrategy implements Strategy {
    public Source buildSource(List<Entity> entities) {

        return new Source();
    }
}
