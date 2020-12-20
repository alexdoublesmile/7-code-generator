package com.plohoy.generator.controller;

import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.model.response.SourceResponse;
import com.plohoy.generator.service.SourceService;

public class SourceController {
    private SourceService service;

    public SourceController(SourceService service) {
        this.service = service;
    }

    public SourceResponse buildSource(SourceRequest request) {
        return service.buildRequest(request);
    }
}
