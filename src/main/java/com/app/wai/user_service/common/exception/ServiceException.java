package com.app.wai.user_service.common.exception;

import com.app.wai.user_service.common.exception.dto.ErrorDetail;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Service Exception for any exception error in service class.
 */
@Getter
public class ServiceException extends RuntimeException {
    private final ApplicationErrorCode errorCode;

    private final List<ErrorDetail> details;

    /**
     * Constructs a new ServiceException with the specified error code.
     *
     * @param errorCode the application-specific error code
     */
    public ServiceException(ApplicationErrorCode errorCode) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
        this.details = List.of();
    }

    /**
     * Constructs a new ServiceException with the specified error code and details.
     *
     * @param errorCode the application-specific error code
     * @param details   additional error details
     */
    public ServiceException(ApplicationErrorCode errorCode, List<ErrorDetail> details) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
        this.details = details;
    }

    /**
     * Constructs a new ServiceException with the specified message and error code.
     *
     * @param message   the error message
     * @param errorCode the application-specific error code
     */
    public ServiceException(String message, ApplicationErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.details = List.of();
    }

    /**
     * Constructs a new ServiceException with the specified message, error code, and details.
     *
     * @param message   the error message
     * @param errorCode the application-specific error code
     * @param details   additional error details
     */
    public ServiceException(
        String message, ApplicationErrorCode errorCode, List<ErrorDetail> details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }


    protected HttpStatus getHttpStatus() {
        return this.errorCode.getHttpStatus();
    }

    protected int getHttpStatusValue() {
        return this.errorCode.getHttpStatus().value();
    }

}
