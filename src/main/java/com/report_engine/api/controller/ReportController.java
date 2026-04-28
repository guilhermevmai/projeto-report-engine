package com.report_engine.api.controller;

import com.report_engine.api.model.UsersReport;
import com.report_engine.api.model.enums.UserStatus;
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

    @PostMapping("/upload-correto")
    public String uploadArquivoCorreto(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(reportService.readWithBufferedReader(file)).getBody();
    }


    @PostMapping("/upload-errado")
    public String uploadArquivoErrado(@RequestParam("file") MultipartFile file) throws IOException {

        return ResponseEntity.ok(reportService.readWithAllLines(file)).getBody();
    }
}
