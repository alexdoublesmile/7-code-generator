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

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.DTO_SUFFIX;
import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.ID;

@Data
public class UpdateMethodEntity extends MethodEntity {
    public UpdateMethodEntity(ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                dtoEntity.getName(),
                "update",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA,false,
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(
                                        ArgumentAnnotationEntity.builder()
                                                    .name("PathVariable")
                                                    .value(ID)
                                                    .build()))
                                .type(dtoEntity.getIdType())
                                .name(ID)
                                .build(),
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(
                                        ArgumentAnnotationEntity.builder()
                                                    .name("RequestBody")
                                                    .build()))
                                .type(dtoEntity.getName())
                                .name(DTO_SUFFIX.toLowerCase())
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("PutMapping")
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
                "return service.update(id, dto);",
                endPoint
        );
    }

    public UpdateMethodEntity(ClassEntity entity, ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                dtoEntity.getName(),
                "update",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA,false,
                        ArgumentEntity.builder()
                                .type(dtoEntity.getIdType())
                                .name(ID)
                                .build(),
                        ArgumentEntity.builder()
                                .type(dtoEntity.getName())
                                .name(DTO_SUFFIX.toLowerCase())
                                .build()),
                null,
                null,
                "if (Objects.nonNull(dto.getId())\n" +
                        "                && !dto.getId().equals(id)) {\n" +
                        "            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, idsDoNotMatchMessage);\n" +
                        "        }\n" +
                        "        dto.setId(id);\n" +
                        "\n" +
                        "        return mapper.toDto(\n" +
                        "                repository.save(\n" +
                        "                        mapper.toEntity(dto)));",
                endPoint
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
