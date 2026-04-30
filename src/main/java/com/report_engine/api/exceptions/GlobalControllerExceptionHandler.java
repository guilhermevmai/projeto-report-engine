package com.report_engine.api.exceptions;

import com.report_engine.api.dto.response.ExceptionResponseDto;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> handleConflict (RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponseDto> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = String.format("None strategies found by the following name: %s", e.getValue());
        ExceptionResponseDto response = new ExceptionResponseDto(
                HttpStatus.BAD_REQUEST.getReasonPhrase(), message);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponseDto> handleMissingParam(MissingServletRequestParameterException e) {
        String message = String.format("the parameter '%s' is required", e.getParameterName());
        ExceptionResponseDto response = new ExceptionResponseDto(
                HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return new ResponseEntity<>(response,
                HttpStatus.BAD_REQUEST);
    }
}
