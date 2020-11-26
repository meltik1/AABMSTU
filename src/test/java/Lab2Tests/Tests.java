package Lab2Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lab2.Matrix;


public class Tests {

    int[][] A = new int[2][2];
    int[][] B = new int[2][2];
    int[][] C = new int[0][2];
    int[][] D = new int[2][0];
    int[][] E = new int[5][5];



    /*
    Тесты стандартного умножения матриц
     */
    @Test
    public void  testStandart() {
        for (int i = 0, counter = 1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter++;
            }
        }
        int[][] res = {{7,10}, {15,22}};
        Assertions.assertArrayEquals(res, Matrix.MultStand(A, B));

        for (int i = 0, counter = -1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter--;
            }
        }

        Assertions.assertArrayEquals(res, Matrix.MultStand(A, B));
        Assertions.assertArrayEquals(null, Matrix.MultStand(A, C));
        Assertions.assertArrayEquals(null, Matrix.MultStand(A,D));
        Assertions.assertArrayEquals(null, Matrix.MultStand(A, E));
    }

    /*
        Тесты  умножения виноградом
     */
    @Test
    public void  testVin() {
        for (int i = 0, counter = 1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter++;
            }
        }
        int[][] res = {{7,10}, {15,22}};
        Assertions.assertArrayEquals(res, Matrix.MultVin(A, B));

        for (int i = 0, counter = -1; i < 2; i++) {
            for (int j = 0; j < 2;  j++) {
                A[i][j] = counter;
                B[i][j] = counter--;
            }
        }
        Assertions.assertArrayEquals(res, Matrix.MultVin(A, B));
        Assertions.assertArrayEquals(null, Matrix.MultVin(A, C));
        Assertions.assertArrayEquals(null, Matrix.MultVin(A,D));
        Assertions.assertArrayEquals(null, Matrix.MultVin(A, E));
    }

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

}
