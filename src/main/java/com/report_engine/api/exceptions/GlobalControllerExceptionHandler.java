package com.report_engine.api.exceptions;

import com.report_engine.api.dto.response.api_responses.ApiResponse;
import com.report_engine.api.factory.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse> handleGlobalError(Throwable e) {
        String message = "An error has occurred.";
        log.error(message, e);

        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiResponse> handleGenericException(GenericException e) {
        String message = Objects.requireNonNullElse(e.getMessage(), "An error has occurred.");
        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ApiResponse> handleConversionFailedException(ConversionFailedException e) {
        return new ResponseEntity<>(ResponseFactory.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = String.format("Invalid value '%s' for parameter '%s'", e.getValue(), e.getName());

        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> handleMissingParam(MissingServletRequestParameterException e) {
        String message = String.format("The parameter '%s' is required", e.getParameterName());
        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
