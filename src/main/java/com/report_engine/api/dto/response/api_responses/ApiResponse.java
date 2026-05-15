package com.report_engine.api.dto.response.api_responses;

public sealed interface ApiResponse permits
        ErrorResponse,
        SucessResponse,
        WarningResponse {
    String status();
    String message();
}
