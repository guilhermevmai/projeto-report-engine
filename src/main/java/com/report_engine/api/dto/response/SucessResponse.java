package com.report_engine.api.dto.response;

public record SucessResponse<T>(String status, String message, T data)
        implements ApiResponse {
    public SucessResponse(String message, T data) {
        this("SUCCESS", message, data);
    }
}
