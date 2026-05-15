package com.report_engine.api.factory;

import com.report_engine.api.dto.response.api_responses.ApiResponse;
import com.report_engine.api.dto.response.api_responses.ErrorResponse;
import com.report_engine.api.dto.response.api_responses.SucessResponse;
import com.report_engine.api.dto.response.api_responses.WarningResponse;
import com.report_engine.api.dto.response.bases.BaseResponseContracts;

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

    public static ErrorResponse error(String message, int errorCode) {
        return new ErrorResponse("ERROR", message, errorCode);
    }
}
