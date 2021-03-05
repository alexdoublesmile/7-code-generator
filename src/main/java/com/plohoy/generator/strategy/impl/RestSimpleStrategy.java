package com.plohoy.generator.strategy.impl;

import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.util.codegenhelper.filebuilder.FileBuilder;
import com.plohoy.generator.mapper.RequestMapper;
import com.plohoy.generator.view.request.SourceRequest;
import com.plohoy.generator.strategy.Strategy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestSimpleStrategy implements Strategy {

    private RequestMapper requestMapper = new RequestMapper();
    private FileBuilder fileBuilder = new FileBuilder();

    public Source buildSource(SourceRequest request) {
        Source source = requestMapper.mapRequestToSource(request);

        List<ClassEntity> mainEntities = source.getEntities()
                .stream()
                .filter(ClassEntity::hasAnyEndPoint)
                .collect(Collectors.toList());

        return fileBuilder
                .registerSource(source)
                .addDomain()
                .addSimpleController()
                .addSimpleService()
                .addSimpleRepository()
                .addMapper()
                .addException()
                .addSpringBootLauncher()
                .addProperties()
                .generateSource();
    }
}
