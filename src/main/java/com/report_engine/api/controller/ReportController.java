package com.report_engine.api.controller;

import com.report_engine.api.dto.response.api_responses.ApiResponse;
import com.report_engine.api.factory.ResponseFactory;
import com.report_engine.api.model.enums.ReadFilesStrategies;
import com.report_engine.api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/hello")
    public ResponseEntity<ApiResponse> helloWorld() {
        String helloWorld = "A simple Hello world.";
        return ResponseEntity.ok(ResponseFactory.success(helloWorld));
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadArquivoCorreto(@RequestBody MultipartFile file, @
            RequestParam("strategy") ReadFilesStrategies chosedStrategy) throws Exception {
        String message = String.format("File processed using the strategy: %s", chosedStrategy.toString());

        return ResponseEntity.ok(ResponseFactory.success(chosedStrategy.processFile(reportService, file), message));
    }
}
