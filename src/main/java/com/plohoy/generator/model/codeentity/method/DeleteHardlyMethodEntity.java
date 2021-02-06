package com.plohoy.generator.model.codeentity.method;

import com.plohoy.generator.model.EndPoint;
import com.plohoy.generator.model.EndPointType;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.ArgumentAnnotationEntity;
import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.clazz.ClassEntity;
import com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate;
import com.plohoy.generator.util.stringhelper.list.DelimiterType;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import lombok.Data;

@Data
public class DeleteHardlyMethodEntity extends MethodEntity {
    public DeleteHardlyMethodEntity(ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                "ResponseEntity<String>",
                "deleteHardly",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .annotations(new EnumerationList<ArgumentAnnotationEntity>(
                                        ArgumentAnnotationEntity.builder()
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
                                                .name("value")
                                                .quotedValue(endPoint.getPath())
                                                .build(),
                                        PropertyEntity.builder()
                                                .name("produces")
                                                .quotedValue("text/plain")
                                                .build()))
                                .build()),
                "return service.deleteHardly(id);",
                endPoint
        );
    }

    public DeleteHardlyMethodEntity(ClassEntity entity, ClassEntity dtoEntity, EndPoint endPoint) {
        super(
                CodeTemplate.getPublicMod(),
                "ResponseEntity<String>",
                "deleteHardly",
                new EnumerationList<ArgumentEntity>(DelimiterType.COMMA, false,
                        ArgumentEntity.builder()
                                .type(dtoEntity.getIdType())
                                .name("id")
                                .build()),
                null,
                new IndentList<>(
                        AnnotationEntity.builder()
                                .name("Transactional")
                                .build()),
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
                        "        );",
                endPoint
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
