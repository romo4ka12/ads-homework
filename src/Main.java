import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Searcher searcher = new Searcher();
        Experiment experiment = new Experiment(sorter, searcher);

        int[] demoArray = sorter.generateRandomArray(15);
        System.out.print("Before sorting: ");
        sorter.printArray(demoArray);

        sorter.advancedSort(demoArray);
        System.out.print("After sorting: ");
        sorter.printArray(demoArray);

        experiment.runAllExperiments();
    }
}

class Sorter {
    public void basicSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public void advancedSort(int[] arr) {
        sort(arr);
    }

    public void sort(int[] x) {
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
        for (int i = 0; i < Math.min(arr.length, 15); i++) {
            System.out.print(arr[i] + " ");
        }
        if (arr.length > 15) {
            System.out.print("... [" + arr.length + " elements]");
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

class Searcher {
    public int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

class Experiment {
    private Sorter sorter;
    private Searcher searcher;

    public Experiment(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }

    public long measureSortTime(int[] arr, String type) {
        int[] copy = Arrays.copyOf(arr, arr.length);

        long startTime = System.nanoTime();
        if (type.equals("basic")) {
            sorter.basicSort(copy);
        } else if (type.equals("advanced")) {
            sorter.advancedSort(copy);
        }
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();
        searcher.search(arr, target);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000, 10000};

        for (int size : sizes) {
            System.out.println("Array Size: " + size);

            int[] randomArr = sorter.generateRandomArray(size);
            int[] sortedArr = sorter.generateSortedArray(size);

            long basicRandomTime = measureSortTime(randomArr, "basic");
            long advancedRandomTime = measureSortTime(randomArr, "advanced");

            long basicSortedTime = measureSortTime(sortedArr, "basic");
            long advancedSortedTime = measureSortTime(sortedArr, "advanced");

            System.out.println("[Input Data: Random]");
            System.out.println("  Basic Sort (Insertion): " + basicRandomTime + " ns");
            System.out.println("  Advanced Sort (Merge):  " + advancedRandomTime + " ns");

            System.out.println("[Input Data: Sorted]");
            System.out.println("  Basic Sort (Insertion): " + basicSortedTime + " ns");
            System.out.println("  Advanced Sort (Merge):  " + advancedSortedTime + " ns");


            int target = sortedArr[size / 2];
            long searchTime = measureSearchTime(sortedArr, target);

            System.out.println("[Search: Binary Search]");
            System.out.println("  Search Time: " + searchTime + " ns\n");
        }
    }
}
