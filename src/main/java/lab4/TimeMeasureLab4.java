package lab4;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class TimeMeasureLab4 {
    @Param({"1000"})
    public int params;
    public  int[][] generate_matrix(int n, int m) {
        int[][] new_matrix = new int[n][m];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                new_matrix[i][j] = random.nextInt(1000);
            }
        }
        return new_matrix;
    }


    public static void main(String[] args) throws IOException, RunnerException {
        Options options = new OptionsBuilder().include(TimeMeasureLab4.class.getSimpleName()).
                forks(1).resultFormat(ResultFormatType.TEXT).result("MatrixComparison.txt").build();
        new Runner(options).run();

    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureStandart() {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return Matrix.MultVinOpt(mart1, matr2);

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel1() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 1);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel2() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 2);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel4() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 4);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel5() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 5);
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel16() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 16);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel8() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 8);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel32() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 32);
    }



    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public int[][] MeasureParallel64() throws InterruptedException {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return MatrixParallel.MultVinOpt(mart1, matr2, 64);
    }


}
