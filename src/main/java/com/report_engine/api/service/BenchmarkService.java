package com.report_engine.api.service;

import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import lombok.RequiredArgsConstructor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class BenchmarkService {

    private final ReportService reportService;

    //@Async
    public CompletableFuture<String> rodarBenchmark(ReadFilesStrategies chosedStrategy) {
        try {
            Options opt = new OptionsBuilder()
                    .include("com.report_engine.api.service.ArquivoBenchmark")
                    .jvmArgs("-Djava.class.path=" + System.getProperty("java.class.path"))
                    .param("strategy", String.valueOf(chosedStrategy))
                    .forks(1)
                    .warmupIterations(2)
                    .measurementIterations(3)
                    .resultFormat(ResultFormatType.JSON)
                    .result("result-jhm.json")
                    .build();


            new Runner(opt).run();
            return CompletableFuture.completedFuture("Benchmark concluído para: " + chosedStrategy);
        } catch (RunnerException e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
