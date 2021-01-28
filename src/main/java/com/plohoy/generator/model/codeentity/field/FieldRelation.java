package com.plohoy.generator.model.codeentity.field;

import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.util.stringhelper.list.impl.EnumerationList;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldRelation {
    private RelationType relationType;
    private boolean relationOwner;
    private EnumerationList<PropertyEntity> properties;
}
