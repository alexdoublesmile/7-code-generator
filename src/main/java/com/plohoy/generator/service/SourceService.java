package com.plohoy.generator.service;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.model.response.SourceResponse;
import com.plohoy.generator.model.tool.Tool;
import com.plohoy.generator.strategy.Strategy;
import com.plohoy.generator.strategy.StrategyHandler;

public class SourceService {
    private Strategy strategy;

    public SourceResponse buildRequest(SourceRequest request) {

        strategy = new StrategyHandler()
                .getStrategies()
                .get(request.getArchitecture());

        Source source = strategy.buildSource(request.getEntities());

        for (Tool tool : request.getTools()) {
            source = tool.generateCode(source);
        }

        return new SourceResponse("OK");
    }
}
