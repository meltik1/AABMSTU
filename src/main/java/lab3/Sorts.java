package lab3;

public class Sorts {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j <arr.length - i-1; j ++) {
                if (arr[j] > arr[j+1]) {
                    int element = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = element;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot-1);
            quickSort(arr, pivot+1, end);
        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[(begin + end) / 2];
        int i = begin - 1, j = end + 1;

        while (true)
        {
            do
            {
                i++;
            }
            while (arr[i] < pivot);

            do
            {
                j--;
            }
            while (arr[j] > pivot);

            if (i >= j)
                return j;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            //Swap(ref arr[i], ref arr[j]);
        }
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

}
