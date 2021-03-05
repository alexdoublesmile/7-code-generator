package com.plohoy.generator.view.request;

import com.plohoy.generator.model.codeentity.field.ValidationEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestEntityField {
    private String type;
    private String name;
    private String description;
    private RequestEntityRelation relation;
    private boolean array;
    private boolean filter;
    private List<ValidationEntity> validationList;
    private boolean entity;
}
