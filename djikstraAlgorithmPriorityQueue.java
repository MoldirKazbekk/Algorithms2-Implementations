package com.Algorithms2;
import java.util.*;
// for directed graphs
public class djikstraAlgorithmPriorityQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numVertices = sc.nextInt();
        int numEdges = sc.nextInt();
        ArrayList<Vertex> graph = new ArrayList<>();

        for(int i=0;i<numVertices+1;i++){
            graph.add(new Vertex(i)); //add n number of vertices to the graph
        }
        for(int i=0;i<numEdges;i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(v1).addNeighbor(graph.get(v2), weight); // build connections between vertices by filling their adjacency list
        }

        int source = sc.nextInt(); // source node that the shortest path would be calculated from
        shortestPathFrom(graph, graph.get(source));

        graph.remove(0); // if the vertices starts from the 1
        for(Vertex v: graph)
            System.out.println(v.index + " distance = " + v.getPath());
    }
    public static void shortestPathFrom(ArrayList<Vertex> graph, Vertex source){
         PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(graph.size()); // 1st step: create a priority queue
         source.setPath(0); // set source node's path to 0
         priorityQueue.offer(source); // add it to the pr queue
         while(!priorityQueue.isEmpty()){ //do until the queue is empty
             Vertex currentV = priorityQueue.poll();  // remove the lowest path vertex
             for(Map.Entry<Vertex, Integer> adjPair:currentV.adjList.entrySet()){ //iterate through adjacency list of the current vertex
                 Vertex neighbor = adjPair.getKey();
                 int weightOfEdge = adjPair.getValue();
                 if(!neighbor.isVisited()){ // if the vertex is not visited(evaluated finally) - is not included in the settled set
                     int newDist = currentV.getPath() + weightOfEdge; // relaxation of the neighbor vertex
                     if(newDist<neighbor.getPath()){ // if the shortest path is found
                         priorityQueue.remove(neighbor); //update the value of the vertex and add it to the list
                         neighbor.setPath(newDist);
                         neighbor.setPredecessor(currentV);
                         priorityQueue.add(neighbor);
                     } // otherwise it means it is already included in the priority queue

                 }
             }
             currentV.setVisited();
         }
    }
    public static ArrayList<Vertex> shortestPathTo(ArrayList<Vertex> graph,Vertex target){ //to get vertices in the shortest path to the target Node
        ArrayList<Vertex> vertices = new ArrayList<>();
        for(Vertex v = target;target != null; target = target.predecessor){
            vertices.add(target);
        }
        Collections.reverse(vertices);
        return vertices;
    }
}
class Vertex implements Comparable<Vertex>{
    int index;
    boolean visited;
    Vertex predecessor;
    HashMap<Vertex, Integer> adjList = new HashMap<>();
    int path = Integer.MAX_VALUE; // path from source node to this node initially infinite
    public Vertex(int index){
        this.index = index;
    }

    public void addNeighbor(Vertex vertex, int weight){
        adjList.put(vertex, weight);
    }
    public void setVisited() {
        this.visited = !visited;
    }
    public boolean isVisited(){
        return visited;
    }
    public int getPath() {
        return path;
    }
    public void setPath(int path) {
        this.path = path;
    }
    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(this.path, o.path);
    }
    public void setPredecessor(Vertex v) {
        predecessor = v;
    }
}
//        5
//        7
//        1 2 17
//        1 3 10
//        3 2 5
//        2 4 1
//        3 4 9
//        4 5 6
//        3 5 11
//        1
