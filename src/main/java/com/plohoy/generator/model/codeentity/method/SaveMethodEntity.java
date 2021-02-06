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

@Data
public class SaveMethodEntity extends MethodEntity {
    public SaveMethodEntity(ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                dtoEntity.getName(),
                "save",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<ArgumentAnnotationEntity>(
                                        ArgumentAnnotationEntity.builder()
                                                        .name("RequestBody")
                                                        .build()))
                                .type(dtoEntity.getName())
                                .name("dto")
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("PostMapping")
                                .property(PropertyEntity.builder()
                                        .name("produces")
                                        .quotedValue("application/json")
                                        .build())
                                .build()),
                "return service.save(dto);",
                endPoint
        );
    }

    public SaveMethodEntity(ClassEntity entity, ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                dtoEntity.getName(),
                "save",
                new EnumerationList<ArgumentEntity>( DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .type(dtoEntity.getName())
                                .name("dto")
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("Transactional")
                                .build()),
                "return mapper.toDto(\n" +
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
