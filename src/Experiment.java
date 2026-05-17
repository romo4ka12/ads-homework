class Experiment {
    public void runTraversals(Graph g, int size) {
        System.out.println("\nTesting Graph Size: " + size);
        if (size == 10) {
            System.out.println("Graph Structure:");
            g.printGraph();
        }

        long startBfs = System.nanoTime();
        if (size == 10) System.out.print("BFS Order: ");
        g.bfs(0);
        long endBfs = System.nanoTime();
        System.out.println("\nBFS Time: " + (endBfs - startBfs) + " ns");

        long startDfs = System.nanoTime();
        if (size == 10) System.out.print("DFS Order: ");
        g.dfs(0);
        long endDfs = System.nanoTime();
        System.out.println("\nDFS Time: " + (endDfs - startDfs) + " ns");
    }

    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        for (int size : sizes) {
            Graph g = new Graph();
            for (int i = 0; i < size; i++) g.addVertex(new Vertex(i));
            for (int i = 0; i < size; i++) {
                g.addEdge(i, (i + 1) % size);
                g.addEdge(i, (i + 5) % size);
            }
            runTraversals(g, size);
        }
    }
}