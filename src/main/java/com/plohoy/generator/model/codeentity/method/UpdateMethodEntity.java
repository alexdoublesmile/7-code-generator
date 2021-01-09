package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
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
    public UpdateMethodEntity(ClassEntity dtoClass, String entryPointPath) {
        super(
                CodeTemplate.getPublicMod(),
                dtoClass.getName(),
                "update",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA,false,
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(
                                            AnnotationEntity.builder()
                                                    .name("PathVariable")
                                                    .value(ID)
                                                    .build()))
                                .type(dtoClass.getIdType())
                                .name(ID)
                                .build(),
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(
                                            AnnotationEntity.builder()
                                                    .name("RequestBody")
                                                    .build()))
                                .type(dtoClass.getName())
                                .name(DTO_SUFFIX.toLowerCase())
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("PutMapping")
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
                "return service.update(id, dto);"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
