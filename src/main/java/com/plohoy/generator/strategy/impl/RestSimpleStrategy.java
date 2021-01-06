package com.plohoy.generator.strategy.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.util.codegenhelper.FileBuilder;
import com.plohoy.generator.mapper.RequestMapper;
import com.plohoy.generator.view.request.SourceRequest;
import com.plohoy.generator.strategy.Strategy;

public class RestSimpleStrategy implements Strategy {

    private RequestMapper requestMapper = new RequestMapper();
    private FileBuilder fileBuilder = new FileBuilder();

    public Source buildSource(SourceRequest request) {
        return fileBuilder
                .registerSource(requestMapper.mapRequestToSource(request))
                .addSpringBootLauncher()
                .addSimpleController()
                .addSimpleService()
                .addSimpleRepository()
                .addMapper()
                .addDomain()
                .addException()
                .addProperties()
                .generateSource();
    }
}
