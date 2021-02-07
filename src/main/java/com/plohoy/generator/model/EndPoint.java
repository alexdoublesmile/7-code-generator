package com.plohoy.generator.model;

import com.plohoy.generator.util.stringhelper.StringUtil;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class EndPoint {
    private static final String DEFAULT_ROOT_PATH = "/";
    private static final String DEFAULT_FIND_PATH = "/{id}";
    private static final String DEFAULT_DELETE_SOFTLY_PATH = "/{id}/delete_soft";
    private static final String DEFAULT_DELETE_HARDLY_PATH = "/{id}/delete_hard";
    private static final String DEFAULT_RESTORE_PATH = "/{id}/restore";
    private EndPointType type;
    private String path;
    private String description;
    private List<ResponseCode> responseCodes;
    private String entityName;

    public String getPath() {
        return Optional.ofNullable(path).orElseGet(() -> getPathByEndPoint(type));
    }

    private String getPathByEndPoint(EndPointType type) {
        switch (type) {
            case FIND_END_POINT:
            case UPDATE_END_POINT: return DEFAULT_FIND_PATH;
            case DELETE_SOFTLY_END_POINT: return DEFAULT_DELETE_SOFTLY_PATH;
            case DELETE_HARDLY_END_POINT: return DEFAULT_DELETE_HARDLY_PATH;
            case RESTORE_END_POINT: return DEFAULT_RESTORE_PATH;
            case CONTROLLER_END_POINT: return DEFAULT_ROOT_PATH + StringUtil.toSnakeCase(entityName);
            case CREATE_END_POINT:
            case FIND_ALL_END_POINT:
            default: return DEFAULT_ROOT_PATH;
        }
    }
}
