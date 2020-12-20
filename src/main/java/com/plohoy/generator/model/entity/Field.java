package com.plohoy.generator.model.entity;

import lombok.Data;

import java.util.List;

@Data
public class Field {
    private List<String> modifiers;
    private String type;
    private String name;
}
