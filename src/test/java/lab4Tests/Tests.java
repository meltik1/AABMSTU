package lab4Tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lab4.Matrix;
import lab4.MatrixParallel;

public class Tests {

    int[][] A = new int[2][2];
    int[][] B = new int[2][2];
    int[][] C = new int[0][2];
    int[][] D = new int[2][0];
    int[][] E = new int[5][5];



    /*
    Тесты оптимизированного умножения матриц
     */
    @Test
    public void  testVinOpt() {
        for (int i = 0, counter = 1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter++;
            }
        }
        int[][] res = {{7,10}, {15,22}};
        Assertions.assertArrayEquals(res, Matrix.MultVinOpt(A, B));

        for (int i = 0, counter = -1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter--;
            }
        }
        Assertions.assertArrayEquals(res, Matrix.MultVinOpt(A, B));
        Assertions.assertArrayEquals(null, Matrix.MultVinOpt(A, C));
        Assertions.assertArrayEquals(null, Matrix.MultVinOpt(A,D));
        Assertions.assertArrayEquals(null, Matrix.MultVinOpt(A, E));
    }

    @Test
    public void TestVinParallel() throws InterruptedException {
        for (int i = 0, counter = 1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter++;
            }
        }
        int[][] res = {{7,10}, {15,22}};
        Assertions.assertArrayEquals(res, MatrixParallel.MultVinOpt(A, B, 2));

        for (int i = 0, counter = -1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter--;
            }
        }
        Assertions.assertArrayEquals(res, MatrixParallel.MultVinOpt(A, B,2));
        Assertions.assertArrayEquals(null, MatrixParallel.MultVinOpt(A, C, 2));
        Assertions.assertArrayEquals(null, MatrixParallel.MultVinOpt(A,D, 2));
        Assertions.assertArrayEquals(null, MatrixParallel.MultVinOpt(A, E, 2));
    }
}


