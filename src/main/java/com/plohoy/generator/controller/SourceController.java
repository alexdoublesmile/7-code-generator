package com.plohoy.generator.controller;

import com.plohoy.generator.service.SourceService;
import com.plohoy.generator.view.request.SourceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("controller")
@RequiredArgsConstructor
public class SourceController {
    private final SourceService service;

    public ResponseEntity<String> buildSource(SourceRequest request) {
        return service.buildSource(request);
    }
}
