package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.ArgumentAnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.ID;

public class RestoreMethodEntity  extends MethodEntity {
    public RestoreMethodEntity(ClassEntity dto, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                dto.getName(),
                "restore",
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
                                .name("GetMapping")
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
                "return service.changeDeletedState(id, false);",
                endPoint
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
