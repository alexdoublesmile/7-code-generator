package com.plohoy.generator.service;

import com.plohoy.generator.model.ArchitectureType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.Tool;
import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.model.response.SourceResponse;
import com.plohoy.generator.strategy.Strategy;

import java.util.HashMap;

public class SourceService {
    private HashMap<ArchitectureType, Strategy> strategies = new HashMap<ArchitectureType, Strategy>();

    public SourceResponse buildRequest(SourceRequest request) {

        Strategy strategy = strategies.get(request.getArchitecture());
        Source source = strategy.buildSource(request.getEntities());

        for (Tool tool : request.getTools()) {
            source = tool.generateCode(source);
        }
        return new SourceResponse("OK");
    }
}
