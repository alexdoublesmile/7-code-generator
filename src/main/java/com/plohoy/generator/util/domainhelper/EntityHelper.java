package com.plohoy.generator.util.domainhelper;

import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.file.AbstractSourceFile;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

@UtilityClass
public class EntityHelper {

    public boolean needSoftDeleteField(ClassEntity entity) {
        return Objects.nonNull(entity.getEndPoints()) && entity.getEndPoints()
                .stream()
                .map(endPoint -> endPoint.getType())
                .anyMatch((type) ->
                        type.equals(EndPointType.DELETE_SOFTLY_END_POINT));
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

    public static boolean needValidation(ClassEntity dtoEntity) {
        return dtoEntity.getFields()
                .stream()
                .anyMatch(FieldHelper::needValidation);
    }
}
