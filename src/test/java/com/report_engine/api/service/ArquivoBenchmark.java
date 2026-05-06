package com.report_engine.api.service;

import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import org.openjdk.jmh.annotations.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ArquivoBenchmark {


    @Param({"ALL_LINES, STREAM"})
    private String strategy;

    private ReportService reportService;
    private MultipartFile realFile;

    private ReadFilesStrategies enumStrategy;

    @Setup
    public void setup() throws IOException {
        this.reportService = new ReportService();
        this.enumStrategy = ReadFilesStrategies.valueOf(strategy);

        File file = new File("src/main/resources/data/teste_grande.csv");
        byte[] content = Files.readAllBytes(file.toPath());

        this.realFile = new MockMultipartFile("teste_grande.csv", "teste_grande.csv", "text/csv", content);
    }

    @Benchmark
    public void executarBenchmark() throws IOException{
        enumStrategy.processFile(reportService, realFile);
    }
}
