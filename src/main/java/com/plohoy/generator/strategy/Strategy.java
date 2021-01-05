package com.plohoy.generator.strategy;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.view.request.SourceRequest;

public interface Strategy {

    Source buildSource(SourceRequest request);
}
