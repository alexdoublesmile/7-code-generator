package com.plohoy.generator.util.codegenhelper;

import com.plohoy.generator.model.codeentity.*;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.IndentList;

import java.util.Arrays;

public class CodeTemplate {
    public static final String EMPTY_STRING = "";
    public static final String NULL_STRING = "null";
    public static final String SPACE_SYMBOL = " ";
    public static final String ENUMERATION_DELIMITER = ",";
    public static final String CODE_DELIMITER = ";";
    public static final String OPEN_BODY_BRACKET = "{";
    public static final String CLOSE_BODY_BRACKET = "}";
    public static final String OPEN_PARAM_BRACKET = "(";
    public static final String CLOSE_PARAM_BRACKET = ")";
    public static final String QUOTE = "\"";
    public static final String EQUALS = " = ";
    public static final String INDENT = "\n";
    public static final String TAB = "\t";
    public static final String PUBLIC_ACCESS_MODIFIER = "public";
    public static final String PRIVATE_ACCESS_MODIFIER = "private";
    public static final String DEFAULT_RETURN_TYPE = "void";
    public static final String IMPORT_WORD = "import";
    public static final String EXTEND_WORD = "extends";
    public static final String IMPLEMENT_WORD = "implements";
    public static final String THROW_WORD = "throws";
    public static final String STATIC_WORD = "static";
    public static final String PACKAGE_WORD = "package";
    public static final String ANNOTATION_MARK = "@";

    public static final String MAIN_METHOD_NAME = "main";
    public static final String MAIN_METHOD_ARGS_TYPE = "String[]";
    public static final String MAIN_METHOD_ARGS_VALUE = "args";

    public IndentList<MethodEntity> generateMainMethod(String bodyCode) {
        return new IndentList<>(DelimiterType.INDENT, false,
                Arrays.asList(MethodEntity.builder()
                                .modifiers(new EnumerationList<>(DelimiterType.NONE, true,
                                        Arrays.asList(PUBLIC_ACCESS_MODIFIER, STATIC_WORD)
                                ))
                                .returnType(DEFAULT_RETURN_TYPE)
                                .name(MAIN_METHOD_NAME)
                                .args(new EnumerationList<>(DelimiterType.COMMA, false,
                                        Arrays.asList(FieldEntity.builder()
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
                                            .value(PropertyValueEntity.builder()
                                                        .values(new IndentList<>(DelimiterType.COMMA, false,
                                                                Arrays.asList(
                                                                        "classpath:message.properties",
                                                                        "classpath:db.properties"
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

    public static String getIndent() {
        return getIndent(1);
    }

    public static String getTab() {
        return getTab(1);
    }

    public EnumerationList<String> getPublicModifier() {
        return new EnumerationList<>(DelimiterType.NONE, true,
                Arrays.asList(PUBLIC_ACCESS_MODIFIER));
    }

    public EnumerationList<String> getPrivateModifier() {
        return new EnumerationList<>(DelimiterType.NONE, true,
                Arrays.asList(PRIVATE_ACCESS_MODIFIER));
    }
}
