package com.plohoy.generator.model.response;

import com.plohoy.generator.model.Source;

public class SourceResponse {
    private String responseMessage;
    private Source source;

    public SourceResponse(Source source) {
        this.source = source;
        if (source != null) {
            responseMessage = "OK";
        }
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public Source getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "SourceResponse{" +
                "responseMessage='" + responseMessage + '\'' +
                "\n-- source=" + source +
                '}';
    }
}
