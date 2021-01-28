package com.plohoy.generator.view.request;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class RequestEntity {
    private String name;
    private List<RequestEntityField> fields;
    private List<RequestEntityField> dtoFields;
    private String description;
    private List<EndPoint> endPoints;
    private boolean restEntity;
}
