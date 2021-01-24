package com.plohoy.generator.util.codegenhelper.codetemplate;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.annotation.QuotedValueList;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ImportEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.method.*;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.plohoy.generator.model.EndPointType.MAIN_END_POINT;

public class CodeTemplate {
    public static final String INDENT = "\n";
    public static final String TAB = "\t";
    public static final String EMPTY = "";
    public static final String NULL = "null";
    public static final String SPACE = " ";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String QUOTE = "\"";
    public static final String DOT = ".";
    public static final String TRIPLE_DOT = "...";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String WILDCARD = "?";
    public static final String BLANK = "_";
    public static final String MINUS = "-";
    public static final String PLUS = "+";
    public static final String STAR = "*";
    public static final String AND = "&";
    public static final String DOUBLE_AND = "&&";
    public static final String OR = "|";
    public static final String DOUBLE_OR = "||";
    public static final String INVERSE = "!";
    public static final String PERCENT = "%";
    public static final String EQUAL = "=";
    public static final String OPEN_BODY_BRACKET = "{";
    public static final String CLOSE_BODY_BRACKET = "}";
    public static final String BODY_BRACKETS = "{}";
    public static final String OPEN_PARAM_BRACKET = "(";
    public static final String CLOSE_PARAM_BRACKET = ")";
    public static final String PARAM_BRACKETS = "()";
    public static final String OPEN_CORNER_BRACKET = "<";
    public static final String CLOSE_CORNER_BRACKET = ">";
    public static final String CORNER_BRACKETS = "<>";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSE_SQUARE_BRACKET = "]";
    public static final String ARRAY = "[]";

    public static final String JAVA = "java";
    public static final String PACKAGE = "package";
    public static final String IMPORT = "import";
    public static final String ANNOTATION_MARK = "@";
    public static final String PUBLIC_MOD = "public";
    public static final String PRIVATE_MOD = "private";
    public static final String PROTECTED_MOD = "protected";
    public static final String DEFAULT_MOD = "default";
    public static final String STATIC_MOD = "static";
    public static final String FINAL_MOD = "final";
    public static final String ABSTRACT_MOD = "abstract";
    public static final String SYNCHRONIZED_MOD = "synchronized";
    public static final String VOLATILE_MOD = "volatile";
    public static final String TRANSITION_MOD = "transition";
    public static final String CLASS = "class";
    public static final String INTERFACE = "interface";
    public static final String VOID = "void";
    public static final String EXTENDS = "extends";
    public static final String SUPER = "super";
    public static final String THIS = "this";
    public static final String RETURN = "return";
    public static final String IF = "if";
    public static final String ELSE = "else";
    public static final String TRY = "try";
    public static final String CATCH = "catch";
    public static final String FINALLY = "finally";
    public static final String FOR = "for";
    public static final String WHILE = "while";
    public static final String DO = "do";
    public static final String SWITCH = "switch";
    public static final String CASE = "case";
    public static final String INSTANCE_OF = "instanceOf";
    public static final String IMPLEMENTS = "implements";
    public static final String THROWS = "throws";
    public static final String THROW = "throw";
    public static final String EQUALS = "equals";
    public static final String HASHCODE = "hashcode";
    public static final String TO_STRING = "toString";

    public static final String BOOLEAN = "boolean";
    public static final String INT = "int";
    public static final String LONG = "long";
    public static final String SHORT = "short";
    public static final String FLOAT = "float";
    public static final String DOUBLE = "double";
    public static final String CHAR = "char";
    public static final String STRING = "String";
    public static final String OBJECT = "Object";

    public static final String LIST_TEMPLATE = "List<%s>";
    public static final String ARRAY_LIST_TEMPLATE = "ArrayList<%s>";
    public static final String LINKED_LIST_TEMPLATE = "LinkedList<%s>";
    public static final String MAP_TEMPLATE = "Map<%s, %s>";
    public static final String HASH_MAP_TEMPLATE = "HashMap<%s, %s>";
    public static final String SET_TEMPLATE = "Set<%s>";
    public static final String HASH_SET_TEMPLATE = "HashSet<%s>";
    public static final String OPTIONAL_TEMPLATE = "Optional<%s>";

    public static final String DELETED = "deleted";
    public static final String ID = "id";
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
    public static final String ENTITY_SUFFIX = "Entity";
    public static final String EXCEPTION_SUFFIX = "Exception";
    public static final String UTIL_SUFFIX = "Util";
    public static final String HELPER_SUFFIX = "Helper";
    public static final String BUILDER_SUFFIX = "Builder";
    public static final String TEMPLATE_SUFFIX = "Template";
    public static final String REQUEST_SUFFIX = "Request";
    public static final String RESPONSE_SUFFIX = "Response";

    public static final String APPLICATION_PROPERTIES_NAME = "application.properties";
    public static final String APPLICATION_DEV_PROPERTIES_NAME = "application-dev.properties";
    public static final String APPLICATION_TEST_PROPERTIES_NAME = "application-test.properties";
    public static final String MESSAGE_PROPERTIES_NAME = "message.properties";
    public static final String DB_PROPERTIES_NAME = "db.properties";
    public static final String URL_PROPERTIES_NAME = "url.properties";

    public static final String CLASSPATH = "classpath:";
    public static final String JPA_REPO_TEMPLATE = "JpaRepository<%s, %s>";
    public static final String SPRING_BOOT_LAUNCHER_TEMPLATE = "SpringApplication.run(%s.class, args);";

    public static final String SPRING_BOOT_LAUNCHER_ANNOTATION = "SpringBootApplication";
    public static final String SPRING_PROPERTY_ANNOTATION = "PropertySource";
    public static final String SPRING_REST_ANNOTATION = "RestController";
    public static final String SPRING_REQUEST_MAPPING_ANNOTATION = "RequestMapping";
    public static final String LOMBOK_REQ_CONSTRUCTOR_ANNOTATION = "RequiredArgsConstructor";
    public static final String LOMBOK_ALL_CONSTRUCTOR_ANNOTATION = "AllArgsConstructor";
    public static final String LOMBOK_NO_CONSTRUCTOR_ANNOTATION = "NoArgsConstructor";

    public static final String FIND_BY_ID_AND_DELETED_METHOD = "findByIdAndDeleted";
    public static final String FIND_BY_DELETED_METHOD = "findByDeleted";

    public static final String DEFAULT_VERSION = "0.0.1";
    public static final String JAVA_UTIL_PACKAGE = "java.util";
    public static final String SPRING_MAIN_PACKAGE = "org.springframework";
    public static final String SPRING_BOOT_MAIN_PACKAGE = "org.springframework.boot";
    public static final String MAPSTRUCT_MAIN_PACKAGE = "org.mapstruct";
    public static final String LOMBOK_MAIN_PACKAGE = "lombok";




    public String getPackageString(String packageName) {
        return PACKAGE + SPACE + packageName + SEMICOLON + getIndent(2);
    }

    public FieldEntity getEntityControllerField(String entityName) {
        return getEntityField(CONTROLLER_SUFFIX, entityName);
    }

    public FieldEntity getEntityServiceField(String entityName) {
        return getEntityField(SERVICE_SUFFIX, entityName);
    }

    public FieldEntity getEntityRepositoryField(String entityName) {
        return getEntityField(REPO_SUFFIX, entityName);
    }

    public FieldEntity getEntityMapperField(String entityName) {
        return getEntityField(MAPPER_SUFFIX, entityName);
    }

    public FieldEntity getEntityField(String entityName) {
        return getEntityField(EMPTY, entityName);
    }

    public FieldEntity getEntityDTOField(String entityName) {
        return getEntityField(DTO_SUFFIX, entityName);
    }

    private FieldEntity getEntityField(String fieldSpecific, String entityName) {
        return FieldEntity.builder()
                .modifiers(new EnumerationList<>(PRIVATE_MOD, FINAL_MOD))
                .type(entityName + fieldSpecific)
                .name(fieldSpecific.toLowerCase())
                .build();
    }

    public static String getIndent(int indentNumber) {
        String result = EMPTY;
        for (int i = 0; i < indentNumber; i++) {
            result += INDENT;
        }
        return result;
    }

    public static String getTab(int tabNumber) {
        String result = EMPTY;
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

    public static EnumerationList<String> getPublicMod() {
        return new EnumerationList<>(PUBLIC_MOD);
    }

    public EnumerationList<String> getPrivateMod() {
        return new EnumerationList<>(PRIVATE_MOD);
    }

    private MethodEntity getMethodByEndPointType(Map.Entry<EndPointType, EndPoint> endPointPair, ClassEntity dtoEntity) {
        switch(endPointPair.getKey()) {
            case CREATE_END_POINT:
                return new SaveMethodEntity(dtoEntity, endPointPair.getValue());
            case FIND_ALL_END_POINT:
                return new FindAllMethodEntity(dtoEntity, endPointPair.getValue());
            case FIND_END_POINT:
                return new FindMethodEntity(dtoEntity, endPointPair.getValue());
            case UPDATE_END_POINT:
                return new UpdateMethodEntity(dtoEntity, endPointPair.getValue());
            case DELETE_HARD_END_POINT:
                return new DeleteHardMethodEntity(dtoEntity, endPointPair.getValue());
            case DELETE_SOFT_END_POINT:
                return new DeleteSoftMethodEntity(dtoEntity, endPointPair.getValue());
            default:
                return null;
        }
    }

    private MethodEntity getMethodByEndPointType(Map.Entry<EndPointType, EndPoint> endPointPair, ClassEntity entity, ClassEntity dtoEntity) {
        switch(endPointPair.getKey()) {
            case CREATE_END_POINT:
                return new SaveMethodEntity(entity, dtoEntity, endPointPair.getValue());
            case FIND_ALL_END_POINT:
                return new FindAllMethodEntity(entity, dtoEntity, endPointPair.getValue());
            case FIND_END_POINT:
                return new FindMethodEntity(entity, dtoEntity, endPointPair.getValue());
            case UPDATE_END_POINT:
                return new UpdateMethodEntity(entity, dtoEntity, endPointPair.getValue());
            case DELETE_HARD_END_POINT:
                return new DeleteHardMethodEntity(entity, dtoEntity, endPointPair.getValue());
            case DELETE_SOFT_END_POINT:
                return new DeleteSoftMethodEntity(entity, dtoEntity, endPointPair.getValue());
            default:
                return null;
        }
    }

    public IndentList<MethodEntity> getMainMethod(String bodyCode) {
        return new IndentList<MethodEntity>(DelimiterType.INDENT, true,
                MethodEntity.builder()
                        .modifiers(new EnumerationList<>(PUBLIC_MOD, STATIC_MOD))
                        .returnType(VOID)
                        .name(MAIN_METHOD_NAME)
                        .args(new EnumerationList<ArgumentEntity>(false,
                                ArgumentEntity.builder()
                                    .type(MAIN_METHOD_ARGS_TYPE)
                                    .name(MAIN_METHOD_ARGS_VALUE)
                                    .build()))
                        .body(bodyCode)
                        .build()
        );
    }

    public IndentList<ImportEntity> getSpringBootLaunchImports() {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".boot.SpringApplication")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".boot.autoconfigure.SpringBootApplication")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".context.annotation.PropertySource")
                        .build()
        );
    }

    public IndentList<AnnotationEntity> getSpringBootLaunchAnnotations() {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name(SPRING_PROPERTY_ANNOTATION)
                        .properties(new EnumerationList<PropertyEntity>(false,
                                PropertyEntity.builder()
                                    .quotedValueList(QuotedValueList.builder()
                                                .values(new IndentList<String>(DelimiterType.COMMA, false,
                                                        CLASSPATH + MESSAGE_PROPERTIES_NAME,
                                                        CLASSPATH + DB_PROPERTIES_NAME
                                                ))
                                                .build())
                                    .build()))
                        .build(),
                AnnotationEntity.builder()
                        .name(SPRING_BOOT_LAUNCHER_ANNOTATION)
                        .build());
    }

    public String getSpringLaunchBody(String className) {
        return String.format(
                SPRING_BOOT_LAUNCHER_TEMPLATE,
                className
        );
    }

    public IndentList<ImportEntity> getSpringRestImports(String corePackageName, String entityName) {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(corePackageName + DOT + SERVICE_SUFFIX.toLowerCase() + DOT  + entityName + SERVICE_SUFFIX)
                        .build(),
                ImportEntity.builder()
                        .value(corePackageName + DOT + DTO_SUFFIX.toLowerCase() + DOT  + entityName + DTO_SUFFIX)
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".web.bind.annotation.*")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".http.ResponseEntity")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.domain.Page")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.domain.Pageable")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.domain.Sort")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.web.PageableDefault")
                        .build(),
                ImportEntity.builder()
                        .value(LOMBOK_MAIN_PACKAGE + ".RequiredArgsConstructor")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".UUID")
                        .build());
    }

    public IndentList<AnnotationEntity> getSpringRestAnnotations(String controllerPath) {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name(SPRING_REST_ANNOTATION)
                        .build(),
                AnnotationEntity.builder()
                        .name(SPRING_REQUEST_MAPPING_ANNOTATION)
                        .value(controllerPath)
                        .build(),
                AnnotationEntity.builder()
                        .name(LOMBOK_REQ_CONSTRUCTOR_ANNOTATION)
                        .build());
    }

    public IndentList<FieldEntity> getSpringRestFields(ClassEntity entityName) {
        return new IndentList<FieldEntity>(DelimiterType.SEMICOLON, true, true,
                getEntityServiceField(entityName.getName())
        );
    }

    public IndentList<MethodEntity> getSpringRestMethods(HashMap<EndPointType, EndPoint> endPoints, ClassEntity mainDtoEntity) {
        List<MethodEntity> methods = new ArrayList<>();
        for (Map.Entry<EndPointType, EndPoint> endPointPair : endPoints.entrySet()) {

            if (!MAIN_END_POINT.equals(endPointPair.getKey())) {
                methods.add(getMethodByEndPointType(endPointPair, mainDtoEntity));
            }
        }

        return new IndentList<>(DelimiterType.INDENT, true, methods);
    }

    public IndentList<ImportEntity> getSpringServiceImports(String corePackageName, String entityName) {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(corePackageName + DOT + DTO_SUFFIX.toLowerCase() + DOT  + entityName + DTO_SUFFIX)
                        .build(),
                ImportEntity.builder()
                        .value(corePackageName + DOT + ENTITY_SUFFIX.toLowerCase() + DOT + entityName)
                        .build(),
                ImportEntity.builder()
                        .value(corePackageName + DOT + MAPPER_SUFFIX.toLowerCase() + DOT  + entityName + MAPPER_SUFFIX)
                        .build(),
                ImportEntity.builder()
                        .value(corePackageName + DOT + REPO_SUFFIX.toLowerCase() + DOT  + entityName + REPO_SUFFIX)
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".http.HttpStatus")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".http.ResponseEntity")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".beans.factory.annotation.Value")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".dao.EmptyResultDataAccessException")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".stereotype.Service")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".transaction.annotation.Transactional")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".web.server.ResponseStatusException")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.domain.Page")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.domain.Pageable")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.domain.PageImpl")
                        .build(),
                ImportEntity.builder()
                        .value(LOMBOK_MAIN_PACKAGE + ".RequiredArgsConstructor")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".List")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".Objects")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".UUID")
                        .build());
    }

    public IndentList<AnnotationEntity> getSpringServiceAnnotations() {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name(SERVICE_SUFFIX)
                        .build(),
                AnnotationEntity.builder()
                        .name(LOMBOK_REQ_CONSTRUCTOR_ANNOTATION)
                        .build());
    }

    public IndentList<FieldEntity> getSpringServiceFields(ClassEntity mainEntity) {
        return new IndentList<FieldEntity>(DelimiterType.SEMICOLON, true, true,
                getEntityRepositoryField(mainEntity.getName()),
                getEntityMapperField(mainEntity.getName()),
                FieldEntity.builder()
                        .annotations(new IndentList<AnnotationEntity>(
                                AnnotationEntity.builder()
                                        .name("Value")
                                        .value("${ids.do.not.match.message}")
                                        .build()
                        ))
                        .modifiers(getPrivateMod())
                        .type(STRING)
                        .name("idsDoNotMatchMessage")
                        .build()
        );
    }

    public IndentList<MethodEntity> getSpringServiceMethods(HashMap<EndPointType, EndPoint> endPoints, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        List<MethodEntity> methods = new ArrayList<>();
        for (Map.Entry<EndPointType, EndPoint> endPointPair : endPoints.entrySet()) {

            if (!MAIN_END_POINT.equals(endPointPair.getKey())) {
                methods.add(getMethodByEndPointType(endPointPair, mainEntity, mainDtoEntity));
            }
        }

        return new IndentList<>(DelimiterType.INDENT, true, methods);
    }

    public IndentList<ImportEntity> getSpringDataJpaImports(String corePackageName, String entityName) {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".data.jpa.repository.JpaRepository")
                        .build(),
                ImportEntity.builder()
                        .value(corePackageName + DOT + ENTITY_SUFFIX.toLowerCase() + DOT  + entityName)
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".List")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".Optional")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".UUID")
                        .build()
        );
    }

    public String getSpringDataJpaRepoClassName(ClassEntity mainEntity) {
        return String.format(
                JPA_REPO_TEMPLATE,
                mainEntity.getName(),
                mainEntity.getIdType());
    }

    public IndentList<MethodEntity> getSpringDataJpaDeleteMethods(ClassEntity mainEntity) {
        return new IndentList<MethodEntity>(DelimiterType.SEMICOLON, true, true,
                MethodEntity.builder()
                        .returnType(String.format(LIST_TEMPLATE, mainEntity.getName()))
                        .name(FIND_BY_DELETED_METHOD)
                        .args(new EnumerationList<ArgumentEntity>(false,
                                ArgumentEntity.builder()
                                        .type(BOOLEAN)
                                        .name(DELETED)
                                        .build()))
                        .build(),
                MethodEntity.builder()
                        .returnType(String.format(OPTIONAL_TEMPLATE, mainEntity.getName()))
                        .name(FIND_BY_ID_AND_DELETED_METHOD)
                        .args(new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                                ArgumentEntity.builder()
                                        .type(mainEntity.getIdType())
                                        .name(ID)
                                        .build(),
                                ArgumentEntity.builder()
                                        .type(BOOLEAN)
                                        .name(DELETED)
                                        .build()))
                        .build()
        );
    }

    public IndentList<ImportEntity> getMapstructImports(String corePackageName, String entityName) {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(MAPSTRUCT_MAIN_PACKAGE + ".Mapper")
                        .build(),
                ImportEntity.builder()
                        .value(corePackageName + DOT + ENTITY_SUFFIX.toLowerCase() + DOT  + entityName)
                        .build(),
                ImportEntity.builder()
                        .value(corePackageName + DOT + DTO_SUFFIX.toLowerCase() + DOT  + entityName + DTO_SUFFIX)
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".List")
                        .build()
        );
    }

    public IndentList<AnnotationEntity> getMapstructAnnotations() {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name(MAPPER_SUFFIX)
                        .property(PropertyEntity.builder()
                                .name("componentModel")
                                .quotedValue("spring")
                                .build())
                        .build());
    }

    public IndentList<MethodEntity> getMapstructMethods(ClassEntity mainEntity) {
        return new IndentList<MethodEntity>(DelimiterType.SEMICOLON, true, true,
                MethodEntity.builder()
                        .returnType(mainEntity.getName() + DTO_SUFFIX)
                        .name("toDto")
                        .args(new EnumerationList<ArgumentEntity>(false,
                                ArgumentEntity.builder()
                                        .type(mainEntity.getName())
                                        .name(ENTITY_SUFFIX.toLowerCase())
                                        .build()))
                        .build(),
                MethodEntity.builder()
                        .returnType(mainEntity.getName())
                        .name("toEntity")
                        .args(new EnumerationList<ArgumentEntity>(false,
                                ArgumentEntity.builder()
                                        .type(mainEntity.getName() + DTO_SUFFIX)
                                        .name(DTO_SUFFIX.toLowerCase())
                                        .build()))
                        .build(),
                MethodEntity.builder()
                        .returnType(String.format(LIST_TEMPLATE, mainEntity.getName() + DTO_SUFFIX))
                        .name("toDtoList")
                        .args(new EnumerationList<ArgumentEntity>(false,
                                ArgumentEntity.builder()
                                        .type(String.format(LIST_TEMPLATE, mainEntity.getName()))
                                        .name(ENTITY_SUFFIX.toLowerCase() + "List")
                                        .build()))
                        .build()
        );
    }

    public IndentList<ImportEntity> getEntityImports(String corePackageName, String entityName) {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(LOMBOK_MAIN_PACKAGE + ".Data")
                        .build(),
                ImportEntity.builder()
                        .value("javax.persistence.*")
                        .build(),
                ImportEntity.builder()
                        .value("java.time.LocalDate")
                        .build(),
                ImportEntity.builder()
                        .value("java.time.LocalDateTime")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".List")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".ArrayList")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".Objects")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".UUID")
                        .build()
        );
    }

    public IndentList<ImportEntity> getDTOImports(String corePackageName, String name) {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(LOMBOK_MAIN_PACKAGE + ".Data")
                        .build(),
                ImportEntity.builder()
                        .value("java.time.LocalDate")
                        .build(),
                ImportEntity.builder()
                        .value("java.time.LocalDateTime")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".List")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".ArrayList")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".Objects")
                        .build(),
                ImportEntity.builder()
                        .value(JAVA_UTIL_PACKAGE + ".UUID")
                        .build()
        );
    }

    public IndentList<AnnotationEntity> getPersistenceAnnotations() {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name("Data")
                        .build());
    }

    public IndentList<AnnotationEntity> getDTOAnnotations() {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name("Data")
                        .build());
    }

    public IndentList<FieldEntity> getEntityFields(ClassEntity entity) {
        List<FieldEntity> fields = new ArrayList<>();
        for (FieldEntity requestField : entity.getFields()) {
            fields.add(FieldEntity.builder()
                    .modifiers(getPrivateMod())
                    .type(requestField.getType())
                    .name(requestField.getName())
                    .build());
        }
        fields.add(FieldEntity.builder()
                .modifiers(getPrivateMod())
                .type(StringUtils.capitalize(BOOLEAN))
                .name("deleted")
                .build());

        return new IndentList<FieldEntity>(DelimiterType.SEMICOLON, true, true,
                fields
        );
    }

    public IndentList<FieldEntity> getDTOFields(ClassEntity dtoEntity) {
        List<FieldEntity> dtoFields = new ArrayList<>();
        for (FieldEntity requestField : dtoEntity.getFields()) {
            dtoFields.add(FieldEntity.builder()
                    .modifiers(getPrivateMod())
                    .type(requestField.getType())
                    .name(requestField.getName())
                    .schemaDescription(requestField.getSchemaDescription())
                    .build());
        }
        dtoFields.add(FieldEntity.builder()
                .modifiers(getPrivateMod())
                .type(StringUtils.capitalize(BOOLEAN))
                .name("deleted")
                .value("Boolean.FALSE")
                .build());

        return new IndentList<FieldEntity>(DelimiterType.SEMICOLON, true, true,
                dtoFields
        );
    }

    public IndentList<ImportEntity> getExceptionHandlerImports() {
        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true,
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".http.HttpHeaders")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".http.HttpStatus")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".http.ResponseEntity")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".web.bind.annotation.ControllerAdvice")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".web.bind.annotation.ExceptionHandler")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".web.context.request.WebRequest")
                        .build(),
                ImportEntity.builder()
                        .value(SPRING_MAIN_PACKAGE + ".web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler")
                        .build(),
                ImportEntity.builder()
                        .value("javax.persistence.EntityNotFoundException")
                        .build()
        );
    }

    public IndentList<AnnotationEntity> getExceptionHandlerAnnotations() {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name("ControllerAdvice")
                        .build());
    }

    public IndentList<MethodEntity> getExceptionMethods() {
        return new IndentList<MethodEntity>(DelimiterType.INDENT, true,
                MethodEntity.builder()
                        .annotations(new IndentList<AnnotationEntity>(
                                AnnotationEntity.builder()
                                        .name("ExceptionHandler")
                                        .property(PropertyEntity.builder().simpleValue("EntityNotFoundException.class").build())
                                        .build()))
                        .modifiers(new EnumerationList<String>(PROTECTED_MOD))
                        .returnType("ResponseEntity<Object>")
                        .name("handleEntityNotFoundException")
                        .args(new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                                ArgumentEntity.builder()
                                        .type("EntityNotFoundException")
                                        .name("ex")
                                        .build(),
                                ArgumentEntity.builder()
                                        .type("WebRequest")
                                        .name("request")
                                        .build()))
                        .body("return handleExceptionInternal(\n" +
                                "                ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);")
                        .build());
    }

    public HashMap<String, String> getAppProperties() {
        HashMap<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("server.port", "8080");
        propertiesMap.put("spring.jpa.generate-ddl", "false");
        propertiesMap.put("spring.jpa.hibernate.ddl-auto", "none");
        propertiesMap.put("spring.liquibase.change-log", "classpath:db/changelog/db.changelog-master.yml");
        return propertiesMap;
    }

    public HashMap<String, String> getAppDevProperties() {
        HashMap<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("server.port", "8088");
        propertiesMap.put("spring.main.banner-mode", "off");
        propertiesMap.put("spring.jpa.show-sql", "true");
        propertiesMap.put("spring.jpa.generate-ddl", "true");
        propertiesMap.put("spring.jpa.hibernate.ddl-auto", "create-drop");
        propertiesMap.put("spring.liquibase.enabled", "false");
        return propertiesMap;
    }

    public HashMap<String, String> getMessageProperties() {
        HashMap<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("ids.do.not.match.message", "ID from Entity & ID from path don't match");
        propertiesMap.put("entity.not.found.message", "Entity with this ID was not found");
        return propertiesMap;
    }

    public HashMap<String, String> getDBProperties() {
        HashMap<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("spring.datasource.url", "jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:postgres}?sslmode=${DB_SSL_MODE:disable}&prepareThreshold=${DB_PREPARE_THRESHOLD:5}");
        propertiesMap.put("spring.datasource.username", "${DB_USERNAME:postgres}");
        propertiesMap.put("spring.datasource.password", "${DB_PASSWORD:mysecretpassword}");
        propertiesMap.put("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQL9Dialect");
        return propertiesMap;
    }
}
