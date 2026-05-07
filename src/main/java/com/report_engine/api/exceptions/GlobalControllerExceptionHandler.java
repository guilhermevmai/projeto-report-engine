package com.report_engine.api.exceptions;

import com.report_engine.api.dto.response.ErrorResponse;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleGlobalError (Throwable e) {
        String message = "An error has occured.";
        e.printStackTrace();
        ErrorResponse response = new ErrorResponse(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericExcpetion (RuntimeException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorResponse> handleConflict (RuntimeException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = String.format("None strategies found by the following name: %s", e.getValue());
        ErrorResponse response = new ErrorResponse(message, HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(MissingServletRequestParameterException e) {
        String message = String.format("the parameter '%s' is required", e.getParameterName());
        ErrorResponse response = new ErrorResponse(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response,
                HttpStatus.BAD_REQUEST);
    }
}
