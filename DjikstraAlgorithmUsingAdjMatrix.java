package com.Algorithms2;
import java.util.*;

//to find the shortest path for every node in the graph from the source vertex
public class DjikstraAlgorithmUsingAdjMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numVertices = sc.nextInt();
        int numEdges = sc.nextInt();
        int[][] adjMatrix = new int[numVertices+1][numVertices+1];
        for(int i=0;i<numEdges;i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int weight = sc.nextInt();
            adjMatrix[v1][v2] = weight;
        }
        shortestPath(adjMatrix, 1);

    }
    public static void shortestPath(int[][] adjMatrix,int sourceNode){
        int v = adjMatrix.length;
        int[] weight = new int[v];
        HashSet<Integer> settled = new HashSet<>();
        for(int i=0;i<v;i++){
            weight[i] = Integer.MAX_VALUE;
        }
        weight[sourceNode] = 0;
        while(settled.size()!=v-1){
            int minVertex = findMinVertex(weight, settled); // remove the vertex with the lowest path
            neighborsRelaxation(minVertex, adjMatrix[minVertex],weight);
            settled.add(minVertex);
        }
        for(int i=1;i<v;i++){
            System.out.println(i + " path = " + weight[i]);
        }
    }
    public static int findMinVertex(int[] weight, Set<Integer> settled){
        int index=0;
        int lowestWeight=weight[0];
        for(int i=1;i<weight.length;i++){
            if(weight[i]<lowestWeight && !settled.contains(i)){
                index=i;
                lowestWeight=weight[i];
            }
        }
        return index;
    }
    public static void neighborsRelaxation(int source, int[] neighbors, int[] weight){
        for(int i=0;i<neighbors.length;i++){
            if(neighbors[i]!=0){
                 int newDist = weight[source] + neighbors[i];
                 if(weight[i]>newDist){
                     weight[i] = newDist;
                 }
            }
        }
    }
}
