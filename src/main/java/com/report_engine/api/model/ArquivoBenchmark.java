package com.report_engine.api.model;

import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ArquivoBenchmark {

    @Param({"ALL_LINES, STREAM"})
    private String strategy;

    private ReadFilesStrategies enumStrategy;

    @Setup
    public void setup() {
        this.enumStrategy = ReadFilesStrategies.valueOf(strategy);
    }

    @Benchmark
    public long executarBenchmark() {

        switch (enumStrategy) {
            case STREAM :
                return 1L;
            case ALL_LINES:
                return 2L;
            default:
                return 0L;
        }
    }
}
