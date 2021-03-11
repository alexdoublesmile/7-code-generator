package com.plohoy.generator.service;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.tool.AbstractTool;
import com.plohoy.generator.model.tool.ToolHelper;
import com.plohoy.generator.model.tool.ToolType;
import com.plohoy.generator.strategy.StrategyHandler;
import com.plohoy.generator.util.RequestHelper;
import com.plohoy.generator.util.outputhelper.OutputHelper;
import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("service")
public class SourceService {
    @Autowired private StrategyHandler strategyHandler;
    private OutputHelper outputHelper = new OutputHelper();

    public ResponseEntity<String> buildSource(SourceRequest request) {

        request = RequestHelper.normalizeRequestData(request);

        Source source = strategyHandler.getStrategies()
                .get(request.getArchitecture())
                .buildSource(request);

        for (AbstractTool tool : source.getTools().values()) {
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
