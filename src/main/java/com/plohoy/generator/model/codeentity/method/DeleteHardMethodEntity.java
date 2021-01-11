package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

@Data
public class DeleteHardMethodEntity extends MethodEntity {
    public DeleteHardMethodEntity(ClassEntity dtoEntity, String entryPointPath) {
        super(
                CodeTemplate.getPublicMod(),
                "ResponseEntity<String>",
                "deleteHard",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<>(
                                        AnnotationEntity.builder()
                                                .name("PathVariable")
                                                .property(PropertyEntity.builder()
                                                        .quotedValue("id")
                                                        .build())
                                                .build()))
                                .type(dtoEntity.getIdType())
                                .name("id")
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("DeleteMapping")
                                .properties(new EnumerationList<PropertyEntity>(DelimiterType.COMMA, false,
                                        PropertyEntity.builder()
                                                .name("quotedValue")
                                                .quotedValue(entryPointPath)
                                                .build(),
                                        PropertyEntity.builder()
                                                .name("produces")
                                                .quotedValue("text/plain")
                                                .build()))
                                .build()),
                "return service.deleteHard(id);"
        );
    }

    public DeleteHardMethodEntity(ClassEntity entity, ClassEntity dtoEntity) {
        super(
                CodeTemplate.getPublicMod(),
                "ResponseEntity<String>",
                "deleteHard",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .type(dtoEntity.getIdType())
                                .name("id")
                                .build()),
                null,
                null,
                "try {\n" +
                        "            repository.deleteById(id);\n" +
                        "        } catch (EmptyResultDataAccessException e) {\n" +
                        "            return new ResponseEntity<>(\n" +
                        "                    \"The entity with ID: \" + id + \" wasn't found in DB\",\n" +
                        "                    HttpStatus.NOT_FOUND\n" +
                        "            );\n" +
                        "        } catch (Exception e) {\n" +
                        "            return new ResponseEntity<>(\n" +
                        "                    \"Something went wrong!\",\n" +
                        "                    HttpStatus.INTERNAL_SERVER_ERROR\n" +
                        "            );\n" +
                        "        }\n" +
                        "        return new ResponseEntity<>(\n" +
                        "                \"The entity with ID: \" + id + \" was successfully deleted from DB\",\n" +
                        "                HttpStatus.OK\n" +
                        "        );"
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
