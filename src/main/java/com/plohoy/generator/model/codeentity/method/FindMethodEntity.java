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
public class FindMethodEntity extends MethodEntity {
    public FindMethodEntity(ClassEntity dtoClass, String entryPointPath) {
        super(
                CodeTemplate.getPublicModifier(),
                dtoClass.getName(),
                "find",
                new EnumerationList<>(DelimiterType.NONE, false,
                        Arrays.asList(ArgumentEntity.builder()
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
                                                        .build()
                                        )))
                                .type(dtoClass.getFields().get(0).getType())
                                .name("id")
                                .build())),
                null,
                new IndentList<>(DelimiterType.NONE, true,
                        Arrays.asList(AnnotationEntity.builder()
                                .name("GetMapping")
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
                "return service.findById(id);"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
