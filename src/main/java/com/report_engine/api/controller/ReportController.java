package com.report_engine.api.controller;

import com.report_engine.api.dto.response.UserReportDtoResponse;
import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
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
    public String helloWorld() {
        String mensagem = "Hello world";
        return ResponseEntity.ok(mensagem).getBody();
    }

    @PostMapping("/upload")
    public ResponseEntity<UserReportDtoResponse> uploadArquivoCorreto(@RequestParam("file") MultipartFile file, @
            RequestParam("strategy") ReadFilesStrategies chosedStrategy) throws Exception {
        return ResponseEntity.ok(chosedStrategy.processFile(reportService, file));
    }
}
