import java.util.Random;


public class Sorter {
    public void basicSort(int[] arr) {
        for(int i = 1; i < arr.length; i++){
            int j = i - 1;
            int temp = arr[i];
            while(j >=  0 && arr[j] > temp){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public void advancedSort(int[] x) {
        sort(x, 0, x.length - 1);
    }

    private void sort(int[] x, int start, int end) {
        if (start < end) {
            int middle = start + (end - start) / 2;

            sort(x, start, middle);
            sort(x, middle + 1, end);

            merge(x, start, end, middle);
        }
    }

    private void merge(int[] x, int start, int end, int middle) {
        int leftSize = middle - start + 1;
        int rightSize = end - middle;

        int[] a = new int[leftSize];
        int[] b = new int[rightSize];

        for (int j = 0; j < leftSize; j++) {
            a[j] = x[start + j];
        }

        for (int j = 0; j < rightSize; j++) {
            b[j] = x[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = start;

        while (i < leftSize && j < rightSize) {
            if (a[i] <= b[j]) {
                x[k] = a[i];
                i++;
            } else {
                x[k] = b[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            x[k] = a[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            x[k] = b[j];
            j++;
            k++;
        }
    }

    public void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    public int[] generateSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        advancedSort(arr);
        return arr;
    }
}