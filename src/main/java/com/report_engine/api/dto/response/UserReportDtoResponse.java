package com.report_engine.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.report_engine.api.dto.response.bases.BaseResponseContracts;

import java.util.List;

public record UserReportDtoResponse(
        Long totalProcessed,
        Long successCount,
        Long errorCount,

        @JsonIgnore
        boolean warning,

        @JsonIgnore
        List<String> reasons) implements BaseResponseContracts {

    @Override
    public boolean isWarning() { return warning; }

    @Override
    public List<String> warningReasons() { return reasons; }
}
