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

