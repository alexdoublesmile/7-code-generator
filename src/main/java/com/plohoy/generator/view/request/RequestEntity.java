package com.plohoy.generator.view.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestEntity {
    private String name;
    private List<RequestEntityField> fields;

}
