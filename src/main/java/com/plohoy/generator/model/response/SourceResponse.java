package com.plohoy.generator.model.response;

import com.plohoy.generator.model.Source;

public class SourceResponse {
    private String response;
    private Source source;

    public SourceResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public Source getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "SourceResponse{" +
                "response='" + response + '\'' +
                "\n-- source=" + source +
                '}';
    }
}
