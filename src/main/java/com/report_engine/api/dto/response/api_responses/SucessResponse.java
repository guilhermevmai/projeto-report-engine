package com.report_engine.api.dto.response.api_responses;

public record SucessResponse<T>(String status, String message, T data) implements ApiResponse {
}
