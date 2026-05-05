import java.util.Arrays;

public class Experiment {
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

            System.out.println("Input Data: Random");
            System.out.println("  Basic Sort (Insertion): " + basicRandomTime + " ns");
            System.out.println("  Advanced Sort (Merge):  " + advancedRandomTime + " ns");

            System.out.println("Input Data: Sorted");
            System.out.println("  Basic Sort (Insertion): " + basicSortedTime + " ns");
            System.out.println("  Advanced Sort (Merge):  " + advancedSortedTime + " ns");


            int target = sortedArr[size / 2];
            long searchTime = measureSearchTime(sortedArr, target);

            System.out.println("Search: Binary Search");
            System.out.println("  Search Time: " + searchTime + " ns\n");
        }
    }
}