package com.report_engine.api.dto.response.bases;

import java.util.List;

public interface BaseResponseContracts {
    default boolean isWarning() { return false; }
    default List<String> warningReasons() { return List.of(); }
}
