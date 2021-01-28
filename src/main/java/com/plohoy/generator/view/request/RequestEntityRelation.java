package com.plohoy.generator.view.request;

import com.plohoy.generator.model.codeentity.annotation.PropertyEntity;
import com.plohoy.generator.model.codeentity.field.RelationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestEntityRelation {
    private RelationType relationType;
    private boolean relationOwner;
    private List<PropertyEntity> properties;
}
