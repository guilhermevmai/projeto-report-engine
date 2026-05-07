package com.report_engine.api.model.enums;

import com.report_engine.api.dto.response.ApiResponse;
import com.report_engine.api.dto.response.SucessResponse;
import com.report_engine.api.dto.response.UserReportDtoResponse;
import com.report_engine.api.service.ReportService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public enum ReadFilesStrategies {
    ALL_LINES {
        @Override
        public ApiResponse processFile(ReportService service, MultipartFile file) throws IOException {
            String message = "Arquivo processado lendo todas as linhas.";
            return new SucessResponse<UserReportDtoResponse>(message, service.readWithAllLines(file));
        }
    },
    STREAM {
        @Override
        public ApiResponse processFile(ReportService service, MultipartFile file) throws IOException {
            String message = "Arquivo processado com bufferedReader.";
            return new SucessResponse<UserReportDtoResponse>(message,service.readWithBufferedReader(file));
        }
    };

    public abstract ApiResponse processFile(ReportService reportService, MultipartFile file) throws IOException;
}
