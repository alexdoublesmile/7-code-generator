package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.ArgumentAnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.ID;

@Data
public class DeleteSoftMethodEntity extends MethodEntity {
    public DeleteSoftMethodEntity(ClassEntity dto, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                dto.getName(),
                "deleteSoft",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<ArgumentAnnotationEntity>(true,
                                        ArgumentAnnotationEntity.builder()
                                                .name("PathVariable")
                                                .property(PropertyEntity.builder().quotedValue(ID).build())
                                                .build()))
                                .type(dto.getIdType())
                                .name("id")
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("DeleteMapping")
                                .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                        PropertyEntity.builder()
                                                .name("value")
                                                .quotedValue(endPoint.getPath())
                                                .build(),
                                        PropertyEntity.builder()
                                                .name("produces")
                                                .quotedValue("application/json")
                                                .build()))
                                .build()),
                "return service.deleteSoft(id);",
                endPoint
        );
    }

    public DeleteSoftMethodEntity(ClassEntity entity, ClassEntity dto, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                dto.getName(),
                "deleteSoft",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .type(dto.getIdType())
                                .name("id")
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("Transactional")
                                .build()),
                entity.getName() + " entityFromDB = repository.findById(id)\n" +
                        "                .orElseThrow(() -> { throw new ResponseStatusException(\n" +
                        "                        HttpStatus.NOT_FOUND,\n" +
                        "                        \"The entity with ID: \" + id + \" wasn't found in DB\"); });\n" +
                        "        entityFromDB.setDeleted(true);\n" +
                        "\n" +
                        "        return mapper.toDto(\n" +
                        "                repository.save(entityFromDB));",
                endPoint
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
