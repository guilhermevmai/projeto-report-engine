package com.report_engine.api.exceptions;

public class GenericException extends RuntimeException {
    private static final String GENERIC_MESSAGE = "An error has occured.";
    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
    public GenericException(Throwable cause) { super(GENERIC_MESSAGE, cause); }
}
