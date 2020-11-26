package lab3;

import java.util.Arrays;

public class TimeTests {

    public static  Long Mill = 1000000L;
    public static int  counter = 10;

    public static Long MeasureBubbleSort(int arr[]) {
        Long start = System.nanoTime();
        for (int i = 0; i < counter; i++) {
            int[] new_arr = Arrays.copyOf(arr, arr.length);
            Sorts.bubbleSort(new_arr);
        }
        Long end = System.nanoTime();
        return (end - start)/Mill;
    }

    public static Long MeasureMergeSort(int arr[]) {
        Long start = System.nanoTime();
        for (int i = 0; i < counter; i++) {
            int[] new_arr = Arrays.copyOf(arr, arr.length);
            Sorts.mergeSort(new_arr, new_arr.length);
        }
        Long end = System.nanoTime();
        return (end - start)/Mill;
    }

    public static Long MeasureQucikSort(int arr[]) {
        Long start = System.nanoTime();
        for (int i = 0; i < counter; i++) {
            int[] new_arr = Arrays.copyOf(arr, arr.length);
            Sorts.quickSort(new_arr, 0, new_arr.length-1);
        }
        Long end = System.nanoTime();
        return (end - start)/Mill;
    }
}
