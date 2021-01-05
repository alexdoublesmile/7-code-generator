package com.plohoy.generator.service;

import com.plohoy.generator.view.request.SourceRequest;
import org.springframework.http.ResponseEntity;

public interface SourceService {
    ResponseEntity<String> buildSource(SourceRequest request);
}
