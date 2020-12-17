package lab3;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class TimeTests {
    

    @State(Scope.Benchmark)
    public static class Arr  {
        @Param({"1000", "2000", "3000", "4000", "5000"})
        public int params;

        public int[] arrRandom;
        public int[] arrSorted;
        public int[] arrReversed;
        @Setup(Level.Trial)
        public void setUp() {
            arrRandom = generate_arr(params);
            arrSorted = new int[params];
            arrReversed = new int[params];
            for (int i = 0; i < params; i++) {
                arrSorted[i] = i;
            }
            for (int i = 0; i < params; i++) {
                arrReversed[i] = params-i;
            }
        }
    }        
    

    public static int[] generate_arr(int n) {
        int[] arr = new int[n];
        Random random= new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Options options = new OptionsBuilder().include(TimeTests.class.getSimpleName()).
                forks(1).resultFormat(ResultFormatType.TEXT).result("SortComparison.txt").build();
        new Runner(options).run();

    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureBubbleRandom(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrRandom, arr.arrRandom.length);
        Sorts.bubbleSort(res);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureQuickRandom(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrRandom, arr.arrRandom.length);
        Sorts.quickSort(res, 0, res.length-1);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureMergeRandom(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrRandom, arr.arrRandom.length);
        Sorts.mergeSort(res, res.length);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureBubbleWorst(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrRandom, arr.arrRandom.length);
        Sorts.bubbleSort(res);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureQuickWorst(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrSorted, arr.arrSorted.length);
        Sorts.quickSort(res, 0, res.length-1);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureMergeWorst(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrSorted, arr.arrSorted.length);
        Sorts.mergeSort(res, res.length);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureBubbleBest(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrReversed, arr.arrReversed.length);
        Sorts.bubbleSort(res);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureQuickBest(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrReversed, arr.arrReversed.length);
        Sorts.quickSort(res, 0, res.length-1);
        return res;
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[] measureMergeBest(Arr arr) {
        int[] res = Arrays.copyOf(arr.arrReversed, arr.arrReversed.length);
        Sorts.mergeSort(res, res.length);
        return res;
    }


}
