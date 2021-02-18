package com.plohoy.generator.util.codegenhelper.codetemplate;

import com.plohoy.generator.model.file.AbstractSourceFile;
import com.plohoy.generator.util.domainhelper.DomainHelper;
import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.annotation.QuotedValueList;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.clazz.ImportEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.method.*;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static com.plohoy.generator.model.EndPointType.CONTROLLER_END_POINT;

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
    public static final String HASHCODE = "hashCode";
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

    private MethodEntity getMethodByEndPointType(EndPoint endPoint, ClassEntity dtoEntity) {
        switch(endPoint.getType()) {
            case CREATE_END_POINT:
                return new SaveMethodEntity(dtoEntity, endPoint);
            case FIND_ALL_END_POINT:
                return new FindAllMethodEntity(dtoEntity, endPoint);
            case FIND_END_POINT:
                return new FindMethodEntity(dtoEntity, endPoint);
            case UPDATE_END_POINT:
                return new UpdateMethodEntity(dtoEntity, endPoint);
            case DELETE_HARDLY_END_POINT:
                return new DeleteHardlyMethodEntity(dtoEntity, endPoint);
            case DELETE_SOFTLY_END_POINT:
                return new DeleteSoftlyMethodEntity(dtoEntity, endPoint);
            case RESTORE_END_POINT:
                return new RestoreMethodEntity(dtoEntity, endPoint);
            default:
                return null;
        }
    }

    private MethodEntity getMethodByEndPointType(EndPoint endPoint, ClassEntity entity, ClassEntity dtoEntity) {
        switch(endPoint.getType()) {
            case CREATE_END_POINT:
                return new SaveMethodEntity(entity, dtoEntity, endPoint);
            case FIND_ALL_END_POINT:
                return new FindAllMethodEntity(entity, dtoEntity, endPoint);
            case FIND_END_POINT:
                return new FindMethodEntity(entity, dtoEntity, endPoint);
            case UPDATE_END_POINT:
                return new UpdateMethodEntity(entity, dtoEntity, endPoint);
            case DELETE_HARDLY_END_POINT:
                return new DeleteHardlyMethodEntity(entity, dtoEntity, endPoint);
            case DELETE_SOFTLY_END_POINT:
                return new DeleteSoftlyMethodEntity(entity, dtoEntity, endPoint);
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
                                                        CLASSPATH + DB_PROPERTIES_NAME))
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

    public IndentList<MethodEntity> getSpringRestMethods(List<EndPoint> endPoints, ClassEntity mainDtoEntity) {
        List<MethodEntity> methods = new ArrayList<>();
        for (EndPoint endPoint : endPoints) {

            if (!CONTROLLER_END_POINT.equals(endPoint.getType())) {
                methods.add(getMethodByEndPointType(endPoint, mainDtoEntity));
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

    public IndentList<MethodEntity> getSpringServiceMethods(List<EndPoint> endPoints, ClassEntity mainEntity, ClassEntity mainDtoEntity) {
        List<MethodEntity> methods = new ArrayList<>();
        for (EndPoint endPoint : endPoints) {

            if (!CONTROLLER_END_POINT.equals(endPoint.getType())) {
                methods.add(getMethodByEndPointType(endPoint, mainEntity, mainDtoEntity));
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

    public IndentList<ImportEntity> getMapStructImports(
            String corePackageName,
            String entityName,
            HashMap<FieldEntity, FieldEntity> loopPossibleWithMappedFields) {
        List<ImportEntity> imports = new ArrayList<>();

        imports.add(ImportEntity.builder()
                .value(MAPSTRUCT_MAIN_PACKAGE + ".Mapper")
                .build());
        imports.add(ImportEntity.builder()
                .value(MAPSTRUCT_MAIN_PACKAGE + ".Mapping")
                .build());
        imports.add(ImportEntity.builder()
                .value(corePackageName + DOT + ENTITY_SUFFIX.toLowerCase() + DOT  + entityName)
                .build());
        imports.add(ImportEntity.builder()
                .value(corePackageName + DOT + DTO_SUFFIX.toLowerCase() + DOT  + entityName + DTO_SUFFIX)
                .build());
        imports.add(ImportEntity.builder()
                .value(corePackageName + DOT + ENTITY_SUFFIX.toLowerCase() + DOT  + "*")
                .build());
        imports.add(ImportEntity.builder()
                .value(corePackageName + DOT + DTO_SUFFIX.toLowerCase() + DOT  + "*")
                .build());
        imports.add(ImportEntity.builder()
                .value(JAVA_UTIL_PACKAGE + ".List")
                .build());

        for (FieldEntity loopPossibleFields : loopPossibleWithMappedFields.keySet()) {
            imports.add(ImportEntity.builder()
                    .value(corePackageName + DOT + ENTITY_SUFFIX.toLowerCase() + DOT  + StringUtils.capitalize(loopPossibleFields.getType()))
                    .build());
            imports.add(ImportEntity.builder()
                    .value(corePackageName + DOT + DTO_SUFFIX.toLowerCase() + DOT  + StringUtils.capitalize(loopPossibleFields.getType()) + DTO_SUFFIX)
                    .build());
        }

        return new IndentList<ImportEntity>(DelimiterType.SEMICOLON, true, true, imports);
    }

    public IndentList<AnnotationEntity> getMapStructAnnotations() {
        return new IndentList<>(
                AnnotationEntity.builder()
                        .name(MAPPER_SUFFIX)
                        .property(PropertyEntity.builder()
                                .name("componentModel")
                                .quotedValue("spring")
                                .build())
                        .build());
    }

    public IndentList<MethodEntity> getMapStructMethods(ClassEntity mainEntity, List<AbstractSourceFile> entityFiles) {
        List<MethodEntity> mapStructMethods = new ArrayList<>();

        HashMap<FieldEntity, FieldEntity> loopPossibleWithMappedFields = getLoopPossibleFields(mainEntity, entityFiles);

        for (Map.Entry<FieldEntity, FieldEntity> loopPossibleFieldPair : loopPossibleWithMappedFields.entrySet()) {
            FieldEntity loopPossibleField = loopPossibleFieldPair.getKey();
            FieldEntity mappedField = loopPossibleFieldPair.getValue();

            mapStructMethods.add(
                    getToDtoLoopPreventMethod(loopPossibleField.getType(), mappedField.getName()));
            mapStructMethods.add(
                    getToEntityLoopPreventMethod(loopPossibleField.getType(), mappedField.getName()));
        }

        List<MethodEntity> differentMapStructMethods = mapStructMethods.stream()
                .distinct()
                .collect(Collectors.toList());

        differentMapStructMethods.addAll(getBasicMapStructMethods(mainEntity));

        differentMapStructMethods.forEach(method -> {
            if (hasDuplicateTypes(differentMapStructMethods, method.getReturnType())) {
                MethodEntity methodByType = getMethodByTypeForRemove(differentMapStructMethods, method.getReturnType(), method);

                MethodEntity mergeMethod, removeMethod;
                if (isRootMethod(method)) {
                    mergeMethod = method;
                    removeMethod = methodByType;
                } else {
                    mergeMethod = methodByType;
                    removeMethod = method;
                }

                mergeMethodsByAnnotations(mergeMethod, removeMethod);
            }
        });

        Iterator<MethodEntity> iterator = differentMapStructMethods.iterator();
        while (iterator.hasNext()) {
            if ("SHOULD_REMOVE".equals(iterator.next().getReturnType())) {
                iterator.remove();
            }
        }


        return new IndentList<MethodEntity>(DelimiterType.SEMICOLON, true, true, differentMapStructMethods);
    }

    private boolean isRootMethod(MethodEntity method) {
        return "toDto".equals(method.getName()) || "toEntity".equals(method.getName());
    }

    private MethodEntity getMethodByTypeForRemove(List<MethodEntity> mapStructMethods, String returnType, MethodEntity methodForMerge) {
        List<MethodEntity> methodsByType = mapStructMethods.stream()
                .filter(method -> returnType.equals(method.getReturnType())
                        && !methodForMerge.equals(method))
                .collect(Collectors.toList());

        return methodsByType.get(0);
    }

    public boolean hasDuplicateTypes(List<MethodEntity> methods, String returnType) {
        return methods.stream()
                .filter(method -> returnType.equals(method.getReturnType()))
                .count() > 1;
    }

    private MethodEntity mergeMethodsByAnnotations(MethodEntity methodForMerge, MethodEntity methodForRemove) {
        methodForRemove.getAnnotations()
                .stream()
                .forEach(annotationFromRemovedMethod -> {
                    if (Objects.isNull(methodForMerge.getAnnotations())) {
                        methodForMerge.setAnnotations(new IndentList<AnnotationEntity>());
                    }

                    methodForMerge.getAnnotations()
                            .add(AnnotationEntity.builder()
                                    .name(annotationFromRemovedMethod.getName())
                                    .value(annotationFromRemovedMethod.getValue())
                                    .property(annotationFromRemovedMethod.getProperty())
                                    .properties(annotationFromRemovedMethod.getProperties())
                                    .build().setParentEntity(methodForMerge));

                });

        methodForRemove.setReturnType("SHOULD_REMOVE");

        return methodForMerge;
    }

    private HashMap<FieldEntity, FieldEntity> getLoopPossibleFields(ClassEntity mainEntity, List<AbstractSourceFile> entityFiles) {
        HashMap<FieldEntity, FieldEntity> loopFieldsMap = new HashMap<>();

        List<ClassEntity> loopPossibleEntities = new ArrayList<>();

        mainEntity.getFields()
                .stream()
                .filter(DomainHelper::hasAnyRelations)
                .filter(field -> DomainHelper.isExternalTypeField(field, mainEntity.getName())
                                || DomainHelper.isSingleClassLoopPossible(field, mainEntity.getName()))
                .forEach(field -> {
                    loopFieldsMap.put(
                            field,
                            DomainHelper.getMappedFieldFromFiles(field, mainEntity.getName(), entityFiles));

                    if (DomainHelper.isExternalTypeField(field, mainEntity.getName())) {
                        loopPossibleEntities.add(DomainHelper.getEntityByType(field.getType(), entityFiles));
                    }
                });

        for (ClassEntity loopPossibleEntity : loopPossibleEntities) {
            loopPossibleEntity.getFields().stream()
                    .filter(DomainHelper::hasAnyRelations)
                    .filter(field -> DomainHelper.isExternalTypeField(field, mainEntity.getName()))
                    .filter(field -> DomainHelper.isExternalTypeField(field, loopPossibleEntity.getName())
                            || DomainHelper.isSingleClassLoopPossible(field, loopPossibleEntity.getName()))
                    .forEach(field -> loopFieldsMap.put(
                            field,
                            DomainHelper.getMappedFieldFromFiles(field, loopPossibleEntity.getName(), entityFiles)));
        }

        return loopFieldsMap;
    }

    private Collection<? extends MethodEntity> getBasicMapStructMethods(ClassEntity mainEntity) {
        List<MethodEntity> basicMapStructMethods = new ArrayList<>();

        MethodEntity toDtoMethod = MethodEntity.builder()
                .returnType(mainEntity.getName() + DTO_SUFFIX)
                .name("toDto")
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(mainEntity.getName())
                                .name(ENTITY_SUFFIX.toLowerCase())
                                .build()))
                .build();

        MethodEntity toEntityMethod = MethodEntity.builder()
                .returnType(mainEntity.getName())
                .name("toEntity")
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(mainEntity.getName() + DTO_SUFFIX)
                                .name(DTO_SUFFIX.toLowerCase())
                                .build()))
                .build();

        MethodEntity toDtoListMethod = MethodEntity.builder()
                .returnType(String.format(LIST_TEMPLATE, mainEntity.getName() + DTO_SUFFIX))
                .name("toDtoList")
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(String.format(LIST_TEMPLATE, mainEntity.getName()))
                                .name(ENTITY_SUFFIX.toLowerCase() + "List")
                                .build()))
                .build();

        basicMapStructMethods.add(toDtoMethod);
        basicMapStructMethods.add(toEntityMethod);
        basicMapStructMethods.add(toDtoListMethod);
        return basicMapStructMethods;
    }

    private MethodEntity getToDtoLoopPreventMethod(String loopPossibleFieldName, String mappedFieldName) {
        return MethodEntity.builder()
                .annotations(new IndentList<AnnotationEntity>(
                        AnnotationEntity.builder()
                                .name("Mapping")
                                .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                        PropertyEntity.builder()
                                                .name("target")
                                                .quotedValue(mappedFieldName)
                                                .build(),
                                        PropertyEntity.builder()
                                                .name("ignore")
                                                .simpleValue("true")
                                                .build()))
                                .build()))
                .returnType(StringUtils.capitalize(loopPossibleFieldName + DTO_SUFFIX))
                .name(StringUtil.toCamelCase(loopPossibleFieldName, true)
                        + "To" + StringUtils.capitalize(loopPossibleFieldName + DTO_SUFFIX))
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(StringUtils.capitalize(loopPossibleFieldName))
                                .name(loopPossibleFieldName.toLowerCase())
                                .build()))
                .build();
    }

    private MethodEntity getToEntityLoopPreventMethod(String relationFieldName, String entityName) {
        return MethodEntity.builder()
                .annotations(new IndentList<AnnotationEntity>(
                        AnnotationEntity.builder()
                                .name("Mapping")
                                .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                        PropertyEntity.builder()
                                                .name("target")
                                                .quotedValue(entityName.toLowerCase())
                                                .build(),
                                        PropertyEntity.builder()
                                                .name("ignore")
                                                .simpleValue("true")
                                                .build()))
                                .build()))
                .returnType(StringUtils.capitalize(relationFieldName))
                .name(StringUtil.toCamelCase(relationFieldName, true) + DTO_SUFFIX + "To" + StringUtils.capitalize(relationFieldName))
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(StringUtils.capitalize(relationFieldName + DTO_SUFFIX))
                                .name(DTO_SUFFIX.toLowerCase())
                                .build()))
                .build();
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
                        .value(JAVA_UTIL_PACKAGE + ".*")
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
                        .value(JAVA_UTIL_PACKAGE + ".*")
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
                    .relation(requestField.getRelation())
                    .array(requestField.isArray())
                    .build());
        }

        if (DomainHelper.needSoftDeleteField(entity)) {
            fields.add(FieldEntity.builder()
                    .modifiers(getPrivateMod())
                    .type(StringUtils.capitalize(BOOLEAN))
                    .name("deleted")
                    .build());
        }

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
                    .relation(requestField.getRelation())
                    .schemaDescription(requestField.getSchemaDescription())
                    .array(requestField.isArray())
                    .build());
        }

        if (DomainHelper.needSoftDeleteField(dtoEntity)) {
            dtoFields.add(FieldEntity.builder()
                    .modifiers(getPrivateMod())
                    .type(StringUtils.capitalize(BOOLEAN))
                    .name("deleted")
                    .value("Boolean.FALSE")
                    .build());
        }

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

    public IndentList<MethodEntity> getEntityMethods(ClassEntity entity, List<ClassEntity> entities) {
        List<MethodEntity> methods = new ArrayList<>();

        for (FieldEntity field : entity.getFields()) {

            if (Objects.nonNull(field.getRelation())) {
                methods.addAll(setMethodsByRelation(field, entity.getName(), entities));
            }
        }

        return new IndentList<MethodEntity>(DelimiterType.INDENT, true, false, methods);
    }

    private Collection<? extends MethodEntity> setMethodsByRelation(FieldEntity field, String entityName, List<ClassEntity> entities) {
        switch (field.getRelation().getRelationType()) {
            case ONE_TO_ONE: return setOneToRelationMethods(field, entityName, entities, false);
            case MANY_TO_ONE: return setOneToRelationMethods(field, entityName, entities, true);
            case ONE_TO_MANY: return setListRelationMethods(field, entityName, entities, false);
            case MANY_TO_MANY: return setListRelationMethods(field, entityName, entities, true);
            default: return Collections.emptyList();
        }
    }

    private Collection<? extends MethodEntity> setListRelationMethods(FieldEntity field, String entityName, List<ClassEntity> entities, boolean isManyToMany) {
        List<MethodEntity> relationMethods = new ArrayList<>();

        FieldEntity mappedField = DomainHelper.getMappedFieldFromEntities(field, entityName, entities);
        String mappedFieldName = mappedField.getName();
        String mappedFieldType = mappedField.getType();
        String fieldName = field.getName();
        String fieldType = field.getType();

        MethodEntity setListMethod = MethodEntity.builder()
                .modifiers(getPublicMod())
                .returnType(VOID)
                .name("set" + StringUtils.capitalize(field.getName()))
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type("Set<" + field.getType() + ">")
                                .name(field.getName())
                                .build()))
                .body(this.getListSetterBody(fieldName, mappedFieldName, fieldType, mappedFieldType, isManyToMany))
                .build();

        MethodEntity getListMethod = MethodEntity.builder()
                .modifiers(getPublicMod())
                .returnType("Set<" + field.getType() + ">")
                .name("get" + StringUtils.capitalize(field.getName()))
                .body(getListGetterBody(fieldName))
                .build();

        MethodEntity addMethod = MethodEntity.builder()
                .modifiers(getPublicMod())
                .returnType(VOID)
                .name("add" + StringUtil.toSingle(StringUtils.capitalize(field.getName())))
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(field.getType())
                                .name(field.getType().toLowerCase())
                                .build()))
                .body(getAddToListBody(fieldName, mappedFieldName, fieldType, mappedFieldType, isManyToMany))
                .build();

        MethodEntity removeMethod = MethodEntity.builder()
                .modifiers(getPublicMod())
                .returnType(VOID)
                .name("remove" + StringUtil.toSingle(StringUtils.capitalize(field.getName())))
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(field.getType())
                                .name(field.getType().toLowerCase())
                                .build()))
                .body(getRemoveFromListBody(fieldName, mappedFieldName, fieldType, mappedFieldType, isManyToMany))
                .build();

        relationMethods.add(setListMethod);
        relationMethods.add(getListMethod);
        relationMethods.add(addMethod);
        relationMethods.add(removeMethod);

        return relationMethods;
    }

    private Collection<? extends MethodEntity> setOneToRelationMethods(FieldEntity field, String entityName, List<ClassEntity> entities, boolean isManyToOne) {
        List<MethodEntity> relationMethods = new ArrayList<>();

        FieldEntity mappedField = DomainHelper.getMappedFieldFromEntities(field, entityName, entities);
        String mappedFieldName = mappedField.getName();
        String mappedFieldType = mappedField.getType();
        String fieldName = field.getName();
        String fieldType = field.getType();

        MethodEntity setMethod = MethodEntity.builder()
                .modifiers(getPublicMod())
                .returnType(VOID)
                .name("set" + StringUtils.capitalize(field.getName()))
                .args(new EnumerationList<ArgumentEntity>(false,
                        ArgumentEntity.builder()
                                .type(field.getType())
                                .name("new" + StringUtils.capitalize(field.getName()))
                                .build()))
                .body(getSetterRelationBody(fieldName, mappedFieldName, fieldType, mappedFieldType, isManyToOne))
                .build();

        relationMethods.add(setMethod);

        return relationMethods;
    }

    private String getSetterRelationBody(String fieldName, String mappedFieldName, String fieldType, String mappedFieldType, boolean isManyToOne) {
        return "if (Objects.equals(" + fieldName + ", new" + StringUtils.capitalize(fieldName) + ")) { return; }\n" +
                "\n" +
                "\t\t" + fieldType + " old" + StringUtils.capitalize(fieldName) + " = " + fieldName + ";\n" +
                "\t\t" + fieldName + " = new" + StringUtils.capitalize(fieldName) + ";\n" +
                "\t\tif (Objects.nonNull(old" + StringUtils.capitalize(fieldName) + "))\n" +
                "\t\t\told" + StringUtils.capitalize(fieldName) + (isManyToOne ?
                        ".remove" + StringUtil.toSingle(StringUtils.capitalize(mappedFieldName)) + "(this);\n"
                        : ".set" + StringUtils.capitalize(mappedFieldName) + "(NULL);\n") +
                "\n" +
                "\t\tif (Objects.nonNull(" + fieldName + "))\n" +
                "\t\t\t" + fieldName + (isManyToOne ?
                        ".add" + StringUtil.toSingle(StringUtils.capitalize(mappedFieldName)) + "(this);\n"
                        : ".set" + StringUtils.capitalize(mappedFieldName) + "(this);");
    }

    private String getListSetterBody(String fieldName, String mappedFieldName, String fieldType, String mappedFieldType, boolean isManyToMany) {
        return "if (Objects.nonNull("+ fieldName + ") && !" + fieldName + ".isEmpty()) {\n" +
                "\t\t\t" + fieldName + ".forEach(" + fieldType.toLowerCase()
                    + " -> " + fieldType.toLowerCase() + (isManyToMany ?
                        ".add" + StringUtil.toSingle(StringUtils.capitalize(mappedFieldName))
                        : ".set" + StringUtils.capitalize(mappedFieldName))
                    + "(this));\n" +
                "\t\t\tthis." + fieldName + " = " + fieldName + ";\n" +
                "\t\t}";
    }

    private String getListGetterBody(String fieldName) {
        return "return new HashSet<>(" + fieldName + ");";
    }

    private String getAddToListBody(String fieldName, String mappedFieldName, String fieldType, String mappedFieldType, boolean isManyToMany) {
        return "if (Objects.isNull(" + fieldType.toLowerCase() + ") || " + fieldName + ".contains(" + fieldType.toLowerCase() + ")) {\n" +
                "\t\t\treturn;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t" + fieldName + ".add(" + fieldType.toLowerCase() + ");\n" +
                "\t\t" + fieldType.toLowerCase() + (isManyToMany ?
                    ".add" + StringUtil.toSingle(StringUtils.capitalize(mappedFieldName))
                    : ".set" + StringUtils.capitalize(mappedFieldName))
                    + "(this);";
    }

    private String getRemoveFromListBody(String fieldName, String mappedFieldName, String fieldType, String mappedFieldType, boolean isManyToMany) {
        return "if (!" + fieldName + ".contains(" + fieldType.toLowerCase() + ")) {\n" +
                "\t\t\treturn;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t" + fieldName + ".remove(" + fieldType.toLowerCase() + ");\n" +
                "\t\t" + fieldType.toLowerCase() + (isManyToMany ?
                    ".remove" + StringUtil.toSingle(StringUtils.capitalize(mappedFieldName)) + "(this);"
                    : ".set" + StringUtils.capitalize(mappedFieldName) + "(NULL);");
    }

//    public IndentList<MethodEntity> getEqualsAndHashMethods(String entityName) {
//        List<MethodEntity> methods = new ArrayList<>();
//
//        MethodEntity equalsMethod = MethodEntity.builder()
//                .annotations(new IndentList<AnnotationEntity>(
//                        AnnotationEntity.builder().name("Override").build()))
//                .modifiers(getPublicMod())
//                .returnType(BOOLEAN)
//                .name(EQUALS)
//                .args(new EnumerationList<ArgumentEntity>(false,
//                        ArgumentEntity.builder()
//                                .type("Object").name("o")
//                                .build()))
//                .body("if (this == o) return true;\n" +
//                        "\t\tif (Objects.isNull(o) || getClass() != o.getClass()) return false;\n" +
//                        "\t\t"+ entityName + SPACE + entityName.toLowerCase()
//                        + SPACE + EQUAL + SPACE + OPEN_PARAM_BRACKET + entityName + CLOSE_PARAM_BRACKET
//                        + " o;\n" +
//                        "\t\treturn Objects.equals(id, "+ entityName.toLowerCase() +".id);")
//                .build();
//
//        MethodEntity hashCodeMethod = MethodEntity.builder()
//                .annotations(new IndentList<AnnotationEntity>(
//                        AnnotationEntity.builder().name("Override").build()))
//                .modifiers(getPublicMod())
//                .returnType(INT)
//                .name(HASHCODE)
//                .body("return Objects.hash(id);")
//                .build();
//
//        methods.add(equalsMethod);
//        methods.add(hashCodeMethod);
//
//        return new IndentList<MethodEntity>(DelimiterType.INDENT, true, false, methods);
//    }
}
