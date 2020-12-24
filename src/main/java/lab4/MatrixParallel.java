package lab4;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

public class MatrixParallel {
    public static class ParallelCounting implements Runnable {
        private int[][] res, matr1, matr2;
        private int[] rowVector, colVector;
        private int n1, m1, m2, start;

        public  ParallelCounting(MatrixInfoDto dto) {
            res = dto.getRes();
            matr1 = dto.getLeft();
            matr2 = dto.getRight();
            rowVector = dto.getRowvector();
            colVector = dto.getColvector();
            n1 = dto.getN1();
            start = dto.getN2();
            m1 = dto.getM1();
            m2 = dto.getM2();
        }

        @Override
        public void run() {
            for (int i = start; i < n1; i++)
            {
                for (int j = 0; j < m2; j++)
                {
                    int buff = -(rowVector[i] + colVector[j]);
                    for (int k = 0; k < (m1 - m1%2); k += 2)
                    {
                        buff += (matr1[i][k + 1] + matr2[k][j]) * (matr1[i][k] + matr2[k + 1][j]);
                    }
                    res[i][j] = buff;
                }
            }
        }
    }

    public static int[][] MultVinOpt(int[][] matr1, int[][] matr2, int thread_number) throws InterruptedException {
        int n1 = matr1.length;
        int n2 = matr2.length;

        if (n1 == 0 || n2 == 0)
            return null;

        int m1 = matr1[0].length;
        int m2 = matr2[0].length;

        if (m1 != n2 || m1 == 0 || m2 == 0)
            return null;

        int[] rowVector = new int[n1];
        int[] colVector = new int[m2];

        int[][] res = new int[n1][];
        for (int i = 0; i < n1; i++)
            res[i] = new int[m2];

        int m1Mod2 = m1 % 2;
        int n2Mod2 = n2 % 2;

        for (int i = 0; i < n1; i++)
        {
            for (int j = 0; j < (m1 - m1Mod2); j += 2)
            {
                rowVector[i] += matr1[i][j] * matr1[i][j + 1];
            }
        }

        for (int i = 0; i < m2; i++)
        {
            for (int j = 0; j < (n2 - n2Mod2); j += 2)
            {
                colVector[i] += matr2[j][i] * matr2[j + 1][i];
            }
        }

        Thread[] threads = new Thread[thread_number];
        int row_start = 0;
        int rowsPerThred = n1/thread_number;
        for (int i  = 0; i < thread_number; i++) {
            int row_end = row_start + rowsPerThred;
            if (i == thread_number - 1)
                   row_end = n1;

            MatrixInfoDto dto = new MatrixInfoDto();
            dto.setRes(res);
            dto.setLeft(matr1);
            dto.setRight(matr2);
            dto.setRowvector(rowVector);
            dto.setColvector(colVector);
            dto.setM1(m1);
            dto.setM2(m2);
            dto.setN2(row_start);
            dto.setN1(row_end);

            MatrixParallel.ParallelCounting e = new MatrixParallel.ParallelCounting(dto);
            threads[i] = new Thread(e);

            threads[i].start();

            row_start = row_end;
        }


        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }



        if (m1Mod2 == 1)
        {
            int m1Min_1 = m1 - 1;
            for (int i = 0; i < n1; i++)
            {
                for (int j = 0; j < m2; j++)
                {
                    res[i][j] += matr1[i][m1Min_1] * matr2[m1Min_1][j];
                }
            }
        }

        return res;
    }
}
