package com.plohoy.generator.model.response;

import com.plohoy.generator.model.Source;
import lombok.Data;

@Data
public class SourceResponse {
    private String responseMessage;
    private Source source;

    public SourceResponse(Source source) {
        this.source = source;
        if (source != null) {
            responseMessage = "OK";
        }
    }
}
