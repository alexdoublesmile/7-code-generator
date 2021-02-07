package com.plohoy.generator.view.request;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Builder
public class RequestEntity {
    private String name;
    private List<RequestEntityField> fields;
    private List<RequestEntityField> dtoFields;
    private String description;
    private List<EndPoint> endPoints;

    public RequestEntity(String name, List<RequestEntityField> fields, List<RequestEntityField> dtoFields, String description, List<EndPoint> endPoints) {
        this.name = name;
        this.fields = fields;
        this.dtoFields = dtoFields;
        this.description = description;

        if (Objects.nonNull(endPoints)) setEndPoints(endPoints);
    }

    public void setEndPoints(List<EndPoint> endPoints) {
        endPoints.forEach(endPoint -> endPoint.setEntityName(name));
        this.endPoints = endPoints;
    }

    public String getDescription() {
        return Optional.ofNullable(description).orElseGet(() -> name);
    }
}
