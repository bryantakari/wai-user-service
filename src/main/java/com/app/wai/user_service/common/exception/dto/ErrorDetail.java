package com.app.wai.user_service.common.exception.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a detailed error message that provides additional information about an exception.
 * This abstract class is intended to be extended by specific error detail implementations.
 */
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true)
@SuperBuilder
public abstract class ErrorDetail implements Serializable {

    private String reason;
    private String description;

    protected ErrorDetail(String reason, String description) {
        this.reason = reason;
        this.description = description;
    }

}
