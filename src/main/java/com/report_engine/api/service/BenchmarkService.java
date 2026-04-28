package com.report_engine.api.service;

import com.report_engine.api.model.ArquivoBenchmark;
import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BenchmarkService {

    @Async
    public CompletableFuture<String> rodarBenchmark(ReadFilesStrategies chosedStrategy) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ArquivoBenchmark.class.getSimpleName())
                .param("strategy", String.valueOf(chosedStrategy))
                .forks(1)
                .build();

        new Runner(opt).run();

        return CompletableFuture.completedFuture("Benchmark concluído para: " + chosedStrategy);
    }
}
