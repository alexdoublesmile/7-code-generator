package com.plohoy.generator.strategy;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.strategy.impl.RestFlexStrategy;
import com.plohoy.generator.strategy.impl.RestSimpleStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StrategyHandler {
    private HashMap<ArchitectureType, Strategy> strategies;

    public StrategyHandler() {
        strategies = new HashMap<>();
        strategies.put(ArchitectureType.REST_SIMPLE, new RestSimpleStrategy());
        strategies.put(ArchitectureType.REST_FLEX, new RestFlexStrategy());
    }

    public HashMap<ArchitectureType, Strategy> getStrategies() {
        return strategies;
    }
}
