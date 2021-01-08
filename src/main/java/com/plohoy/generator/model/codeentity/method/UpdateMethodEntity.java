package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.codeentity.AnnotationEntity;
import com.plohoy.generator.model.codeentity.ClassEntity;
import com.plohoy.generator.model.codeentity.PropertyEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.Arrays;

@Data
public class UpdateMethodEntity extends MethodEntity {
    public UpdateMethodEntity(ClassEntity dtoClass, String entryPointPath) {
        super(
                CodeTemplate.getPublicModifier(),
                dtoClass.getName(),
                "update",
                new EnumerationList<>(DelimiterType.NONE, false,
                        Arrays.asList(
                                ArgumentEntity.builder()
                                        .annotations(new EnumerationList<>(DelimiterType.SPACE, true,
                                                Arrays.asList(
                                                        AnnotationEntity.builder()
                                                                .name("Parameter")
                                                                .properties(new EnumerationList<>(DelimiterType.COMMA, false,
                                                                        Arrays.asList(
                                                                                PropertyEntity.builder()
                                                                                        .name("description")
                                                                                        .value("some Description")
                                                                                        .build(),
                                                                                PropertyEntity.builder()
                                                                                        .name("required")
                                                                                        .value("true")
                                                                                        .build(),
                                                                                PropertyEntity.builder()
                                                                                        .name("example")
                                                                                        .value("some Example")
                                                                                        .build())))
                                                                .build(),
                                                        AnnotationEntity.builder()
                                                                .name("PathVariable")
                                                                .value("id")
                                                                .build())))
                                        .type(dtoClass.getFields().get(0).getType())
                                        .name("id")
                                        .build(),
                                ArgumentEntity.builder()
                                        .annotations(new EnumerationList<>(DelimiterType.SPACE, true,
                                                Arrays.asList(
                                                        AnnotationEntity.builder()
                                                                .name("Parameter")
                                                                .properties(new EnumerationList<>(DelimiterType.COMMA, false,
                                                                        Arrays.asList(
                                                                                PropertyEntity.builder()
                                                                                        .name("required")
                                                                                        .value("true")
                                                                                        .build())))
                                                                .build(),
                                                        AnnotationEntity.builder()
                                                                .name("RequestBody")
                                                                .build())))
                                        .type(dtoClass.getName())
                                        .name("dto")
                                        .build())),
                null,
                new IndentList<>(DelimiterType.NONE, true,
                        Arrays.asList(AnnotationEntity.builder()
                                .name("PutMapping")
                                .properties(new EnumerationList<>(DelimiterType.COMMA, false,
                                        Arrays.asList(
                                                PropertyEntity.builder()
                                                        .name("value")
                                                        .value(entryPointPath)
                                                        .build(),
                                                PropertyEntity.builder()
                                                        .name("produces")
                                                        .value("application/json")
                                                        .build())))
                                .build())
                ),
                "return service.update(id, dto);"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
