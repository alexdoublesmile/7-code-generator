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
public class DeleteHardMethodEntity extends MethodEntity {
    public DeleteHardMethodEntity(ClassEntity dtoClass, String entryPointPath) {
        super(
                CodeTemplate.getPublicMod(),
                "ResponseEntity<String>",
                "deleteHard",
                new EnumerationList<>(DelimiterType.NONE, false,
                        Arrays.asList(ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(DelimiterType.SPACE, true,
                                        Arrays.asList(
                                                AnnotationEntity.builder()
                                                        .name("PathVariable")
                                                        .property(PropertyEntity.builder()
                                                                .quotedValue("id")
                                                                .build())
                                                        .build())))
                                .type(dtoClass.getFields().get(0).getType())
                                .name("id")
                                .build())),
                null,
                new IndentList<>(DelimiterType.NONE, true,
                        Arrays.asList(AnnotationEntity.builder()
                                .name("DeleteMapping")
                                .properties(new EnumerationList<>(DelimiterType.COMMA, false,
                                        Arrays.asList(
                                                PropertyEntity.builder()
                                                        .name("quotedValue")
                                                        .quotedValue(entryPointPath)
                                                        .build(),
                                                PropertyEntity.builder()
                                                        .name("produces")
                                                        .quotedValue("text/plain")
                                                        .build())))
                                .build())
                ),
                "return service.deleteHard(id);"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
