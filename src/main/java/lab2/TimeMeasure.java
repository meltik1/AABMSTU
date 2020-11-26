package lab2;

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
public class TimeMeasure {
    @Param({"100", "200", "300" , "400", "500", "600", "700", "800", "900" })
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
        Options options = new OptionsBuilder().include(TimeMeasure.class.getSimpleName()).
                forks(1).resultFormat(ResultFormatType.LATEX).result("qwe.tex").build();
        new Runner(options).run();

    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 100)
    public int[][] MeasureStandart() {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return Matrix.MultStand(mart1, matr2);

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 100)
    public int[][] MeasurePomgrade() {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return Matrix.MultVin(mart1, matr2);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 100)
    public int[][] MeasurePomgradeOpt() {
        int[][] mart1 = generate_matrix(params, params);
        int[][] matr2 = generate_matrix(params, params);
        return Matrix.MultVinOpt(mart1, matr2);
    }

}
