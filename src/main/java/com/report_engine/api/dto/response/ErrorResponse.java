package com.report_engine.api.dto.response;

public record ErrorResponse(String status, String message, int errorCode) implements ApiResponse {
    public ErrorResponse(String message, int errorCode) {
        this("ERROR", message, errorCode);
    }
}
