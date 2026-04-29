package com.report_engine.api.controller;

import com.report_engine.api.model.UsersReport;
import com.report_engine.api.model.enums.UserStatus;
import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import com.report_engine.api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
    public String uploadArquivoCorreto(@RequestParam("file") MultipartFile file, @
            RequestParam("strategy") ReadFilesStrategies chosedStrategy) throws Exception {
        return ResponseEntity.ok(reportService.processarArquivo(file, chosedStrategy)).getBody();
    }
}
