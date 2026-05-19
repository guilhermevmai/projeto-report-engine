package com.report_engine.api.factory;

import com.report_engine.api.dto.response.api_responses.*;
import com.report_engine.api.dto.response.bases.BaseResponseContracts;

import java.time.Instant;

public class ResponseFactory {

    public static <T>SucessResponse<T> success(T data) {
        return new SucessResponse<>("SUCCESS", "Operation completed with success.", data);
    }

    public static <T>ApiResponse success(BaseResponseContracts data, String customMessage) {

        if (data.isWarning()) {
            return new WarningResponse<>("WARNING", customMessage, data, data.warningReasons());
        }

        return new SucessResponse<>("SUCCESS", customMessage, data);
    }

    public static AsyncResponse async(BaseResponseContracts data,
                                      String customMessage, String taskId) {
        return new AsyncResponse("PROCESSING", customMessage, taskId, Instant.now());
    }

    public static ErrorResponse error(String message, int errorCode) {
        return new ErrorResponse("ERROR", message, errorCode);
    }
}
