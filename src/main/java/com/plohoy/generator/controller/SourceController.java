package com.plohoy.generator.controller;

import com.plohoy.generator.service.SourceService;
import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("controller")
public class SourceController {
    @Autowired private SourceService service;

    public ResponseEntity<String> buildSource(SourceRequest request) {
        return service.buildSource(request);
    }
}
