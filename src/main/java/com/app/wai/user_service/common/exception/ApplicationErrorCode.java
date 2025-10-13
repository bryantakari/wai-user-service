package com.app.wai.user_service.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Application Error Code.
 */
@Getter
public enum ApplicationErrorCode {
    /**
     * Database error.
     */
    DATABASE_ERROR(1, "Database error", HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * Item not found error.
     */
    ITEM_NOT_FOUND(2, "Item not found", HttpStatus.NOT_FOUND),

    /**
     * Bad request error.
     */
    BAD_REQUEST(3, "Bad request", HttpStatus.BAD_REQUEST),


    /**
     * Access Denied Error.
     */
    FORBIDDEN(4, "Access Denied", HttpStatus.FORBIDDEN),

    /**
     * Internal server error.
     */
    INTERNAL_SERVER_ERROR(5, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * Conflict error.
     */
    CONFLICT(6, "Conflict", HttpStatus.CONFLICT),

    /**
     * Unauthorized error.
     */
    UNAUTHORIZED(7, "Unauthorized", HttpStatus.UNAUTHORIZED);


    /**
     * -- GETTER -- Return the integer value of this error code.
     */
    private final int errorCode;

    /**
     * -- GETTER -- Return the reason phrase of this error code.
     */
    private final String reasonPhrase;

    /**
     * -- GETTER -- Return the HTTP status code object.
     */
    private final HttpStatus httpStatus;

    ApplicationErrorCode(int errorCode, String reasonPhrase, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.reasonPhrase = reasonPhrase;
        this.httpStatus = httpStatus;
    }

}
