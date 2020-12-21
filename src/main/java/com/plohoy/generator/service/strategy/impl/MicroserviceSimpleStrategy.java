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
import java.util.List;
import java.util.Objects;

public class MicroserviceSimpleStrategy implements Strategy {
    private SourceRequest request;

    public Source buildSource(SourceRequest request) {
        this.request = request;
        Source source = new Source();

        File rootDir = makeDir("../uploads/generated/" + request.getSourceName());
        File apiModuleDir = makeDir(rootDir.getPath() + "/" + request.getSourceName() + "-api");
        File coreModuleDir = makeDir(rootDir.getPath() + "/" + request.getSourceName() + "-core");

        String apiPackagePath = buildApiTree(apiModuleDir);
        String corePackagePath = buildCoreTree(coreModuleDir);

        buildDtoFiles(apiPackagePath + "/dto/");
        buildDomainFiles(corePackagePath + "/entity/");

        String mainEntityName = request.getEntities().get(0).getName();
        buildControllerFiles(corePackagePath + "/controller/", mainEntityName);
        buildServiceFiles(corePackagePath + "/service/", mainEntityName);
        buildRepositoryFiles(corePackagePath + "/repository/", request.getEntities());
        buildMapperFiles(corePackagePath + "/mapper/", request.getEntities());
        buildExceptionFiles(corePackagePath + "/exception/");

        buildApplicationFile(corePackagePath);

        buildMainPomFile(rootDir.getPath());
        buildApiPomFile(apiModuleDir.getPath());
        buildCorePomFile(coreModuleDir.getPath());

        return source;
    }

    private void buildRepositoryFiles(String repositoryPath, List<Entity> entities) {
        for (Entity entity : entities) {
            
        }
    }

    private void buildServiceFiles(String servicePath, String... entities) {
        for (String entityName : entities) {
            File file = new File(servicePath + entityName + "Service.java");
            StringBuilder serviceData = new StringBuilder();

            String corePackageName = request.getPackageName().replaceAll("/", ".");

            serviceData.append("package " + corePackageName + ".controller;\n\n");

            serviceData.append("import " + corePackageName + ".repository." + entityName + "Repository;\n");
            serviceData.append("import " + corePackageName + ".mapper." + entityName + "Mapper;\n");
            serviceData.append("import " + corePackageName + ".entity." + entityName + ";\n");
            serviceData.append("import " + corePackageName + ".dto." + entityName + "Dto;\n\n");

            serviceData.append("import java.util.*;\n");
            serviceData.append("import java.time.*;\n");
            serviceData.append("import org.springframework.beans.factory.annotation.*;\n");
            serviceData.append("import org.springframework.data.domain.*;\n");
            serviceData.append("import org.springframework.http.*;\n");
            serviceData.append("import org.springframework.stereotype.Service;\n");
            serviceData.append("import org.springframework.transaction.annotation.Transactional;\n");
            serviceData.append("import org.springframework.web.server.ResponseStatusException;\n");
            serviceData.append("import org.springframework.dao.EmptyResultDataAccessException;\n");
            serviceData.append("import lombok.extern.slf4j.Slf4j;\n\n");

            serviceData.append("@Slf4j\n");
            serviceData.append("@Service\n\n");
            serviceData = openClass(serviceData, file.getName());

            serviceData = writeServiceConstructor(serviceData);

            serviceData = writeFindAllService(serviceData);

            serviceData = writeGetService(serviceData);

            serviceData = writeCreateService(serviceData);

            serviceData = writeUpdateService(serviceData);

            serviceData = writeSoftDeleteService(serviceData);

            serviceData = writeHardDeleteService(serviceData);

            closeClassAndWriteToFile(serviceData, file);
        }
    }

    private void buildControllerFiles(String controllerPath, String ... entities) {
        for (String entityName : entities) {
            File file = new File(controllerPath + entityName + "Controller.java");
            StringBuilder controllerData = new StringBuilder();

            String corePackageName = request.getPackageName().replaceAll("/", ".");

            controllerData.append("package " + corePackageName + ".controller;\n\n");

            controllerData.append("import " + corePackageName + ".service." + entityName + "Service;\n");
            controllerData.append("import " + corePackageName + ".dto." + entityName + "Dto;\n\n");

            controllerData.append("import java.util.*;\n");
            controllerData.append("import org.springframework.beans.factory.annotation.Autowired;\n");
            controllerData.append("import org.springframework.data.domain.*;\n");
            controllerData.append("import org.springframework.data.web.PageableDefault;\n");
            controllerData.append("import org.springframework.http.ResponseEntity;\n");
            controllerData.append("import org.springframework.web.bind.annotation.*;\n");
            controllerData.append("import io.swagger.v3.oas.annotations.*;\n");
            controllerData.append("import lombok.extern.slf4j.Slf4j;\n\n");

            controllerData.append("@Slf4j\n");
            controllerData.append("@Tag(name = \"\")\n");
            controllerData.append("@RestController\n");
            controllerData.append("@RequestMapping(\"\")\n");
            controllerData = openClass(controllerData, file.getName());

            controllerData = writeControllerConstructor(controllerData);

            controllerData = writeSwagger(controllerData);
            controllerData = writeFindAllController(controllerData);

            controllerData = writeSwagger(controllerData);
            controllerData = writeGetController(controllerData);

            controllerData = writeSwagger(controllerData);
            controllerData = writeCreateController(controllerData);

            controllerData = writeSwagger(controllerData);
            controllerData = writeUpdateController(controllerData);

            controllerData = writeSwagger(controllerData);
            controllerData = writeSoftDeleteController(controllerData);

            controllerData = writeSwagger(controllerData);
            controllerData = writeHardDeleteController(controllerData);

            closeClassAndWriteToFile(controllerData, file);
        }
    }

    private void buildDtoFiles(String dtoPackagePath) {
        for (Entity entity : request.getEntities()) {
            File file = new File(dtoPackagePath + entity.getName() + "Dto.java");
            StringBuilder dtoData = new StringBuilder();

            String dtoPackageName = request.getPackageName().replaceAll("/", ".");

            dtoData.append("package " + dtoPackageName + ".dto;\n\n");
            dtoData.append("import java.util.*;\n");
            dtoData.append("import java.time.*;\n\n");
            dtoData.append("import com.fasterxml.jackson.annotation.JsonProperty;\n\n");
            dtoData.append("import lombok.Data;\n\n");

            dtoData.append("@Data\n");
            dtoData = openClass(dtoData, file.getName());


            for (Field field : entity.getFields()) {
                dtoData.append("\t");
                dtoData.append("@JsonProperty");
                dtoData.append("(\"" + field.getName() + "\")\n");

                dtoData = writeField(dtoData, field);
            }

            closeClassAndWriteToFile(dtoData, file);
        }
    }

    private void buildDomainFiles(String domainPackagePath) {
        for (Entity entity : request.getEntities()) {
            File file = new File(domainPackagePath + entity.getName() + ".java");
            StringBuilder data = new StringBuilder();

            String corePackageName = request.getPackageName().replaceAll("/", ".");

            data.append("package " + corePackageName + ".entity;\n\n");
            data.append("import java.util.*;\n");
            data.append("import java.time.*;\n\n");
            data.append("import javax.persistence.*;\n\n");
            data.append("import lombok.Data;\n\n");

            data.append("@Data\n");
            data.append("@Entity\n");
            data = openClass(data, file.getName());

            for (Field field : entity.getFields()) {
                data.append("\t");
                if (field.getName().equals("id")) {
                    data.append("@GeneratedValue");
                    data.append("\n");
                    data.append("\t");
                    data.append("@Id");
                } else {
                    data.append("@Column");
                }
                data.append(" ");

                data = writeField(data, field);
            }

            closeClassAndWriteToFile(data, file);
        }
    }

    private String buildApiTree(File moduleDir) {
        File apiPackageDir = buildSourceTree(moduleDir);
        File dtoDir = makeDir(apiPackageDir.getPath() + "/dto/");

        return apiPackageDir.getPath();
    }

    private String buildCoreTree(File moduleDir) {
        File corePackageDir = buildSourceTree(moduleDir);

        File entityDir = makeDir(corePackageDir.getPath() + "/entity/");
        File controllerDir = makeDir(corePackageDir.getPath() + "/controller/");
        File serviceDir = makeDir(corePackageDir.getPath() + "/service/");
        File repositoryDir = makeDir(corePackageDir.getPath() + "/repository/");
        File mapperDir = makeDir(corePackageDir.getPath() + "/mapper/");
        File exceptionDir = makeDir(corePackageDir.getPath() + "/exception/");

        return corePackageDir.getPath();
    }

    private File buildSourceTree(File moduleRootDir) {
        File srcDir = makeDir(moduleRootDir.getPath() + "/src");

        File mainSrcDir = makeDir(srcDir.getPath() + "/main");
        File testSrcDir = makeDir(srcDir.getPath() + "/test");

        File mainJavaDir = makeDir(mainSrcDir.getPath() + "/java");
        File resourcesJavaDir = makeDir(mainSrcDir.getPath() + "/resources");

        File packageDir = new File(mainJavaDir.getPath() + "/" + request.getPackageName());
        packageDir.mkdirs();

        return packageDir;
    }

    private File makeDir(String s) {
        File file = new File(s);
        file.mkdir();

        return file;
    }

    private StringBuilder openClass(StringBuilder data, String fileName) {
        data.append("public");
        data.append(" ");
        data.append("class");
        data.append(" ");
        data.append(fileName, 0, fileName.length() - 5);
        data.append(" ");
        data.append("{");
        data.append("\n");
        return data;
    }

    private void closeClassAndWriteToFile(StringBuilder data, File file) {
        data.append("}");

        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(data.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder writeField(StringBuilder data, Field field) {
        if (Objects.isNull(field.getModifiers()) || field.getModifiers().isEmpty()) {
            data.append("\t");
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

        return data;
    }
}
