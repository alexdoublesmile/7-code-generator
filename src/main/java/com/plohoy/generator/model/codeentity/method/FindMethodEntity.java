package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.Arrays;

@Data
public class FindMethodEntity extends MethodEntity {
    public FindMethodEntity(ClassEntity dtoEntity, String entryPointPath) {
        super(
                CodeTemplate.getPublicMod(),
                dtoEntity.getName(),
                "find",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                    ArgumentEntity.builder()
                            .annotations(new EnumerationList<>(
                                    AnnotationEntity.builder()
                                            .name("PathVariable")
                                            .value("id")
                                            .build()))
                            .type(dtoEntity.getIdType())
                            .name("id")
                            .build()),
                null,
                new IndentList<AnnotationEntity>(
                        AnnotationEntity.builder()
                            .name("GetMapping")
                            .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                    PropertyEntity.builder()
                                            .name("quotedValue")
                                            .quotedValue(entryPointPath)
                                            .build(),
                                    PropertyEntity.builder()
                                            .name("produces")
                                            .quotedValue("application/json")
                                            .build()))
                            .build()),
                "return service.findById(id);"
        );
    }

    public FindMethodEntity(ClassEntity entity, ClassEntity dtoEntity) {
        super(
                CodeTemplate.getPublicMod(),
                dtoEntity.getName(),
                "find",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                            .type(dtoEntity.getIdType())
                            .name("id")
                            .build()),
                null,
                null,
                "return mapper.toDto(\n" +
                        "                repository.findByIdAndDeleted(id, false)\n" +
                        "                        .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); }));"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
