package com.plohoy.generator.model.codeentity.field;

import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.codeentity.annotation.AnnotationEntity;
import com.plohoy.generator.util.stringhelper.StringUtil;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import com.plohoy.generator.util.stringhelper.list.impl.IndentList;
import com.plohoy.generator.view.request.RequestEntityRelation;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.*;

@Data
@Builder
public class FieldEntity extends CodeEntity {
    private EnumerationList<String> modifiers;
    private String type;
    private String name;
    private String value;
    private FieldValueEntity values;
    private IndentList<AnnotationEntity> annotations;
    private String schemaDescription;
    private FieldRelation relation;
    private boolean array;
    private boolean filter;
    private List<ValidationEntity> validationList;

    public FieldEntity(
            EnumerationList<String> modifiers,
            String type,
            String name,
            String value,
            FieldValueEntity values,
            IndentList<AnnotationEntity> annotations,
            String schemaDescription,
            FieldRelation relation,
            boolean array,
            boolean filter,
            List<ValidationEntity> validationList
    ) {
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
        this.value = value;
        this.schemaDescription = schemaDescription;
        this.relation = relation;
        this.array = array;
        this.filter = filter;
        this.validationList = validationList;

        if (Objects.nonNull(values)) setValues(values);
        if (Objects.nonNull(annotations)) setAnnotations(annotations);
        if (array) this.value = "new HashSet<>()";

    }

    public void setValues(FieldValueEntity values) {
        this.values = values;
        this.values.setParentEntity(this);
    }

    public void setAnnotations(IndentList<AnnotationEntity> annotations) {
        while (annotations.remove(null));

        this.annotations = annotations;
        this.annotations.stream().forEach(element -> element.setParentEntity(this));
    }

    public boolean isValidated() {
        return validationList.size() > 0;
    }

    @Override
    public FieldEntity setParentEntity(CodeEntity parentEntity) {
        this.parentEntity = parentEntity;
        return this;
    }

    @Override
    public String toString() {
        return annotations
                + StringUtil.checkForNull(modifiers,
                getTab(getNestLvl()) + modifiers)
                + (array ? "Set<" + type + ">" : BOOLEAN.equals(type) ? StringUtils.capitalize(type) : type)
                + SPACE + name
                + StringUtil.checkStringForNull(value,
                SPACE + EQUAL + SPACE + value)
                + values;
    }
}
