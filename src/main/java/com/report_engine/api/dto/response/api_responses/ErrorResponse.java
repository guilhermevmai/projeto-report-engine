package com.report_engine.api.dto.response.api_responses;

public record ErrorResponse(String status, String message, int errorCode) implements ApiResponse {
}
