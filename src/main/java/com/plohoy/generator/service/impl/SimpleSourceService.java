package com.plohoy.generator.service.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.service.SourceService;
import com.plohoy.generator.strategy.StrategyHandler;
import com.plohoy.generator.util.outputhelper.OutputHelper;
import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service("service")
public class SimpleSourceService implements SourceService {
    @Autowired private StrategyHandler strategyHandler;
    private OutputHelper outputHelper = new OutputHelper();

    @Override
    public ResponseEntity<String> buildSource(SourceRequest request) {

        Source source = strategyHandler.getStrategies()
                .get(request.getArchitecture())
                .buildSource(request);

        for (AbstractTool tool : request.getTools().values()) {
            source = tool.generateCode(source);
        }

        return outputHelper.writeFiles(
                source.getSourceData()
                        .values()
                        .stream()
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList()));
    }
}
