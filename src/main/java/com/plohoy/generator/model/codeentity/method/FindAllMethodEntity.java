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
public class FindAllMethodEntity extends MethodEntity {
    public FindAllMethodEntity(ClassEntity dtoClass, String entryPointPath) {
        super(
                CodeTemplate.getPublicMod(),
                String.format("Page<%s>", dtoClass.getName()),
                "findAll",
                new EnumerationList<>(DelimiterType.COMMA, false,
                        Arrays.asList(
                                ArgumentEntity.builder()
                                        .annotations(new EnumerationList<>(DelimiterType.SPACE, true,
                                                Arrays.asList(
                                                    AnnotationEntity.builder()
                                                            .name("RequestParam")
                                                            .properties(new EnumerationList<>(DelimiterType.COMMA, false,
                                                                    Arrays.asList(
                                                                            PropertyEntity.builder()
                                                                                    .name("required")
                                                                                    .quotedValue("false")
                                                                                    .build(),
                                                                            PropertyEntity.builder()
                                                                                    .name("defaultValue")
                                                                                    .quotedValue("false")
                                                                                    .build())))
                                                            .build())))
                                        .type("boolean")
                                        .name("deleted")
                                        .build(),
                                ArgumentEntity.builder()
                                        .annotations(new EnumerationList<>(DelimiterType.SPACE, true,
                                                Arrays.asList(
                                                    AnnotationEntity.builder()
                                                            .name("PageableDefault")
                                                            .properties(new EnumerationList<>(DelimiterType.COMMA, false,
                                                                    Arrays.asList(
                                                                            PropertyEntity.builder()
                                                                                    .name("sort")
                                                                                    .simpleValue("{ \"id\" }")
                                                                                    .build(),
                                                                            PropertyEntity.builder()
                                                                                    .name("direction")
                                                                                    .simpleValue("Sort.Direction.DESC")
                                                                                    .build())))
                                                            .build())))
                                        .type("Pageable")
                                        .name("pageable")
                                        .build())),
                null,
                new IndentList<>(DelimiterType.NONE, true,
                        Arrays.asList(AnnotationEntity.builder()
                                .name("GetMapping")
                                .property(PropertyEntity.builder()
                                        .name("produces")
                                        .quotedValue("application/json")
                                        .build())
                                .build())
                ),
                "return service.findAll(pageable, deleted);"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
