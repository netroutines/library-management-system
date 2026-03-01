package com.netroutines.lms.api.advice;

import com.netroutines.lms.api.advice.exception.ApiException;
import com.netroutines.lms.api.advice.response.ErrorResponse;
import com.netroutines.lms.api.advice.response.ValidationErrorResponse;
import com.netroutines.lms.config.property.ProfileProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ProfileProperties profileProperties;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiExceptions(ApiException e) {
        log.warn("API exception: {} at {}", e.getMessage(), pathFrom(e));

        var body = new ErrorResponse(
                e.getStatus().value(),
                e.getStatus().name(),
                e.getMessage(),
                pathFrom(e),
                now()
        );

        return ResponseEntity.status(e.getStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        log.warn("Validation failed at {}: {}", pathFrom(e), e.getMessage());

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        var body = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                "Validation failed",
                errors,
                pathFrom(e),
                now()
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandler(NoHandlerFoundException e) {
        log.warn("Route not found: {}", e.getRequestURL());

        var body = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                "Route not found",
                e.getRequestURL(),
                now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedType(HttpMediaTypeNotSupportedException e) {
        log.warn("Unsupported media type at {}: {}", pathFrom(e), e.getContentType());

        var body = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "UNSUPPORTED_MEDIA_TYPE",
                "The content type is not supported",
                pathFrom(e),
                now()
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponse> handleNotAcceptable(HttpMediaTypeNotAcceptableException e) {
        log.warn("Not acceptable media type at {}: {}", pathFrom(e), e.getMessage());

        var body = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "NOT_ACCEPTABLE",
                "Requested media type is not supported",
                pathFrom(e),
                now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadJson(HttpMessageNotReadableException e) {
        log.warn("Invalid JSON at {}: {}", pathFrom(e), e.getMessage());

        var body = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_JSON",
                "Malformed JSON request",
                pathFrom(e),
                now()
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException e) {

        String rootMessage = e.getMostSpecificCause().getMessage();

        log.warn("Data integrity violation at {}: {}", pathFrom(e), rootMessage);

        String errorCode;
        String message;

        if (rootMessage != null && rootMessage.contains("Duplicate entry")) {
            errorCode = "UNIQUE_CONSTRAINT_VIOLATION";
            message = "A resource with the same unique field already exists.";
        } else {
            errorCode = "FK_CONSTRAINT_VIOLATION";
            message = "Resource cannot be deleted because it is referenced by other entities.";
        }

        var body = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                errorCode,
                message,
                pathFrom(e),
                now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception e) {

        if (profileProperties.debug()) {
            log.error("Unexpected error occurred", e);
        } else {
            log.error("Unexpected error occurred: {}", e.getMessage());
        }

        String message = profileProperties.debug()
                ? e.getMessage()
                : "An unexpected error occurred";

        var body = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                message,
                pathFrom(e),
                now()
        );

        return ResponseEntity.internalServerError().body(body);
    }

    private String pathFrom(Exception e) {
        return RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes attrs
                ? attrs.getRequest().getRequestURI()
                : "";
    }

    private Instant now() {
        return Instant.now();
    }

}
