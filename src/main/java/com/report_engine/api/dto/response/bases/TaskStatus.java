package com.report_engine.api.dto.response.bases;

import com.report_engine.api.model.enums.TaskState;

import java.time.Instant;

public record TaskStatus<T>(
        String taskId,
        TaskState state,
        Instant createdAt,
        Instant finishedAt,
        T response
) {
}
