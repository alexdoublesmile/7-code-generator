package com.plohoy.generator.view.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestEntityField {
    private String type;
    private String name;
    private String description;
    private RequestEntityRelation relation;
    private boolean array;
    private boolean entity;
}
