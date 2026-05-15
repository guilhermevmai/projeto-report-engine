package com.report_engine.api.exceptions;

public class GenericException extends RuntimeException {
    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
    public GenericException(Throwable cause) { super(cause); }
}
