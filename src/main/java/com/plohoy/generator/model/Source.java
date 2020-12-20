package com.plohoy.generator.model;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class Source {
    private String name;
    private List<File> files;
    private boolean isArchive;
}
