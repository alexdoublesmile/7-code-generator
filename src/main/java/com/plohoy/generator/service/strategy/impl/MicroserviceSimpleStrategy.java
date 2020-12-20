package com.plohoy.generator.service.strategy.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.entity.Entity;
import com.plohoy.generator.model.entity.Field;
import com.plohoy.generator.model.request.SourceRequest;
import com.plohoy.generator.service.strategy.Strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MicroserviceSimpleStrategy implements Strategy {
    public Source buildSource(SourceRequest request) {
        Source source = new Source();

        File rootDir = makeDir("../uploads/generated/"
                + request.getSourceName());

        File sourceDir = makeDir(rootDir.getPath() + "/src");
        File mainSourceDir = makeDir(sourceDir.getPath() + "/main");
        File testSourceDir = makeDir(sourceDir.getPath() + "/test");

        File mainJavaDir = makeDir(mainSourceDir.getPath() + "/java");
        File resourcesJavaDir = makeDir(mainSourceDir.getPath() + "/resources");

        File packageDir = new File(mainJavaDir.getPath() + "/" + request.getPackageName());
        packageDir.mkdirs();

        File entityDir = new File(packageDir.getPath() + "/entity/");
        entityDir.mkdir();

        File controllerDir = new File(packageDir.getPath() + "/controller/");
        controllerDir.mkdir();

        File serviceDir = new File(packageDir.getPath() + "/service/");
        serviceDir.mkdir();

        File repositoryDir = new File(packageDir.getPath() + "/repository/");
        repositoryDir.mkdir();

        File mapperDir = new File(packageDir.getPath() + "/mapper/");
        mapperDir.mkdir();

        File exceptionDir = new File(packageDir.getPath() + "/exception/");
        exceptionDir.mkdir();

        List<File> files = new ArrayList<>();
        files.add(rootDir);
        files.add(sourceDir);
        files.add(mainSourceDir);
        files.add(testSourceDir);
        files.add(mainJavaDir);
        files.add(resourcesJavaDir);
        source.setFiles(files);


        for (Entity entity : request.getEntities()) {
            File file = new File(entityDir + "/" + entity.getName() + ".java");
            StringBuilder data = new StringBuilder();

            data.append("public class ");
            data.append(file.getName(), 0, file.getName().length() - 5);
            data.append(" {");
            data.append("\n");

            for (Field field : entity.getFields()) {
                data.append("\t");

                if (Objects.isNull(field.getModifiers()) || field.getModifiers().isEmpty()) {
                    data.append("private");
                    data.append(" ");

                } else {
                    for (String modifier : field.getModifiers()) {
                        data.append(modifier);
                        data.append(" ");
                    }
                }
                data.append(field.getType());
                data.append(" ");
                data.append(field.getName());
                data.append(";");
                data.append("\n");
            }

            data.append("}");

            try {
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(data.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return source;
    }

    private File makeDir(String s) {
        File file = new File(s);
        file.mkdir();

        return file;
    }
}
