package com.report_engine.api.dto.response;

public record ErrorResponse(String status, String message, int errorCode) implements ApiResponse {
}
