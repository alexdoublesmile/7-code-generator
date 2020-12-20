package com.plohoy.generator.model;

import java.io.File;
import java.util.List;

public class Source {
    private String name;
    private List<File> files;
    private boolean isArchive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", files=" + files +
                ", isArchive=" + isArchive +
                '}';
    }
}
