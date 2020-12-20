package com.plohoy.generator.service;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.model.response.SourceResponse;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.service.strategy.Strategy;
import com.plohoy.generator.service.strategy.StrategyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service")
public class SourceService {
    @Autowired private StrategyHandler strategyHandler;
    private Strategy strategy;

    public SourceResponse buildRequest(SourceRequest request) {

        strategy = strategyHandler
                .getStrategies()
                .get(request.getArchitecture());

        Source source = strategy.buildSource(request);

        for (AbstractTool tool : request.getTools()) {
            source = tool.generateCode(source);
        }

        return new SourceResponse(source);
    }
}
