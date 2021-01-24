package com.plohoy.generator.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EndPoint {
    private EndPointType type;
    private String path;
    private String description;
    private List<ResponseCode> responseCodes;
}
