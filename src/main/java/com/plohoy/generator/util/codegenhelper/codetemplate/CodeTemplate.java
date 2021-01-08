package com.plohoy.generator.util.codegenhelper.codetemplate;

import com.plohoy.generator.model.EntryPointType;
import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.*;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import java.util.*;
import java.util.stream.Collectors;

import static com.plohoy.generator.util.stringhelper.list.DelimiterType.DOT;

public class CodeTemplate {
    public static final String INDENT = "\n";
    public static final String TAB = "\t";
    public static final String EMPTY_STRING = "";
    public static final String NULL_STRING = "null";
    public static final String SPACE_SYMBOL = " ";
    public static final String SLASH_SYMBOL = "/";
    public static final String QUOTE = "\"";
    public static final String ENUMERATION_DELIMITER = ",";
    public static final String CODE_DELIMITER = ";";
    public static final String OPEN_BODY_BRACKET = "{";
    public static final String CLOSE_BODY_BRACKET = "}";
    public static final String OPEN_PARAM_BRACKET = "(";
    public static final String CLOSE_PARAM_BRACKET = ")";
    public static final String EQUALS = "=";
    public static final String PUBLIC_ACCESS_MODIFIER = "public";
    public static final String PRIVATE_ACCESS_MODIFIER = "private";
    public static final String DEFAULT_RETURN_TYPE = "void";
    public static final String IMPORT_WORD = "import";
    public static final String EXTEND_WORD = "extends";
    public static final String IMPLEMENT_WORD = "implements";
    public static final String THROW_WORD = "throws";
    public static final String STATIC_WORD = "static";
    public static final String FINAL_WORD = "final";
    public static final String PACKAGE_WORD = "package";
    public static final String ANNOTATION_MARK = "@";

    public static final String MAIN_METHOD_NAME = "main";
    public static final String MAIN_METHOD_ARGS_TYPE = "String[]";
    public static final String MAIN_METHOD_ARGS_VALUE = "args";

    public static final String DEFAULT_JAVA_EXTENSION = ".java";
    public static final String LAUNCHER_SUFFIX = "Application";
    public static final String CONTROLLER_SUFFIX = "Controller";
    public static final String SERVICE_SUFFIX = "Service";
    public static final String MAPPER_SUFFIX = "Mapper";
    public static final String REPO_SUFFIX = "Repository";
    public static final String STRATEGY_SUFFIX = "Strategy";
    public static final String DTO_SUFFIX = "Dto";
    public static final String DAO_SUFFIX = "Dao";
    public static final String CONFIG_SUFFIX = "Config";
    public static final String ENTITY_SUFFIX = "RequestEntity";
    public static final String EXCEPTION_SUFFIX= "Exception";

    public static final String APPLICATION_PROPERTIES_NAME = "application.properties";
    public static final String APPLICATION_DEV_PROPERTIES_NAME = "application-dev.properties";
    public static final String APPLICATION_TEST_PROPERTIES_NAME = "application-test.properties";
    public static final String MESSAGE_PROPERTIES_NAME = "message.properties";
    public static final String DB_PROPERTIES_NAME = "db.properties";
    public static final String URL_PROPERTIES_NAME = "url.properties";

    public static final String CLASSPATH_NAME = "classpath:";

    public IndentList<MethodEntity> generateMainMethod(String bodyCode) {
        return new IndentList<>(DelimiterType.INDENT, false,
                Arrays.asList(MethodEntity.builder()
                                .modifiers(new EnumerationList<>(DelimiterType.NONE, true,
                                        Arrays.asList(PUBLIC_ACCESS_MODIFIER, STATIC_WORD)
                                ))
                                .returnType(DEFAULT_RETURN_TYPE)
                                .name(MAIN_METHOD_NAME)
                                .args(new EnumerationList<>(DelimiterType.COMMA, false,
                                        Arrays.asList(ArgumentEntity.builder()
                                                        .type(MAIN_METHOD_ARGS_TYPE)
                                                        .name(MAIN_METHOD_ARGS_VALUE)
                                                        .build()
                                        )
                                ))
                                .body(bodyCode)
                                .build()
                )
        );
    }

    public IndentList<AnnotationEntity> generateSpringBootLauncherAnnotations() {
        return new IndentList<>(DelimiterType.NONE, true,
                Arrays.asList(
                        AnnotationEntity.builder()
                                .name("PropertySource")
                                .properties(new EnumerationList<>(DelimiterType.COMMA, false,
                                        Arrays.asList(PropertyEntity.builder()
                                            .values(PropertyValueEntity.builder()
                                                        .values(new IndentList<>(DelimiterType.COMMA, false,
                                                                Arrays.asList(
                                                                        CLASSPATH_NAME + MESSAGE_PROPERTIES_NAME,
                                                                        CLASSPATH_NAME + DB_PROPERTIES_NAME
                                                                )
                                                        ))
                                                        .build())
                                            .build()))
                                        )
                                .build(),
                        AnnotationEntity.builder()
                                .name("SpringBootApplication")
                                .build()
                )
        );
    }

    public String generateSpringLauncherBody(String className) {
        return String.format(
                "SpringApplication.run(%s.class, args);",
                className
        );
    }

    public IndentList<ImportEntity> generateSpringBootLauncherImports() {
        return new IndentList<>(DelimiterType.SEMICOLON, true,
                Arrays.asList(
                        ImportEntity.builder()
                                .value("org.springframework.boot.SpringApplication")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.boot.autoconfigure.SpringBootApplication")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.context.annotation.PropertySource")
                                .build()
                )
        );
    }

    public IndentList<ImportEntity> generateSpringDataJpaImports(String corePackageName, String entityName) {
        return new IndentList<>(DelimiterType.SEMICOLON, true,
                Arrays.asList(
                        ImportEntity.builder()
                                .value("org.springframework.data.jpa.repository.JpaRepository")
                                .build(),
                        ImportEntity.builder()
                                .value(corePackageName + DOT.getDelimiter() + ENTITY_SUFFIX.toLowerCase() + DOT.getDelimiter()  + entityName)
                                .build(),
                        ImportEntity.builder()
                                .value("java.util.List")
                                .build(),
                        ImportEntity.builder()
                                .value("java.util.Optional")
                                .build(),
                        ImportEntity.builder()
                                .value("java.util.UUID")
                                .build()
                )
        );
    }

    public String getPackageString(String corePackageName) {
        return PACKAGE_WORD + SPACE_SYMBOL + corePackageName + CODE_DELIMITER + getIndent(2);
    }

    public static String getIndent(int indentNumber) {
        String result = EMPTY_STRING;
        for (int i = 0; i < indentNumber; i++) {
            result += INDENT;
        }
        return result;
    }

    public static String getTab(int tabNumber) {
        String result = EMPTY_STRING;
        for (int i = 0; i < tabNumber; i++) {
            result += TAB;
        }
        return result;
    }

    public static String getTab(int currentTab, CodeEntity codeEntity) {
        CodeEntity currentEntity = codeEntity.getParentEntity();

        while (Objects.nonNull(currentEntity)) {
            currentTab += currentEntity.getNestLvl();
            currentEntity = currentEntity.getParentEntity();
        }

        return getTab(currentTab);
    }

    public static String getIndent() {
        return getIndent(1);
    }

    public static String getTab() {
        return getTab(1);
    }

    public static EnumerationList<String> getPublicModifier() {
        return new EnumerationList<>(DelimiterType.NONE, true,
                Arrays.asList(PUBLIC_ACCESS_MODIFIER));
    }

    public EnumerationList<String> getPrivateModifier() {
        return new EnumerationList<>(DelimiterType.NONE, true,
                Arrays.asList(PRIVATE_ACCESS_MODIFIER));
    }

    public String getJpaRepositoryClassName(ClassEntity mainEntity) {
        return String.format(
                "JpaRepository<%s, %s>",
                mainEntity.getName(),
                mainEntity.getFields()
                        .stream()
                        .filter(element -> "id".equals(element.getName()))
                        .map(element -> element.getType())
                        .collect(Collectors.toList()).get(0));
    }

    public IndentList<MethodEntity> generateRepoDeleteMethods(ClassEntity mainEntity) {
        return new IndentList<>(DelimiterType.NONE, true,
                Arrays.asList(
                        MethodEntity.builder()
                                .returnType(String.format("List<%s>", mainEntity.getName()))
                                .name("findByDeleted")
                                .args(new EnumerationList<>(DelimiterType.COMMA, false,
                                        Arrays.asList(ArgumentEntity.builder()
                                                .type("boolean")
                                                .name("deleted")
                                                .build()
                                        )
                                ))
                                .build(),
                        MethodEntity.builder()
                                .returnType(String.format("Optional<%s>", mainEntity.getName()))
                                .name("findByIdAndDeleted")
                                .args(new EnumerationList<>(DelimiterType.COMMA, false,
                                        Arrays.asList(
                                                ArgumentEntity.builder()
                                                        .type(mainEntity.getFields()
                                                                .stream()
                                                                .filter(element -> "id".equals(element.getName()))
                                                                .map(element -> element.getType())
                                                                .collect(Collectors.toList()).get(0))
                                                        .name("id")
                                                        .build(),
                                                ArgumentEntity.builder()
                                                        .type("boolean")
                                                        .name("deleted")
                                                        .build()
                                        )
                                ))
                                .build()
                )
        );
    }

    public IndentList<AnnotationEntity> generateSpringRestControllerAnnotations(String controllerPath) {
        return new IndentList<>(DelimiterType.NONE, true,
                Arrays.asList(
                        AnnotationEntity.builder()
                                .name("RestController")
                                .build(),
                        AnnotationEntity.builder()
                                .name("RequestMapping")
                                .value(controllerPath)
                                .build(),
                        AnnotationEntity.builder()
                                .name("RequiredArgsConstructor")
                                .build()
                ));
    }

    public IndentList<ImportEntity> generateSpringRestControllerImports(String corePackageName, String entityName) {
        return new IndentList<>(DelimiterType.SEMICOLON, true,
                Arrays.asList(
                        ImportEntity.builder()
                                .value(corePackageName + DOT.getDelimiter() + SERVICE_SUFFIX.toLowerCase() + DOT.getDelimiter()  + entityName + "Service")
                                .build(),
                        ImportEntity.builder()
                                .value(corePackageName + DOT.getDelimiter() + DTO_SUFFIX.toLowerCase() + DOT.getDelimiter()  + entityName + "Dto")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.web.bind.annotation.*")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.http.ResponseEntity")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.beans.factory.annotation.Autowired")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.data.domain.Page")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.data.domain.Pageable")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.data.domain.Sort")
                                .build(),
                        ImportEntity.builder()
                                .value("org.springframework.data.web.PageableDefault")
                                .build(),
                        ImportEntity.builder()
                                .value("java.util.UUID")
                                .build()
                )
        );
    }

    public IndentList<MethodEntity> generateControllerMethods(Source source, ClassEntity mainEntity) {
        List<MethodEntity> methods = new ArrayList<>();
        for (Map.Entry<EntryPointType, String> entryPointPair : source.getEntryPoints().entrySet()) {
            methods.add(generateMethodFromEntryType(entryPointPair, mainEntity));
        }

        return new IndentList<>(DelimiterType.INDENT, false, methods);
    }

    private MethodEntity generateMethodFromEntryType(Map.Entry<EntryPointType, String> entryPointPair, ClassEntity mainEntity) {
        switch(entryPointPair.getKey()) {
            case CREATE_ENTRY_POINT: return new CreateMethodEntity(mainEntity.getName());
            default: return null;
        }
    }

    public IndentList<FieldEntity> getEntityControllerField(ClassEntity mainEntity) {
        return getEntityField(CONTROLLER_SUFFIX, mainEntity);
    }

    public IndentList<FieldEntity> getEntityServiceField(ClassEntity mainEntity) {
        return getEntityField(SERVICE_SUFFIX, mainEntity);
    }

    public IndentList<FieldEntity> getEntityRepositoryField(ClassEntity mainEntity) {
        return getEntityField(REPO_SUFFIX, mainEntity);
    }

    public IndentList<FieldEntity> getEntityField(ClassEntity mainEntity) {
        return getEntityField(EMPTY_STRING, mainEntity);
    }

    public IndentList<FieldEntity> getEntityDTOField(ClassEntity mainEntity) {
        return getEntityField(DTO_SUFFIX, mainEntity);
    }

    private IndentList<FieldEntity> getEntityField(String fieldSpecific, ClassEntity entity) {
        return new IndentList<>(DelimiterType.SEMICOLON, true,
                Arrays.asList(
                        FieldEntity.builder()
                                .modifiers(
                                        new EnumerationList<>(DelimiterType.NONE, true,
                                                Arrays.asList(PRIVATE_ACCESS_MODIFIER, FINAL_WORD))
                                )
                                .type(entity.getName() + fieldSpecific)
                                .name(fieldSpecific.toLowerCase())
                                .build()
                )
        );
    }
}
