package com.plohoy.generator.service.strategy;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.request.SourceRequest;

public interface Strategy {

    Source buildSource(SourceRequest request);
}
