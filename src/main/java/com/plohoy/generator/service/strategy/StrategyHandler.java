package com.plohoy.generator.service.strategy;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.service.strategy.impl.MicroserviceFlexStrategy;
import com.plohoy.generator.service.strategy.impl.MicroserviceSimpleStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StrategyHandler {
    private HashMap<ArchitectureType, Strategy> strategies;

    public StrategyHandler() {
        strategies = new HashMap<>();
        strategies.put(ArchitectureType.MICROSERVICE_SIMPLE, new MicroserviceSimpleStrategy());
        strategies.put(ArchitectureType.MICROSERVICE_FLEX, new MicroserviceFlexStrategy());
    }

    public HashMap<ArchitectureType, Strategy> getStrategies() {
        return strategies;
    }
}
