package Algorithms2;

import java.util.*;

//for computing max flow
public class FordFulkersonAlgo {
    public static void main(String[] args) {
        int vertices = 6;
        int[][] adjMatrix = {
                {0, 10, 8, 0, 0, 0},
                {0, 0, 5, 5, 0, 0},
                {0, 4, 0, 0, 10, 0},
                {0, 0, 9, 0, 10, 3},
                {0, 0, 0, 6, 0, 14},
                {0, 0, 0, 0, 0, 0}
        };
        Graph directedGraph = new Graph(vertices, adjMatrix);
        int maxFlow = directedGraph.computeMaxFlow(0, 5);
        System.out.println("max flow from 0 to 5 is: " + maxFlow);
    }
}

class Graph {
    int vertices;
    int[][] adjMatrix;

    public Graph(int vertices, int[][] adjMatrix) {
        this.vertices = vertices;
        this.adjMatrix = adjMatrix;
    }
    public int computeMaxFlow(int source, int sink) {
        int[][] residualGraph = new int[vertices][vertices];
        int maxFlow = 0; //to store the final answer
        //initialize residual graph same as original graph (flow = 0, residual capacity = original capacity)
        for (int i = 0; i < vertices; i++) {
            System.arraycopy(adjMatrix[i], 0, residualGraph[i], 0, vertices);
        }
        //to store the path from source node to destination (parent of source-parent[source]=-1)
        int[] parents = new int[vertices];
        while (isPathExist_BFS(residualGraph, source, sink, parents)) {
            //parents[] contain the path from source to sink node
            int currentMaxFlow = Integer.MAX_VALUE;
            int current = sink;

            //find the flow which can be passed through the path - min capacity in the path
            while (current != source) {
                int parent = parents[current];
                currentMaxFlow = Math.min(currentMaxFlow, residualGraph[parent][current]);
                current = parent;
            }

            //update the residual graph edges
            current = sink;
            while (current != source) {
                int parent = parents[current];
                residualGraph[parent][current] -= currentMaxFlow;// reduce the forward edge capacity (remained part of original capacity)
                residualGraph[current][parent] += currentMaxFlow;// increase the backward edge capacity (current flow)
                current = parent;
            }
            maxFlow += currentMaxFlow;
        }
        return maxFlow;
    }

    public boolean isPathExist_BFS(int[][] residualGraph, int source, int dest, int[] parents) {
        //to keep track of visited vertices;
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        //insert the source vertex and mark it visited
        queue.offer(source);
        parents[source] = -1;
        visited[source] = true;
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            //iterate through adjacent vertices of currentVertex
            for (int v = 0; v < vertices; v++) {
                //if the adj vertex is not visited yet, add it to the queue and mark it visited
                if (!visited[v] && residualGraph[currentVertex][v] > 0) {
                    queue.offer(v);
                    parents[v] = currentVertex;
                    visited[v] = true;
                }
            }
        }
        return visited[dest];
    }
}
