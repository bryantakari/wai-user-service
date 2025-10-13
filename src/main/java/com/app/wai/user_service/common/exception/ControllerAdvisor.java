package com.app.wai.user_service.common.exception;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import com.app.wai.user_service.common.exception.dto.BaseErrorResponse;
import com.app.wai.user_service.common.exception.dto.ErrorMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * The Controller advisor.
 */
@RestControllerAdvice
public class ControllerAdvisor {

    /**
     * Handle service exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<BaseErrorResponse> handleServiceException(ServiceException ex,
        WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder().timestamp(new Date())
            .code(ex.getHttpStatusValue()).error(ex.getErrorCode()).message(ex.getMessage())
            .path(((ServletWebRequest) request).getRequest().getRequestURI())
            .details(ex.getDetails()).build();
        BaseErrorResponse response =
            BaseErrorResponse.builder().error(errorMessage).build();
        return ResponseEntity.status(HttpStatus.valueOf(ex.getHttpStatusValue()))
            .contentType(APPLICATION_JSON)
            .body(response);
    }

    /**
     * Handle argument not valid exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> handleArgumentNotValidException(
        MethodArgumentNotValidException ex, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        final List<String> customFieldErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            final String field = fieldError.getField();
            final String message = fieldError.getDefaultMessage();
            customFieldErrors.add(field + " " + message);
        }

        ErrorMessage errorMessage = ErrorMessage.builder()
            .timestamp(new Date())
            .error(ApplicationErrorCode.BAD_REQUEST).message(customFieldErrors)
            .code(HttpStatus.BAD_REQUEST.value())
            .path(((ServletWebRequest) request).getRequest().getRequestURI())
            .build();

        BaseErrorResponse response = BaseErrorResponse.builder()
            .error(errorMessage)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .contentType(APPLICATION_JSON)
            .body(response);
    }
}
