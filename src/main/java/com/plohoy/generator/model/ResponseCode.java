package com.plohoy.generator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCode {
    private String code;
    private String description;
}
