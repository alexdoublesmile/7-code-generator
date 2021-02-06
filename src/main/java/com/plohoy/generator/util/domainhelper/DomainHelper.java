package com.plohoy.generator.util.domainhelper;

import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.field.FieldEntity;
import com.plohoy.generator.model.codeentity.field.RelationType;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class DomainHelper {

    public boolean needSoftDeleteField(ClassEntity entity) {
        return Objects.nonNull(entity.getEndPoints()) && entity.getEndPoints()
                .stream()
                .map(endPoint -> endPoint.getType())
                .anyMatch((type) ->
                        type.equals(EndPointType.DELETE_SOFTLY_END_POINT));
    }

    public boolean isOneToOneBackReference(FieldEntity fieldEntity) {
        return DomainHelper.hasOneToOneRelation(fieldEntity) && !fieldEntity.getRelation().isRelationOwner();
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
                || field.getRelation().getRelationType().equals(RelationType.ONE_TO_MANY));
    }

    public boolean hasManyToManyRelation(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && field.getRelation().getRelationType().equals(RelationType.MANY_TO_MANY);
    }

    public boolean isRelationOwner(FieldEntity field) {
        return Objects.nonNull(field.getRelation())
                && field.getRelation().isRelationOwner();
    }
}
