package com.report_engine.api.factory;

import com.report_engine.api.dto.response.ErrorResponse;
import com.report_engine.api.dto.response.SucessResponse;

public class ResponseFactory {

    public static <T>SucessResponse<T> success(T data) {
        return new SucessResponse<>("SUCCESS", "Operation completed with success.", data);
    }

    public static <T>SucessResponse<T> success(T data, String customMessage) {
        return new SucessResponse<>("SUCCESS", customMessage, data);
    }

    public static ErrorResponse error(String message, int errorCode) {
        return new ErrorResponse("ERROR", message, errorCode);
    }
}
