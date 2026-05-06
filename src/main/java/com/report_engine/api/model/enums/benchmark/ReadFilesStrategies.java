package com.report_engine.api.model.enums.benchmark;

import com.report_engine.api.dto.response.UserReportDtoResponse;
import com.report_engine.api.service.ReportService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public enum ReadFilesStrategies {
    ALL_LINES {
        @Override
        public UserReportDtoResponse processFile(ReportService service, MultipartFile file) throws IOException {
            return service.readWithAllLines(file);
        }
    },
    STREAM {
        @Override
        public UserReportDtoResponse processFile(ReportService service, MultipartFile file) throws IOException {
            return service.readWithBufferedReader(file);
        }
    };

    public abstract UserReportDtoResponse processFile(ReportService reportService, MultipartFile file) throws IOException;
}
