package com.plohoy.generator.util.domainhelper;

import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.field.RelationType;
import com.plohoy.generator.model.codeentity.method.MethodEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@UtilityClass
public class DomainHelper {

    public boolean needSoftDeleteField(ClassEntity entity) {
        return Objects.nonNull(entity.getEndPoints()) && entity.getEndPoints()
                .stream()
                .map(endPoint -> endPoint.getType())
                .anyMatch((type) ->
                        type.equals(EndPointType.DELETE_SOFTLY_END_POINT));
    }

    public boolean hasOneToOneRelation(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && field.getRelation().getRelationType().equals(RelationType.ONE_TO_ONE);
    }

    public boolean hasOneToManyRelation(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && field.getRelation().getRelationType().equals(RelationType.ONE_TO_MANY);
    }

    public boolean hasManyToOneRelation(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && field.getRelation().getRelationType().equals(RelationType.MANY_TO_ONE);
    }

    public boolean hasOneToRelation(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && (field.getRelation().getRelationType().equals(RelationType.ONE_TO_ONE)
                    || field.getRelation().getRelationType().equals(RelationType.ONE_TO_MANY)
                    || field.getRelation().getRelationType().equals(RelationType.MANY_TO_ONE));
    }

    public boolean hasManyToManyRelation(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && field.getRelation().getRelationType().equals(RelationType.MANY_TO_MANY);
    }

    public boolean isRelationOwner(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && field.getRelation().isRelationOwner();
    }

    public static boolean isOneToOneBackReference(FieldEntity field) {
        return hasOneToOneRelation(field) && !isRelationOwner(field);
    }

    public static boolean isManyToManyBackReference(FieldEntity field) {
        return hasManyToManyRelation(field) && !isRelationOwner(field);
    }

    public boolean isOneToBackReference(FieldEntity field) {
        return DomainHelper.hasOneToRelation(field) && !isRelationOwner(field);
    }

    public static boolean isSingleClassLoopPossible(FieldEntity field, String mainEntityName) {
        return Objects.equals(mainEntityName, field.getType())
            && (hasOneToManyRelation(field)
                    || ((hasOneToOneRelation(field) || hasManyToManyRelation(field)) && isRelationOwner(field))
        );
    }

    public static boolean isExternalTypeField(FieldEntity field, String mainEntityName) {
        return !Objects.equals(mainEntityName, field.getType());
    }

    public static boolean hasAnyRelations(FieldEntity field) {
        return hasOneToRelation(field) || hasManyToManyRelation(field);
    }

    public FieldEntity getMappedFieldFromFiles(FieldEntity relationField, String entityName, List<AbstractSourceFile> entityFiles) {
        FieldEntity mappedField = null;
        List<FieldEntity> mappedFieldList;
        String mappedClassName = relationField.getType();
        String relationName = relationField.getRelation().getRelationName();

        for (AbstractSourceFile<ClassEntity> file : entityFiles) {
            ClassEntity someEntity = file.getData();

            if (mappedClassName.equals(someEntity.getName())) {
                mappedFieldList = someEntity.getFields()
                        .stream()
                        .filter(someField -> Objects.nonNull(relationName)
                                ? relationName.equals(someField.getName())
                                : entityName.equals(someField.getType()))
                        .collect(Collectors.toList());

                if (mappedFieldList.size() != 1) {

                    String mappedFieldsString = EMPTY;
                    for (FieldEntity field : mappedFieldList) {
                        mappedFieldsString += TAB + field.getType() + SPACE + field.getName() + INDENT;
                    }

                    String exceptionMessage = mappedFieldList.size() == 0 ?
                            "\n\nThere are no mapped fields for \"" + relationField.getType() + " " + relationField.getName() + "\". \nSet name of it's relation field.\n\n"
                            : "\n\nThere are more than one mapped fields for \"" + relationField.getType() + " " + relationField.getName() + "\":\n" + mappedFieldsString + "\n\nSet name of it's relation field.\n\n";

                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionMessage);
                }

                mappedField = mappedFieldList.get(0);
            }
        }

        return mappedField;
    }

    public static FieldEntity getMappedFieldFromEntities(FieldEntity relationField, String entityName, List<ClassEntity> entities) {
        FieldEntity mappedField = null;
        List<FieldEntity> mappedFieldList = new ArrayList<>();
        String mappedClassName = relationField.getType();
        String relationName = relationField.getRelation().getRelationName();

        for (ClassEntity someEntity : entities) {

            if (mappedClassName.equals(someEntity.getName())) {
                mappedFieldList = someEntity.getFields()
                        .stream()
                        .filter(someField -> Objects.nonNull(relationName)
                                ? relationName.equals(someField.getName())
                                : entityName.equals(someField.getType()))
                        .collect(Collectors.toList());

                if (mappedFieldList.size() != 1) {

                    String mappedFieldsString = EMPTY;
                    for (FieldEntity field : mappedFieldList) {
                        mappedFieldsString += TAB + field.getType() + SPACE + field.getName() + INDENT;
                    }

                    String exceptionMessage = mappedFieldList.size() == 0 ?
                            "\n\nThere are no mapped fields for \"" + relationField.getType() + " " + relationField.getName() + "\". \nSet name of it's relation field.\n\n"
                            : "\n\nThere are more than one mapped fields for \"" + relationField.getType() + " " + relationField.getName() + "\":\n" + mappedFieldsString + "\n\nSet name of it's relation field.\n\n";

                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionMessage);
                }

                mappedField = mappedFieldList.get(0);

            }
        }

        return mappedField;
    }

    public static ClassEntity getEntityByType(String type, List<AbstractSourceFile> entityFiles) {
        for (AbstractSourceFile<ClassEntity> file : entityFiles) {
            ClassEntity someEntity = file.getData();

            if (type.equals(someEntity.getName())) {
                return someEntity;
            }
        }
        return null;
    }
}
