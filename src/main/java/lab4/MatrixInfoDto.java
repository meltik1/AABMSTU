package lab4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatrixInfoDto {
    private int[][] res, left, right;
    private int[] rowvector, colvector;
    int n1, m1,n2,m2;
}
