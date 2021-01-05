package com.plohoy.generator.view.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Entity {
    private String name;
    private List<Field> fields;

}
