package com.report_engine.api.dto.response.api_responses;

import java.time.Instant;

public record AsyncResponse(String status, String message, String taskId, Instant estimationTime) implements ApiResponse {
}
