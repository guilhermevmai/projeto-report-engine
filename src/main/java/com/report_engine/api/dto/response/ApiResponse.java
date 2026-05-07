package com.report_engine.api.dto.response;

public sealed interface ApiResponse permits ErrorResponse, SucessResponse {
    String status();
    String message();
}
