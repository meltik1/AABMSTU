package lab7;

import lab5.LinearConveyor;
import lab5.ParallelConveyor;
import lab5.Phone;
import lab5.TimeMeasurementLab5;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class TimeMeasurementLab7 {

    DictionarySearch search;

    public static void main(String[] args) throws IOException, RunnerException {
        Options options = new OptionsBuilder().include(TimeMeasurementLab7.class.getSimpleName()).
                forks(1).resultFormat(ResultFormatType.TEXT).result("DictionaryComparison.txt").build();
        new Runner(options).run();

    }

    @Setup(Level.Trial)
    public void init() throws SQLException {
     search = new DictionarySearch();
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void MeasureLinear() throws SQLException {
        for (int i = 0; i < search.getListofSortedCustomers().size(); i++) {
            if (search.linealSearch(search.getListofSortedCustomers().get(i).getKey()) == null) {
                System.out.println("Not found");
            }
        }

    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void MeasureSegmentSearch() throws InterruptedException, SQLException {
        for (int i = 0; i < search.getListofSortedCustomers().size(); i++) {
            if (search.segment_search(search.getListofSortedCustomers().get(i).getKey()) == null) {
                System.out.println("Not found");
            }
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void MeasureSegmentBubbleSearch() throws InterruptedException, SQLException {
        for (int i = 0; i < search.getListofSortedCustomers().size(); i++) {
            if (search.searchBySegmentSortedBubble(search.getListofSortedCustomers().get(i).getKey()) == null) {
                System.out.println("Not found");
            }
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void MeasureSegmentquickSearch() throws InterruptedException, SQLException {
        for (int i = 0; i < search.getListofSortedCustomers().size(); i++) {
            if (search.searchBySegmentSortedQuick(search.getListofSortedCustomers().get(i).getKey()) == null) {
                System.out.println("Not found");
            }
        }

    }
}
