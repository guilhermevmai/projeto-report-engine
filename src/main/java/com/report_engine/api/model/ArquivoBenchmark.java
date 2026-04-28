package com.report_engine.api.model;

import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ArquivoBenchmark {


    private String strategy;

    private ReadFilesStrategies enumStrategy;

    @Setup
    public void setup() {
        this.enumStrategy = ReadFilesStrategies.valueOf(strategy);
    }

    @Benchmark
    public long executarBenchmark() {

        switch (enumStrategy) {
            case STREAMING :
                return 1L;
            case ALL_LINES:
                return 2L;
            default:
                return 0L;
        }
    }
}
