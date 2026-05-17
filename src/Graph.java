import java.util.*;

class Graph {
    private Map<Integer, List<Edge>> adjList = new HashMap<>();
    private Map<Integer, Vertex> vertices = new HashMap<>();

    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        if (vertices.containsKey(from) && vertices.containsKey(to)) {
            adjList.get(from).add(new Edge(vertices.get(from), vertices.get(to)));
        }
    }

    public void printGraph() {
        for (int key : adjList.keySet()) {
            System.out.print(key + ": ");
            for (Edge e : adjList.get(key)) {
                System.out.print(e.getDestination().getId() + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (adjList.size() <= 10) System.out.print(current + " ");
            for (Edge edge : adjList.get(current)) {
                int neighbor = edge.getDestination().getId();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(int current, Set<Integer> visited) {
        visited.add(current);
        if (adjList.size() <= 10) System.out.print(current + " ");
        for (Edge edge : adjList.get(current)) {
            int neighbor = edge.getDestination().getId();
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }
}
