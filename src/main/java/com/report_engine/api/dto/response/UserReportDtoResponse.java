package com.report_engine.api.dto.response;

public record UserReportDtoResponse(
        Long totalProcessed,
        Long successCount,
        Long errorCount) {
}
