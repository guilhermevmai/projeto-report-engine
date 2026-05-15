package com.report_engine.api.exceptions;

import com.report_engine.api.dto.response.api_responses.ErrorResponse;
import com.report_engine.api.factory.ResponseFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleGlobalError (Throwable e) {
        String message = "An error has occured.";
        e.printStackTrace();

        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericExcpetion (RuntimeException e) {
        String message = Optional.ofNullable(e.getMessage()).orElse("An error has occured.");
        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorResponse> handleConflict (RuntimeException e) {
        return new ResponseEntity<>(ResponseFactory.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = String.format("None strategies found by the following name: %s", e.getValue().toString().toUpperCase());

        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(MissingServletRequestParameterException e) {
        String message = String.format("the parameter '%s' is required", e.getParameterName());
        return new ResponseEntity<>(ResponseFactory.error(message, HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }
}
