package lab5;

import lab4.TimeMeasureLab4;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class TimeMeasurementLab5 {

    @Param({"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000"})
    public int params;

    public static void main(String[] args) throws IOException, RunnerException {
        Options options = new OptionsBuilder().include(TimeMeasurementLab5.class.getSimpleName()).
                forks(1).resultFormat(ResultFormatType.TEXT).result("ConveyorComparison.txt").build();
        new Runner(options).run();

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public List<Phone> MeasureStandartCoveyor() {
        List<Phone> phones = new ArrayList<Phone>();
        for (int i = 0; i < params; i++) {
            phones.add(new Phone());
        }

        LinearConveyor.constuctor(phones);
        return phones;
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public List<Phone> MeasureParallelConveyor() throws InterruptedException {
        List<Phone> phones = new ArrayList<Phone>();
        for (int i = 0; i < params; i++) {
            phones.add(new Phone());
        }

        ParallelConveyor parallelConveyor = new ParallelConveyor();
        parallelConveyor.construct(phones);
        return phones;
    }

}
