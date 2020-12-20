package com.plohoy.generator.service.strategy;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.entity.Entity;

import java.util.List;

public interface Strategy {

    Source buildSource(List<Entity> entities);
}
