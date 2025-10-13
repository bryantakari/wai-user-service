package com.app.wai.user_service.common.exception.dto;

import com.app.wai.user_service.common.exception.ApplicationErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an error message response for API exceptions.
 * This class is used to structure error details in a standardized format.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({ "code", "message", "status", "timestamp", "path", "details" })
public class ErrorMessage {

    private int code;
    private Object message;
    private Date timestamp;
    private String path;

    @JsonIgnore
    private ApplicationErrorCode error;

    @Builder.Default
    private List<ErrorDetail> details = List.of();

    public String getStatus() {
        return this.error.name();
    }

    /**
     * Retrieves the error message.
     * If the message is not set, it returns the reason phrase from the application error code.
     *
     * @return the error message as a string
     */
    public String getMessage() {
        if (message != null) {
            return message.toString();
        }
        return this.error.getReasonPhrase();
    }

}
