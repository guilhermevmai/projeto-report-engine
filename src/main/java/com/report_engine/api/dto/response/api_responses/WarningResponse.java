package com.report_engine.api.dto.response.api_responses;

import java.util.List;

public record WarningResponse<T>(String status, String message, T data, List<String> reasons) implements ApiResponse {
}
