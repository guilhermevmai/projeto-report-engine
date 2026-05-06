package com.report_engine.api.controller;

import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import com.report_engine.api.service.BenchmarkService;
import com.report_engine.api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/benchmarks")
@RequiredArgsConstructor
public class BenchmarkController {

    private final BenchmarkService benchmarkService;

    @PostMapping("/run-upload")
    public ResponseEntity<String> uploadArquivoCorreto( @RequestParam("strategy") ReadFilesStrategies chosedStrategy) {
        benchmarkService.rodarBenchmark(chosedStrategy);

        return ResponseEntity.accepted()
                .body("Benchmark iniciado em background. Verifique o console e o arquivo benchmark-results.json ao finalizar.");
    }
}