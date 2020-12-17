package lab3Tests;

import lab3.Sorts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Array;
import java.util.Arrays;

public class Tests {

    private int[] res1 = new int[]{-2,1,3,4,5,6,7,8};
    private int[] test1 = new int[]{-2, 1, 8,4, 5, 6, 3, 7};
    private int[] res2 = new int[]{1,1,1,1,1,1,1};
    private int[] test2 = new int[]{1,1,1,1,1,1,1};
    private int[] res3 = new int[0];


    @Test
    public void testBubble() {
        int[] copied_arr1 = Arrays.copyOf(test1, test1.length);
        int[] copied_arr2 = Arrays.copyOf(test2, test2.length);
        int[] copied_arr3 = new int[0];

        Sorts.bubbleSort(copied_arr1);
        Sorts.bubbleSort(copied_arr2);
        Sorts.bubbleSort(copied_arr3);
        Assertions.assertArrayEquals(res1, copied_arr1);
        Assertions.assertArrayEquals(res2, copied_arr2);
        Assertions.assertArrayEquals(res3, copied_arr3);
    }

    @Test
    public void testMerge() {
        int[] copied_arr1 = Arrays.copyOf(test1, test1.length);
        int[] copied_arr2 = Arrays.copyOf(test2, test2.length);
        int[] copied_arr3 = new int[0];

        Sorts.mergeSort(copied_arr1, copied_arr1.length);
        Sorts.mergeSort(copied_arr2, copied_arr2.length);
        Sorts.mergeSort(copied_arr3, copied_arr3.length);
        Assertions.assertArrayEquals(res1, copied_arr1);
        Assertions.assertArrayEquals(res2, copied_arr2);
        Assertions.assertArrayEquals(res3, copied_arr3);
    }

    @Test
    public void testQuick() {
        int[] copied_arr1 = Arrays.copyOf(test1, test1.length);
        int[] copied_arr2 = Arrays.copyOf(test2, test2.length);
        int[] copied_arr3 = new int[0];

        Sorts.quickSort(copied_arr1, 0 , copied_arr1.length-1);
        Sorts.quickSort(copied_arr2, 0, copied_arr2.length-1);
        Sorts.quickSort(copied_arr3, 0 , copied_arr3.length-1);
        Assertions.assertArrayEquals(res1, copied_arr1);
        Assertions.assertArrayEquals(res2, copied_arr2);
        Assertions.assertArrayEquals(res3, copied_arr3);
        
    }
}
