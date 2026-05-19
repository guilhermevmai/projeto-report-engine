package com.report_engine.api.dto.response.api_responses;

public sealed interface ApiResponse permits
        SucessResponse,
        AsyncResponse,
        ErrorResponse,
        WarningResponse {
    String status();
    String message();
}
