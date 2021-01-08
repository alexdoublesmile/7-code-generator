package com.plohoy.generator.model.codeentity;

import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

import java.util.Arrays;

@Data
public class CreateMethodEntity extends MethodEntity {
    public CreateMethodEntity(String returnType) {
        super(
                CodeTemplate.getPublicModifier(),
                returnType,
                "create",
                new EnumerationList<>(DelimiterType.NONE, false,
                        Arrays.asList(ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(DelimiterType.SPACE, true,
                                        Arrays.asList(
                                                AnnotationEntity.builder()
                                                        .name("Parameter")
                                                        .property(PropertyEntity.builder()
                                                                .name("required")
                                                                .value("true")
                                                                .build())
                                                        .build(),
                                                AnnotationEntity.builder()
                                                        .name("RequestBody")
                                                        .build()
                                        )))
                                .type(returnType)
                                .name("dto")
                                .build())),
                null,
                new IndentList<>(DelimiterType.NONE, true,
                        Arrays.asList(AnnotationEntity.builder()
                                        .name("PostMapping")
                                        .property(PropertyEntity.builder()
                                                .name("produces")
                                                .value("application/json")
                                                .build())
                                        .build())
                        ),
                "return service.save(dto);"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
