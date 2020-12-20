package com.plohoy.generator.controller;

import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.model.response.SourceResponse;
import com.plohoy.generator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("controller")
public class SourceController {
    @Autowired private SourceService service;

    public SourceResponse buildSource(SourceRequest request) {
        return service.buildRequest(request);
    }
}
