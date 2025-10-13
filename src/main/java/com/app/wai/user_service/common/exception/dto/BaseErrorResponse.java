package com.app.wai.user_service.common.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * A response class that represents an error response.
 * This class is used to return error information along with a success flag to indicate
 * the status of the operation. It includes an {@link ErrorMessage} object to provide
 * detailed information about the error.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseErrorResponse {
    private ErrorMessage error;
}
