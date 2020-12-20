package com.plohoy.generator.strategy;

import com.plohoy.generator.model.entity.Entity;
import com.plohoy.generator.model.Source;

import java.util.List;

public interface Strategy {

    Source buildSource(List<Entity> entities);
}
