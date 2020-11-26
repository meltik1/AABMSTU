package lab3;

import java.util.Random;

public class Main {
    public static int[] generate_arr(int n) {
        int[] arr = new int[n];
        Random random= new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr =  generate_arr(100000);
        System.out.println(TimeTests.MeasureMergeSort(arr));
        System.out.println(TimeTests.MeasureQucikSort(arr));
        System.out.println(TimeTests.MeasureBubbleSort(arr));


    }
}
