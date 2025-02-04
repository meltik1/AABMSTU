package lab2;

public class Matrix {
    public static int[][] MultStand(int[][] mart1, int[][] matr2)
    {
        int n1 = mart1.length;
        int n2 = matr2.length;

        if (n1  == 0 || n2 == 0)
            return null;

        int m1 = mart1[0].length;
        int m2 = matr2[0].length;

        if (m1 != n2 || m1 == 0|| m2 == 0)
            return null;

        int[][] res = new int[n1][];
        for (int i = 0; i < n1; i++)
            res[i] = new int[m2];

        for (int i = 0; i < n1; i++)
            for (int j = 0; j < m2; j++)
                for (int k = 0; k < m1; k++)
                    res[i][j] += mart1[i][k] * matr2[k][j];

        return res;
    }

    public static int[][] MultVin(int[][] matr1, int[][] matr2)
    {
        int n1 = matr1.length;
        int n2 = matr2.length;


        if (n1 == 0 || n2 == 0 )
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

        for (int i = 0; i < n1; i++)
        {
            for (int j = 0; j < m1 / 2; j++)
            {
                rowVector[i] = rowVector[i] + matr1[i][j * 2] * matr1[i][j * 2 + 1];
            }
        }

        for (int i = 0; i < m2; i++)
        {
            for (int j = 0; j < n2 / 2; j++)
            {
                colVector[i] = colVector[i] + matr2[j * 2][i] * matr2[j * 2 + 1][i];
            }
        }

        for (int i = 0; i < n1; i++)
        {
            for (int j = 0; j < m2; j++)
            {
                res[i][j] = -rowVector[i] - colVector[j];
                for (int k = 0; k < m1 / 2; k++)
                {
                    res[i][j] = res[i][j] + (matr1[i][2 * k + 1] + matr2[2 * k][j]) * (matr1[i][2 * k] + matr2[2 * k + 1][j]);
                }
            }
        }

        if (m1 % 2 == 1)
        {
            for (int i = 0; i < n1; i++)
            {
                for (int j = 0; j < m2; j++)
                {
                    res[i][j] = res[i][j] + matr1[i][m1 - 1] * matr2[m1 - 1][j];
                }
            }
        }

        return res;
    }

    public static int[][] MultVinOpt(int[][] matr1, int[][] matr2)
    {
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

        for (int i = 0; i < n1; i++)
        {
            for (int j = 0; j < m2; j++)
            {
                int buff = -(rowVector[i] + colVector[j]);
                for (int k = 0; k < (m1 - m1Mod2); k += 2)
                {
                    buff += (matr1[i][k + 1] + matr2[k][j]) * (matr1[i][k] + matr2[k + 1][j]);
                }
                res[i][j] = buff;
            }
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
