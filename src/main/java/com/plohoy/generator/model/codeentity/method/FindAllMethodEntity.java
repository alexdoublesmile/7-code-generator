package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.ArgumentAnnotationEntity;
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
    public FindAllMethodEntity(ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                String.format("Page<%s>", dtoEntity.getName()),
                "findAll",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(
                                        ArgumentAnnotationEntity.builder()
                                                .name("PageableDefault")
                                                .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                                        PropertyEntity.builder()
                                                                .name("sort")
                                                                .simpleValue("{ \"id\" }")
                                                                .build(),
                                                        PropertyEntity.builder()
                                                                .name("direction")
                                                                .simpleValue("Sort.Direction.DESC")
                                                                .build()))
                                                .build()))
                                .type("Pageable")
                                .name("pageable")
                                .build(),
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(
                                        ArgumentAnnotationEntity.builder()
                                                .name("RequestParam")
                                                .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                                        PropertyEntity.builder()
                                                                .name("required")
                                                                .simpleValue("false")
                                                                .build(),
                                                        PropertyEntity.builder()
                                                                .name("defaultValue")
                                                                .quotedValue("false")
                                                                .build()))
                                                .build()))
                                .type("boolean")
                                .name("deleted")
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("GetMapping")
                                .property(PropertyEntity.builder()
                                        .name("produces")
                                        .quotedValue("application/json")
                                        .build())
                                .build()),
                "return service.findAll(pageable, deleted);",
                endPoint
        );
    }

    public FindAllMethodEntity(ClassEntity entity, ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                String.format("Page<%s>", dtoEntity.getName()),
                "findAll",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .type("Pageable")
                                .name("pageable")
                                .build(),
                        ArgumentEntity.builder()
                                .type("boolean")
                                .name("deleted")
                                .build()),
                null,
                null,
                "List<" + dtoEntity.getName() + "> dtoList;\n" +
                        "\n" +
                        "        if (deleted) {\n" +
                        "            dtoList = mapper.toDtoList(repository.findAll());\n" +
                        "        } else {\n" +
                        "            dtoList = mapper.toDtoList(repository.findByDeleted(false));\n" +
                        "        }\n" +
                        "\n" +
                        "        int start = (int) pageable.getOffset();\n" +
                        "        int end = (start + pageable.getPageSize()) > dtoList.size() ? dtoList.size()\n" +
                        "                : (start + pageable.getPageSize());\n" +
                        "\n" +
                        "        return new PageImpl<>(\n" +
                        "                dtoList.subList(start, end),\n" +
                        "                pageable,\n" +
                        "                dtoList.size());",
                endPoint
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
