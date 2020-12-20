package com.plohoy.generator.service.strategy;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.service.strategy.impl.MicroserviceSimpleStrategy;

import java.util.HashMap;

public class StrategyHandler {
    private HashMap<ArchitectureType, Strategy> strategies;

    public StrategyHandler() {
        strategies = new HashMap<ArchitectureType, Strategy>();
        strategies.put(ArchitectureType.MICROSERVICE_SIMPLE, new MicroserviceSimpleStrategy());
    }

    public HashMap<ArchitectureType, Strategy> getStrategies() {
        return strategies;
    }
}
