## Analysis Quetions
**How does graph size affect BFS and DFS performance?** \
As the number of vertices V and edges E increases, 
the execution time grows linearly. 
This confirms that performance depends on the amount of vertices and edges in the network.\
**Which traversal is faster in your experiments?** \
DFS was slightly faster because it avoids managing an explicit Java queue object. \
**Do results match the expected complexity O(V + E)?** \
Yes. The time increases smoothly which again shows linear dependency.\
**How does graph structure affect traversal order?**\
The arrangement of edges changes the path the algorithm takes. 
If the graph has loops, both algorithms need a `Visited` set, 
or the program will get stuck in an infinite loop. \
**When is BFS preferred over DFS?**\
BFS is preferred when you need to find the shortest path 
to a target in an unweighted graph. \
**What are the limitations of DFS?**
The main limitation is the risk of stack overflow. 
If a graph is very deep, the recursive calls will run out of system stack memory and crash.


## A. Project Overview

A graph is a non-linear data structure made of points called vertices or nodes 
and lines connecting them called edges. 
Graphs help us model networks like roads on a map. 
Vertices represent objects where each vertex has a unique number identifier. 
Edges represent the connections between objects.Graph traversal is the process of visiting every 
vertex in the graph and we use two main methods for this which are BFS and DFS. \

## B. Class Description
Our program is divided into 5 simple classes:
1. **Vertex:** Stores only the unique number - id of the node.
2. **Edge:** Stores the connection information where it starts - 'source', and where it ends - 'destination'.
3. **Graph:** It stores vertices and edges using an 'Adjacency List'. This means each vertex has a simple list of its neighbors, which saves computer memory.
4. **Experiment:** Automatically creates graphs of different sizes (10, 30, and 100 vertices) and measures how many nanoseconds the computer takes to visit all nodes.

## C. Algorithm Descriptions

1. Breadth-First Search (BFS)
   Breadth First Search starts at one source vertex and explores the graph layer by layer 
   It first marks the source vertex as visited and puts it into a queue which handles 
   data in a first in first out order. Then the algorithm enters a loop where it takes 
   a vertex out of the queue, processes it, and looks at all of it unvisited 
   neighbors. Each of these neighbors is marked as visited and added to the back of the queue
   so they can be processed in the next wave. This process repeats continuously until 
   the queue becomes completely empty. BFS is most commonly used when you need to find the 
   absolute shortest path on a map where all roads have the same distance. The time complexity 
   of this algorithm is O(V + E) because the time grows linearly depending on the 
   total number of vertices and edges in the graph structure.

2. Depth First Search (DFS)
   Depth First Search takes approach by moving as deep as possible along one single path 
   of connections until it hits a dead end. It starts at a source vertex, 
   marks it as visited, and then moves to the first unvisited neighbor it can find.
   The algorithm achieves this deep diving behavior by using recursion where a function calls
   itself, which creates a stack structure with the path taken. 
   When the algorithm reaches a dead end where a vertex has no more unvisited neighbors,
   it returns up the stack to the previous vertex and starts trying to
   explore other available paths. This behavior makes DFS perfect for solving mazes 
   or checking if any path exists between two points. 
   The time complexity is also O(V + E) since the algorithm still needs to check every vertex
   and edge in the graph exactly once in the worst case.

## D. Experimental Results

| Graph Size | BFS        | DFS       |
|------------|------------|-----------|
| 10 V       | 999800 ns  | 404600 ns |
| 30 V       | 69900 ns   | 41300 ns  |
| 100 V      | 157900 ns  | 104100 ns |


## F. Reflective Section
In this assignment I learned how graphs actually work in practice. 
Implementing an adjacency list showed me a simple way to store connections 
between nodes without wasting computer memory. I now clearly understand 
the main difference between the two traversals because BFS moves layer by layer using a queue 
while DFS dives straight ahead until it hits a wall using recursion.
Overall this project helped me feel much more confident about writing graph algorithms.