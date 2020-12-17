package lab4;

import java.util.Random;

public class Main {
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

}
