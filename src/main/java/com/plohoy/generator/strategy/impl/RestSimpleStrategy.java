package com.plohoy.generator.strategy.impl;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.util.codegenhelper.FileBuilder;
import com.plohoy.generator.mapper.RequestMapper;
import com.plohoy.generator.view.request.SourceRequest;
import com.plohoy.generator.strategy.Strategy;

public class RestSimpleStrategy implements Strategy {

    private RequestMapper requestMapper = new RequestMapper();
    private FileBuilder fileBuilder = new FileBuilder();

    public Source buildSource(SourceRequest request) {

        return fileBuilder
                .registerSource(requestMapper.mapRequestToSource(request))
                .addSpringBootLauncher()
                .addSimpleController()
                .addSimpleService()
                .addSimpleRepository()
                .addMapper()
                .addDomain()
                .addException()
                .generateSource();





//        buildDtoFiles(apiPackagePath + "/dto/");
//
//        buildDomainFiles(corePackagePath + "/codeentity/");
//
//        String mainEntityName = request.getSecondaryEntities().get(0).getName();
//        buildControllerFiles(corePackagePath + "/controller/", mainEntityName);
//        buildServiceFiles(corePackagePath + "/service/", mainEntityName);
//        buildRepositoryFiles(corePackagePath + "/repository/", request.getSecondaryEntities());
//        buildMapperFiles(corePackagePath + "/mapper/", request.getSecondaryEntities());
////        buildExceptionFiles(corePackagePath + "/exception/");
//
//        buildApplicationFile(corePackagePath, mainEntityName);
//
//        buildPropertyFile(coreModuleDir.getPath() + "/src/main/resources/");
//


    }

//    private void buildPropertyFile(String resourcesPath) {
//        buildAppProps(resourcesPath + "application.properties");
//        buildAppDevProps(resourcesPath + "application-dev.properties");
//        buildMessageProps(resourcesPath + "message.properties");
//    }
//
//    private void buildMessageProps(String messagePropsPath) {
//        File file = new File(messagePropsPath);
//        StringBuilder messagePropsData = new StringBuilder();
//
//        messagePropsData.append("ids.do.not.match.message=ID from ClassEntity & ID from path don't match");
//
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(messagePropsData.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void buildAppDevProps(String appDevPropsPath) {
//        File file = new File(appDevPropsPath);
//        StringBuilder appDevPropsData = new StringBuilder();
//
//        appDevPropsData.append("server.port=8088\n" +
//                "spring.main.banner-mode=off\n" +
//                "\n" +
//                "spring.jpa.show-sql=true\n" +
//                "spring.jpa.generate-ddl=true\n" +
//                "spring.jpa.hibernate.ddl-auto=create-drop\n" +
//                "spring.liquibase.enabled=false");
//
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(appDevPropsData.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void buildAppProps(String appPropsPath) {
//        File file = new File(appPropsPath);
//        StringBuilder appPropertiesData = new StringBuilder();
//
//        appPropertiesData.append("spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:postgres}?sslmode=${DB_SSL_MODE:disable}&prepareThreshold=${DB_PREPARE_THRESHOLD:5}\n" +
//                "spring.datasource.username=${DB_USERNAME:postgres}\n" +
//                "spring.datasource.password=${DB_PASSWORD:mysecretpassword}\n" +
//                "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect\n" +
//                "\n" +
//                "spring.jpa.generate-ddl=false\n" +
//                "spring.jpa.hibernate.ddl-auto=none\n" +
//                "spring.liquibase.change-log=classpath:postgres/changelog/postgres.changelog-master.yml");
//
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(appPropertiesData.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void buildCorePomFile(String path) {
//        File file = new File(path + "pom.xml");
//        StringBuilder pomData = new StringBuilder();
//
//        pomData.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
//                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
//                "    <modelVersion>4.0.0</modelVersion>\n" +
//                "    <parent>\n" +
//                "        <groupId>"
//                + request.getPackageName()
//                .substring(0, request.getPackageName().length() - request.getSourceName().length())
//                .replaceAll("/", ".")
//                + "</groupId>\n" +
//                "        <artifactId>" + request.getSourceName() + "</artifactId>\n" +
//                "        <version>0.0.1</version>\n" +
//                "    </parent>\n" +
//                "    <groupId>"
//                + request.getPackageName()
//                .substring(0, request.getPackageName().length() - request.getSourceName().length())
//                .replaceAll("/", ".")
//                + "</groupId>\n" +
//                "    <artifactId>" + request.getSourceName() + "-core</artifactId>\n" +
//                "\n" +
//                "    <dependencies>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.springframework.boot</groupId>\n" +
//                "            <artifactId>spring-boot-starter-web</artifactId>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.springframework.boot</groupId>\n" +
//                "            <artifactId>spring-boot-starter-actuator</artifactId>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.springframework.boot</groupId>\n" +
//                "            <artifactId>spring-boot-devtools</artifactId>\n" +
//                "        </dependency>\n" +
//                "\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.springframework.boot</groupId>\n" +
//                "            <artifactId>spring-boot-starter-data-jpa</artifactId>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.postgresql</groupId>\n" +
//                "            <artifactId>postgresql</artifactId>\n" +
//                "            <scope>runtime</scope>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.liquibase</groupId>\n" +
//                "            <artifactId>liquibase-core</artifactId>\n" +
//                "        </dependency>\n" +
//                "\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.projectlombok</groupId>\n" +
//                "            <artifactId>lombok</artifactId>\n" +
//                "            <version>${lombok.version}</version>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.mapstruct</groupId>\n" +
//                "            <artifactId>mapstruct</artifactId>\n" +
//                "            <version>${org.mapstruct.version}</version>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>io.swagger.core.v3</groupId>\n" +
//                "            <artifactId>swagger-annotations</artifactId>\n" +
//                "            <version>${swagger.core.version}</version>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.springdoc</groupId>\n" +
//                "            <artifactId>springdoc-openapi-ui</artifactId>\n" +
//                "            <version>${springdoc.version}</version>\n" +
//                "        </dependency>\n" +
//                "\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.springframework.boot</groupId>\n" +
//                "            <artifactId>spring-boot-starter-test</artifactId>\n" +
//                "            <scope>test</scope>\n" +
//                "            <exclusions>\n" +
//                "                <exclusion>\n" +
//                "                    <groupId>org.junit.vintage</groupId>\n" +
//                "                    <artifactId>junit-vintage-engine</artifactId>\n" +
//                "                </exclusion>\n" +
//                "            </exclusions>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.springframework</groupId>\n" +
//                "            <artifactId>spring-test</artifactId>\n" +
//                "            <version>${spring.version}</version>\n" +
//                "        </dependency>\n" +
//                "\n" +
//                "        <dependency>\n" +
//                "            <groupId>"
//                                + request.getPackageName()
//                                    .substring(0, request.getPackageName().length() - request.getSourceName().length())
//                                    .replaceAll("/", ".")
//                            + "</groupId>\n" +
//                "            <artifactId>" + request.getSourceName() + "-api</artifactId>\n" +
//                "            <version>${version}</version>\n" +
//                "        </dependency>\n" +
//                "    </dependencies>\n" +
//                "\n" +
//                "    <buildsection>\n" +
//                "        <plugins>\n" +
//                "            <buildsection>\n" +
//                "                <groupId>org.springframework.boot</groupId>\n" +
//                "                <artifactId>spring-boot-maven-buildsection</artifactId>\n" +
//                "                <executions>\n" +
//                "                    <execution>\n" +
//                "                        <goals>\n" +
//                "                            <goal>repackage</goal>\n" +
//                "                        </goals>\n" +
//                "                    </execution>\n" +
//                "                </executions>\n" +
//                "            </buildsection>\n" +
//                "            <buildsection>\n" +
//                "                <groupId>org.apache.maven.plugins</groupId>\n" +
//                "                <artifactId>maven-compiler-buildsection</artifactId>\n" +
//                "                <version>3.8.1</version>\n" +
//                "\n" +
//                "                <configuration>\n" +
//                "                    <source>${java.version}</source>\n" +
//                "                    <target>${java.version}</target>\n" +
//                "                    <annotationProcessorPaths>\n" +
//                "                        <path>\n" +
//                "                            <groupId>org.projectlombok</groupId>\n" +
//                "                            <artifactId>lombok</artifactId>\n" +
//                "                            <version>${lombok.version}</version>\n" +
//                "                        </path>\n" +
//                "                        <path>\n" +
//                "                            <groupId>org.mapstruct</groupId>\n" +
//                "                            <artifactId>mapstruct-processor</artifactId>\n" +
//                "                            <version>${org.mapstruct.version}</version>\n" +
//                "                        </path>\n" +
//                "                    </annotationProcessorPaths>\n" +
//                "                    <compilerArgs>\n" +
//                "                        <compilerArg>\n" +
//                "                            -Amapstruct.defaultComponentModel=spring\n" +
//                "                        </compilerArg>\n" +
//                "                    </compilerArgs>\n" +
//                "                </configuration>\n" +
//                "            </buildsection>\n" +
//                "            <buildsection>\n" +
//                "                <groupId>org.liquibase</groupId>\n" +
//                "                <artifactId>liquibase-maven-buildsection</artifactId>\n" +
//                "                <version>3.6.3</version>\n" +
//                "                <configuration>\n" +
//                "                    <propertyFile>${project.basedir}/src/main/resources/liquibase.properties</propertyFile>\n" +
//                "                </configuration>\n" +
//                "            </buildsection>\n" +
//                "        </plugins>\n" +
//                "    </buildsection>\n" +
//                "\n" +
//                "</project>\n");
//
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(pomData.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void buildApiPomFile(String path) {
//        File file = new File(path + "pom.xml");
//        StringBuilder pomData = new StringBuilder();
//
//        pomData.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
//                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
//                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
//                "    <modelVersion>4.0.0</modelVersion>\n" +
//                "    <parent>\n" +
//                "        <groupId>"
//                + request.getPackageName()
//                .substring(0, request.getPackageName().length() - request.getSourceName().length())
//                .replaceAll("/", ".")
//                +  "</groupId>\n" +
//                "        <artifactId>" + request.getSourceName() + "</artifactId>\n" +
//                "        <version>0.0.1</version>\n" +
//                "    </parent>\n" +
//                "    <packaging>jar</packaging>\n" +
//                "\n" +
//                "    <groupId>"
//                + request.getPackageName()
//                .substring(0, request.getPackageName().length() - request.getSourceName().length())
//                .replaceAll("/", ".")
//                + "</groupId>\n" +
//                "    <artifactId>" + request.getSourceName() + "-api</artifactId>\n" +
//                "\n" +
//                "    <dependencies>\n" +
//                "        <dependency>\n" +
//                "            <groupId>org.projectlombok</groupId>\n" +
//                "            <artifactId>lombok</artifactId>\n" +
//                "            <optional>true</optional>\n" +
//                "            <version>${lombok.version}</version>\n" +
//                "        </dependency>\n" +
//                "\n" +
//                "        <dependency>\n" +
//                "            <groupId>validation.validation</groupId>\n" +
//                "            <artifactId>validation.validation-api</artifactId>\n" +
//                "            <version>${validation.validation.version}</version>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>com.fasterxml.jackson.core</groupId>\n" +
//                "            <artifactId>jackson-annotations</artifactId>\n" +
//                "            <version>${jackson.version}</version>\n" +
//                "        </dependency>\n" +
//                "        <dependency>\n" +
//                "            <groupId>io.swagger.core.v3</groupId>\n" +
//                "            <artifactId>swagger-annotations</artifactId>\n" +
//                "            <version>${swagger.core.version}</version>\n" +
//                "        </dependency>\n" +
//                "    </dependencies>\n" +
//                "\n" +
//                "    <buildsection>\n" +
//                "        <plugins>\n" +
//                "            <buildsection>\n" +
//                "                <groupId>org.apache.maven.plugins</groupId>\n" +
//                "                <artifactId>maven-compiler-buildsection</artifactId>\n" +
//                "                <version>3.8.1</version>\n" +
//                "                <configuration>\n" +
//                "                    <source>${java.version}</source>\n" +
//                "                    <target>${java.version}</target>\n" +
//                "                </configuration>\n" +
//                "            </buildsection>\n" +
//                "        </plugins>\n" +
//                "    </buildsection>\n" +
//                "</project>");
//
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(pomData.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void buildMainPomFile(String path) {
//        File file = new File(path + "pom.xml");
//        StringBuilder pomData = new StringBuilder();
//
//        pomData.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
//                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
//                "    <modelVersion>4.0.0</modelVersion>\n" +
//                "    <groupId>"
//                + request.getPackageName()
//                .substring(0, request.getPackageName().length() - request.getSourceName().length())
//                .replaceAll("/", ".")
//                +  "</groupId>\n" +
//                "    <artifactId>" + request.getSourceName() + "</artifactId>\n" +
//                "    <version>0.0.1</version>\n" +
//                "    <name>" + request.getSourceName() + "</name>\n" +
//                "    <packaging>pom</packaging>\n" +
//                "\n" +
//                "    <modules>\n" +
//                "        <module>" + request.getSourceName() + "-api</module>\n" +
//                "        <module>" + request.getSourceName() + "-core</module>\n" +
//                "    </modules>\n" +
//                "\n" +
//                "    <properties>\n" +
//                "        <java.version>11</java.version>\n" +
//                "        <spring.version>5.2.9.RELEASE</spring.version>\n" +
//                "\n" +
//                "        <lombok.version>1.18.12</lombok.version>\n" +
//                "        <org.mapstruct.version>1.3.1.Final</org.mapstruct.version>\n" +
//                "\n" +
//                "        <validation.validation.version>2.0.2</validation.validation.version>\n" +
//                "        <jackson.version>2.11.2</jackson.version>\n" +
//                "        <swagger.core.version>2.1.2</swagger.core.version>\n" +
//                "        <springdoc.version>1.3.9</springdoc.version>\n" +
//                "    </properties>\n" +
//                "\n" +
//                "    <dependencyManagement>\n" +
//                "        <dependencies>\n" +
//                "            <dependency>\n" +
//                "                <groupId>org.springframework.boot</groupId>\n" +
//                "                <artifactId>spring-boot-dependencies</artifactId>\n" +
//                "                <version>2.3.4.RELEASE</version>\n" +
//                "                <type>pom</type>\n" +
//                "                <scope>import</scope>\n" +
//                "            </dependency>\n" +
//                "        </dependencies>\n" +
//                "    </dependencyManagement>\n" +
//                "\n" +
//                "    <buildsection>\n" +
//                "        <plugins>\n" +
//                "            <buildsection>\n" +
//                "                <groupId>org.apache.maven.plugins</groupId>\n" +
//                "                <artifactId>maven-compiler-buildsection</artifactId>\n" +
//                "                <version>3.8.1</version>\n" +
//                "\n" +
//                "                <configuration>\n" +
//                "                    <source>${java.version}</source>\n" +
//                "                    <target>${java.version}</target>\n" +
//                "                </configuration>\n" +
//                "            </buildsection>\n" +
//                "        </plugins>\n" +
//                "    </buildsection>\n" +
//                "</project>\n");
//
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(pomData.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void buildApplicationFile(String corePackagePath, String mainEntityName) {
//        File file = new File(corePackagePath + "/" + mainEntityName + "Application.java");
//        StringBuilder launchData = new StringBuilder();
//
//        String corePackageName = request.getPackageName().replaceAll("/", ".");
//
//        launchData.append("package " + corePackageName + ";\n\n");
//
//        launchData.append("import io.swagger.v3.oas.models.*;\n");
//        launchData.append("import io.swagger.v3.oas.models.info.Info;\n");
//        launchData.append("import org.springframework.boot.SpringApplication;\n");
//        launchData.append("import org.springframework.boot.autoconfigure.SpringBootApplication;;\n");
//        launchData.append("import org.springframework.context.annotation.*;\n\n");
//
//        launchData.append("@PropertySource({\n" +
//                "        \"classpath:message.properties\"\n" +
//                "})\n");
//        launchData.append("@SpringBootApplication\n");
//        launchData = openClass(launchData, file.getName());
//
//        launchData.append("\tpublic static void main(String[] args) {\n\tSpringApplication.run("
//                + file.getName().substring(0, file.getName().length() - 5)
//                + ".class, args);\n\t}\n\n");
//
//        launchData.append("\n" +
//                "    @Bean\n" +
//                "    public OpenAPI customOpenAPI() {\n" +
//                "        return new OpenAPI()\n" +
//                "                .components(new Components())\n" +
//                "                .info(new Info()\n" +
//                "                        .title(\"\"));\n" +
//                "    }");
//
//        closeClassAndWriteToFile(launchData, file);
//    }
//
//    private void buildMapperFiles(String mapperPath, List<ClassEntity> secondaryEntities) {
//        for (ClassEntity codeentity : secondaryEntities) {
//            File file = new File(mapperPath + codeentity.getName() + "Mapper.java");
//            StringBuilder mapperData = new StringBuilder();
//
//            String corePackageName = request.getPackageName().replaceAll("/", ".");
//
//            mapperData.append("package " + corePackageName + ".mapper;\n\n");
//
//            mapperData.append("import " + corePackageName + ".codeentity." + codeentity.getName() + ";\n");
//            mapperData.append("import " + corePackageName + ".dto." + codeentity.getName() + "Dto;\n");
//            mapperData.append("import org.mapstruct.*;\n");
//            mapperData.append("import java.util.*;\n\n");
//
//            mapperData.append("@Mapper(componentModel = \"spring\")\n");
//            mapperData = openInterface(mapperData, file.getName());
//
//            mapperData.append("\t" + codeentity.getName() + "Dto toDto(" + codeentity.getName() + " codeentity);\n");
//            mapperData.append("\t" + codeentity.getName() + " toEntity(" + codeentity.getName() + "Dto dto);\n");
//            mapperData.append("\tList<" + codeentity.getName() + "Dto> toDtoList(List<" + codeentity.getName() + "> entityList);\n");
//
//            closeClassAndWriteToFile(mapperData, file);
//        }
//    }
//
//    private void buildRepositoryFiles(String repositoryPath, List<ClassEntity> secondaryEntities) {
//        for (ClassEntity codeentity : secondaryEntities) {
//            File file = new File(repositoryPath + codeentity.getName() + "Repository.java");
//            StringBuilder repositoryData = new StringBuilder();
//
//            String corePackageName = request.getPackageName().replaceAll("/", ".");
//
//            repositoryData.append("package " + corePackageName + ".repository;\n\n");
//
//            repositoryData.append("import " + corePackageName + ".codeentity." + codeentity.getName() + ";\n");
//            repositoryData.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
//            repositoryData.append("import java.util.*;\n\n");
//
//            repositoryData = openRepository(repositoryData, file.getName(), codeentity.getName(), codeentity.getFields().get(0).getType());
//
//            repositoryData.append("\tList<" + codeentity.getName() + "> findByDeleted(boolean deleted);\n");
//            repositoryData.append("\tOptional<" + codeentity.getName() + "> findByIdAndDeleted(" + codeentity.getFields().get(0).getType() + " id, boolean deleted);\n");
//
//            closeClassAndWriteToFile(repositoryData, file);
//        }
//    }
//
//    private void buildServiceFiles(String servicePath, String ... secondaryEntities) {
//        for (String entityName : secondaryEntities) {
//            File file = new File(servicePath + entityName + "Service.java");
//            StringBuilder serviceData = new StringBuilder();
//
//            String corePackageName = request.getPackageName().replaceAll("/", ".");
//
//            serviceData.append("package " + corePackageName + ".service;\n\n");
//
//            serviceData.append("import " + corePackageName + ".repository." + entityName + "Repository;\n");
//            serviceData.append("import " + corePackageName + ".mapper." + entityName + "Mapper;\n");
//            serviceData.append("import " + corePackageName + ".codeentity." + entityName + ";\n");
//            serviceData.append("import " + corePackageName + ".dto." + entityName + "Dto;\n\n");
//
//            serviceData.append("import java.util.*;\n");
//            serviceData.append("import org.springframework.beans.factory.annotation.*;\n");
//            serviceData.append("import org.springframework.data.domain.*;\n");
//            serviceData.append("import org.springframework.http.*;\n");
//            serviceData.append("import org.springframework.stereotype.Service;\n");
//            serviceData.append("import org.springframework.transaction.annotation.Transactional;\n");
//            serviceData.append("import org.springframework.web.server.ResponseStatusException;\n");
//            serviceData.append("import org.springframework.dao.EmptyResultDataAccessException;\n");
//            serviceData.append("import lombok.extern.slf4j.Slf4j;\n\n");
//
//            serviceData.append("@Slf4j\n");
//            serviceData.append("@Service\n");
//            serviceData = openClass(serviceData, file.getName());
//
//            serviceData = writeServiceConstructor(serviceData, entityName);
//
//            serviceData = writeFindAllService(serviceData, entityName);
//
//            serviceData = writeGetService(serviceData, entityName);
//
//            serviceData = writeCreateService(serviceData, entityName);
//
//            serviceData = writeUpdateService(serviceData, entityName);
//
//            serviceData = writeSoftDeleteService(serviceData, entityName);
//
//            serviceData = writeHardDeleteService(serviceData);
//
//            closeClassAndWriteToFile(serviceData, file);
//        }
//    }
//
//    private StringBuilder writeHardDeleteService(StringBuilder serviceData) {
//        serviceData.append("    public ResponseEntity<String> deleteHardly(UUID id) {\n" +
//                "        try {\n" +
//                "            repository.deleteById(id);\n" +
//                "        } catch (EmptyResultDataAccessException e) {\n" +
//                "            return new ResponseEntity<>(\n" +
//                "                    \"The codeentity with ID: \" + id + \" wasn't found in DB\",\n" +
//                "                    HttpStatus.NOT_FOUND\n" +
//                "            );\n" +
//                "        } catch (Exception e) {\n" +
//                "            return new ResponseEntity<>(\n" +
//                "                    \"Something went wrong!\",\n" +
//                "                    HttpStatus.INTERNAL_SERVER_ERROR\n" +
//                "            );\n" +
//                "        }\n" +
//                "        return new ResponseEntity<>(\n" +
//                "                \"The codeentity with ID: \" + id + \" was successfully deleted from DB\",\n" +
//                "                HttpStatus.OK\n" +
//                "        );\n" +
//                "    }\n");
//
//        return serviceData;
//    }
//
//    private StringBuilder writeSoftDeleteService(StringBuilder serviceData, String entityName) {
//        serviceData.append("    @Transactional\n" +
//                "    public " + entityName + "Dto deleteSoftly(UUID id) {\n" +
//                "        " + entityName + " entityFromDB = repository.findById(id)\n" +
//                "                .orElseThrow(() -> { throw new ResponseStatusException(\n" +
//                "                        HttpStatus.NOT_FOUND,\n" +
//                "                        \"The codeentity with ID: \" + id + \" wasn't found in DB\"); });\n" +
//                "        entityFromDB.setDeleted(true);\n" +
//                "\n" +
//                "        return mapper.toDto(\n" +
//                "                repository.save(entityFromDB));\n" +
//                "    }\n\n");
//
//        return serviceData;
//    }
//
//    private StringBuilder writeUpdateService(StringBuilder serviceData, String entityName) {
//        serviceData.append("    public " + entityName + "Dto update(UUID id, " + entityName + "Dto dto) {\n" +
//                "        if (dto.getId() != null\n" +
//                "                && !dto.getId().equals(id)) {\n" +
//                "            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, idsDoNotMatchMessage);\n" +
//                "        }\n" +
//                "        dto.setId(id);\n" +
//                "\n" +
//                "        return mapper.toDto(\n" +
//                "                repository.save(\n" +
//                "                        mapper.toEntity(dto)));\n" +
//                "    }\n\n");
//
//        return serviceData;
//    }
//
//    private StringBuilder writeCreateService(StringBuilder serviceData, String entityName) {
//        serviceData.append("    public " + entityName + "Dto save(" + entityName + "Dto dto) {\n" +
//                "        return mapper.toDto(\n" +
//                "                repository.save(\n" +
//                "                        mapper.toEntity(dto)));\n" +
//                "    }\n\n");
//
//        return serviceData;
//    }
//
//    private StringBuilder writeGetService(StringBuilder serviceData, String entityName) {
//        serviceData.append("    public " + entityName + "Dto findById(UUID id) {\n" +
//                "        return mapper.toDto(\n" +
//                "                repository.findByIdAndDeleted(id, false)\n" +
//                "                        .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); }));\n" +
//                "    }\n\n");
//
//        return serviceData;
//    }
//
//    private StringBuilder writeFindAllService(StringBuilder serviceData, String entityName) {
//        serviceData.append("    public Page<" + entityName + "Dto> findAll(Pageable pageable, boolean deleted) {\n" +
//                "        List<" + entityName + "Dto> dtoList;\n" +
//                "\n" +
//                "        if (deleted) {\n" +
//                "            dtoList = mapper.toDtoList(repository.findAll());\n" +
//                "        } else {\n" +
//                "            dtoList = mapper.toDtoList(repository.findByDeleted(false));\n" +
//                "        }\n" +
//                "\n" +
//                "        int start = (int) pageable.getOffset();\n" +
//                "        int end = (start + pageable.getPageSize()) > dtoList.size() ? dtoList.size()\n" +
//                "                : (start + pageable.getPageSize());\n" +
//                "\n" +
//                "        return new PageImpl<>(\n" +
//                "                dtoList.subList(start, end),\n" +
//                "                pageable,\n" +
//                "                dtoList.size());\n" +
//                "    }\n\n");
//
//        return serviceData;
//    }
//
//    private StringBuilder writeServiceConstructor(StringBuilder serviceData, String entityName) {
//        serviceData.append("    private final " + entityName + "Repository repository;\n" +
//                "    private final " + entityName + "Mapper mapper;\n" +
//                "\n" +
//                "    @Value(\"${ids.do.not.match.message}\")\n" +
//                "    private String idsDoNotMatchMessage;\n" +
//                "\n" +
//                "    @Autowired\n" +
//                "    public " + entityName + "Service(\n" +
//                "            " + entityName + "Repository repository,\n" +
//                "            " + entityName + "Mapper mapper) {\n" +
//                "        this.repository = repository;\n" +
//                "        this.mapper = mapper;\n" +
//                "    }\n\n");
//
//        return serviceData;
//    }
//
//    private void buildControllerFiles(String controllerPath, String ... secondaryEntities) {
//        for (String entityName : secondaryEntities) {
//            File file = new File(controllerPath + entityName + "Controller.java");
//            StringBuilder controllerData = new StringBuilder();
//
//            String corePackageName = request.getPackageName().replaceAll("/", ".");
//
//            controllerData.append("package " + corePackageName + ".controller;\n\n");
//
//            controllerData.append("import " + corePackageName + ".service." + entityName + "Service;\n");
//            controllerData.append("import " + corePackageName + ".dto." + entityName + "Dto;\n\n");
//
//            controllerData.append("import java.util.*;\n");
//            controllerData.append("import org.springframework.beans.factory.annotation.Autowired;\n");
//            controllerData.append("import org.springframework.data.domain.*;\n");
//            controllerData.append("import org.springframework.data.web.PageableDefault;\n");
//            controllerData.append("import org.springframework.http.ResponseEntity;\n");
//            controllerData.append("import org.springframework.web.bind.annotation.*;\n");
//            controllerData.append("import io.swagger.v3.oas.annotations.Operation;\n");
//            controllerData.append("import io.swagger.v3.oas.annotations.Parameter;\n");
//            controllerData.append("import io.swagger.v3.oas.annotations.responses.ApiResponse;\n");
//            controllerData.append("import io.swagger.v3.oas.annotations.responses.ApiResponses;\n");
//            controllerData.append("import io.swagger.v3.oas.annotations.tags.Tag;\n");
//            controllerData.append("import lombok.extern.slf4j.Slf4j;\n\n");
//
//            controllerData.append("@Slf4j\n");
//            controllerData.append("@Tag(name = \"\")\n");
//            controllerData.append("@RestController\n");
//            controllerData.append("@RequestMapping(\"\")\n");
//            controllerData = openClass(controllerData, file.getName());
//
//            controllerData = writeControllerConstructor(controllerData, entityName);
//
//            controllerData = writeSwagger(controllerData);
//            controllerData = writeFindAllController(controllerData, entityName);
//
//            controllerData = writeSwagger(controllerData);
//            controllerData = writeGetController(controllerData, entityName);
//
//            controllerData = writeSwagger(controllerData);
//            controllerData = writeCreateController(controllerData, entityName);
//
//            controllerData = writeSwagger(controllerData);
//            controllerData = writeUpdateController(controllerData, entityName);
//
//            controllerData = writeSwagger(controllerData);
//            controllerData = writeSoftDeleteController(controllerData, entityName);
//
//            controllerData = writeSwagger(controllerData);
//            controllerData = writeHardDeleteController(controllerData);
//
//            closeClassAndWriteToFile(controllerData, file);
//        }
//    }
//
//    private StringBuilder writeSwagger(StringBuilder controllerData) {
//        controllerData.append("    @Operation(summary = \"\")\n" +
//                "    @ApiResponses(value = {\n" +
//                "            @ApiResponse(responseCode = \"200\", description = \"\"),\n" +
//                "            @ApiResponse(responseCode = \"500\", description = \"Ошибка сервера\")\n" +
//                "    })\n");
//
//        return controllerData;
//    }
//
//    private StringBuilder writeHardDeleteController(StringBuilder controllerData) {
//        controllerData.append("    @DeleteMapping(value = \"/{id}/hard_delete\", produces = \"text/plain\")\n" +
//                "    public ResponseEntity<String> deleteHardly(@PathVariable(\"id\") UUID id\n) {\n" +
//                "        return service.deleteHardly(id);\n" +
//                "    }\n\n");
//
//        return controllerData;
//    }
//
//    private StringBuilder writeSoftDeleteController(StringBuilder controllerData, String entityName) {
//        controllerData.append("    @DeleteMapping(value = \"/{id}/soft_delete\", produces = \"application/json\")\n" +
//                "    public " + entityName + "Dto deleteSoftly(@PathVariable(\"id\") UUID id\n) {\n" +
//                "        return service.deleteSoftly(id);\n" +
//                "    }\n\n");
//
//        return controllerData;
//    }
//
//    private StringBuilder writeUpdateController(StringBuilder controllerData, String entityName) {
//        controllerData.append("    @PutMapping(value = \"/{id}\", produces = \"application/json\")\n" +
//                "    public " + entityName + "Dto update(\n" +
//                "            @Parameter(description = \"\", required = true, example = \"\")\n" +
//                "            @PathVariable(\"id\") UUID id,\n" +
//                "            @Parameter(required = true)\n" +
//                "            @RequestBody " + entityName + "Dto dto\n) {\n" +
//                "        return service.update(id, dto);\n" +
//                "    }\n\n");
//
//        return controllerData;
//    }
//
//    private StringBuilder writeCreateController(StringBuilder controllerData, String entityName) {
//        controllerData.append("    @PostMapping(produces = \"application/json\")\n" +
//                "    public " + entityName + "Dto create(\n" +
//                "            @Parameter(required = true)\n" +
//                "            @RequestBody " + entityName + "Dto dto\n) {\n" +
//                "        return service.save(dto);\n" +
//                "    }\n\n");
//
//        return controllerData;
//    }
//
//    private StringBuilder writeGetController(StringBuilder controllerData, String entityName) {
//        controllerData.append("    @GetMapping(value = \"/{id}\", produces = \"application/json\")\n" +
//                "    public " + entityName + "Dto get(\n" +
//                "            @Parameter(description = \"\", required = true, example = \"\")\n" +
//                "            @PathVariable(\"id\") UUID id\n" +
//                "    ) {\n" +
//                "        return service.findById(id);\n" +
//                "    }\n\n");
//
//        return controllerData;
//    }
//
//    private StringBuilder writeFindAllController(StringBuilder controllerData, String entityName) {
//        controllerData.append("    @GetMapping(produces = \"application/json\")\n" +
//                "    public Page<" + entityName + "Dto> findAll(\n" +
//                "            @RequestParam(required = false, defaultValue = \"false\")\n" +
//                "                    boolean deleted,\n" +
//                "            @PageableDefault(sort = { \"id\" }, direction = Sort.Direction.DESC)\n" +
//                "                    Pageable pageable\n" +
//                "    ) {\n" +
//                "        return service.findAll(pageable, deleted);\n" +
//                "    }\n\n");
//
//        return controllerData;
//    }
//
//    private StringBuilder writeControllerConstructor(StringBuilder controllerData, String entityName) {
//        controllerData.append("    private final " + entityName + "Service service;\n" +
//                "\n" +
//                "    @Autowired\n" +
//                "    public " + entityName + "Controller(" + entityName + "Service service) {\n" +
//                "        this.service = service;\n" +
//                "    }\n\n");
//        return controllerData;
//    }
//
//    private void buildDtoFiles(String dtoPackagePath) {
//        for (ClassEntity codeentity : request.getSecondaryEntities()) {
//            File file = new File(dtoPackagePath + codeentity.getName() + "Dto.java");
//            StringBuilder dtoData = new StringBuilder();
//
//            String dtoPackageName = request.getPackageName().replaceAll("/", ".");
//
//            dtoData.append("package " + dtoPackageName + ".dto;\n\n");
//            dtoData.append("import java.util.*;\n");
//            dtoData.append("import java.time.*;\n\n");
//            dtoData.append("import com.fasterxml.jackson.annotation.JsonIgnore;\n\n");
//            dtoData.append("import com.fasterxml.jackson.annotation.JsonProperty;\n\n");
//            dtoData.append("import lombok.Data;\n\n");
//
//            dtoData.append("@Data\n");
//            dtoData = openClass(dtoData, file.getName());
//
//
//            for (Field field : codeentity.getFields()) {
//                dtoData.append("\t");
//                dtoData.append("@JsonProperty");
//                dtoData.append("(\"" + field.getName() + "\")\n");
//
//                dtoData = writeField(dtoData, field);
//            }
//            dtoData.append("\t@JsonIgnore\n" +
//                    "\tprivate Boolean deleted = Boolean.FALSE;\n");
//
//            closeClassAndWriteToFile(dtoData, file);
//        }
//    }
//
//    private void buildDomainFiles(String domainPackagePath) {
//        for (ClassEntity codeentity : request.getSecondaryEntities()) {
//            File file = new File(domainPackagePath + codeentity.getName() + ".java");
//            StringBuilder data = new StringBuilder();
//
//            String corePackageName = request.getPackageName().replaceAll("/", ".");
//
//            data.append("package " + corePackageName + ".codeentity;\n\n");
//            data.append("import java.util.*;\n");
//            data.append("import java.time.*;\n\n");
//            data.append("import javax.persistence.*;\n\n");
//            data.append("import lombok.Data;\n\n");
//
//            data.append("@Data\n");
//            data.append("@ClassEntity\n");
//            data = openClass(data, file.getName());
//
//            for (Field field : codeentity.getFields()) {
//                data.append("\t");
//                if (field.getName().equals("id")) {
//                    data.append("@GeneratedValue\n");
//                    data.append("\t");
//                    data.append("@Id\n");
//                } else {
//                    data.append("@Column\n");
//                }
//                data.append(" ");
//
//                data = writeField(data, field);
//            }
//            data.append("\t@Column\n\tprivate Boolean deleted;\n");
//
//            closeClassAndWriteToFile(data, file);
//        }
//    }
//
//    private StringBuilder openClass(StringBuilder data, String fileName) {
//        data.append("public");
//        data.append(" ");
//        data.append("class");
//        data.append(" ");
//        data.append(fileName, 0, fileName.length() - 5);
//        data.append(" ");
//        data.append("{");
//        data.append("\n");
//        return data;
//    }
//
//    private StringBuilder openInterface(StringBuilder data, String fileName) {
//        data.append("public");
//        data.append(" ");
//        data.append("interface");
//        data.append(" ");
//        data.append(fileName, 0, fileName.length() - 5);
//        data.append(" ");
//        data.append("{");
//        data.append("\n");
//        return data;
//    }
//
//    private StringBuilder openRepository(StringBuilder data, String fileName, String entityName, String idName) {
//        data.append("public");
//        data.append(" ");
//        data.append("interface");
//        data.append(" ");
//        data.append(fileName, 0, fileName.length() - 5);
//        data.append(" ");
//        data.append("extends");
//        data.append(" ");
//        data.append("JpaRepository");
//        data.append("<");
//        data.append(entityName);
//        data.append(", ");
//        data.append(idName);
//        data.append(">");
//        data.append(" ");
//        data.append("{");
//        data.append("\n");
//        return data;
//    }
//
//    private void closeClassAndWriteToFile(StringBuilder data, File file) {
//        data.append("}");
//
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(data.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private StringBuilder writeField(StringBuilder data, Field field) {
//        if (Objects.isNull(field.getModifiers()) || field.getModifiers().isEmpty()) {
//            data.append("\t");
//            data.append("private");
//            data.append(" ");
//
//        } else {
//            for (String modifier : field.getModifiers()) {
//                data.append(modifier);
//                data.append(" ");
//            }
//        }
//        data.append(field.getType());
//        data.append(" ");
//        data.append(field.getName());
//        data.append(";");
//        data.append("\n");
//
//        return data;
//    }
}
