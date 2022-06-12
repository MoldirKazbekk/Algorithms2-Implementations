package com.Algorithms2;
import java.util.*;
//Kruskal's algorithm implementation
public class MST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter number of vertices: ");
        int numberOfVertices = sc.nextInt();
        //System.out.println("Enter number of edges: ");
        int numberOfEdges = sc.nextInt();

        LinkedList<Edge> edges = new LinkedList<>();  //stores all edges in original graph
        HashMap<Integer, Set<Integer>> forest = new HashMap<>();
        for(int i=0;i<numberOfEdges;i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int weight = sc.nextInt();
            forest.put(v1, new HashSet<>(v1)); //make a distinct set for every vertex in the graph:
            forest.put(v2, new HashSet<>(v2));
            edges.add(new Edge(v1,v2,weight));
        }
        Collections.sort(edges); // - Step 1:sort the edges by weights in ascending order
        Set<Edge> MST = new HashSet<>();
        int cost = 0; //to count the minimum value for spanning tree
        while(MST.size()<numberOfVertices-1){ //repeat the statements until there are (v-1) edges in the spanning tree
            Edge current = edges.removeFirst(); // - Step 2: Pick the smallest edge
            Set<Integer> v1 = forest.get(current.v1);
            Set<Integer> v2 = forest.get(current.v2);
            if (v1!=v2){ // Check if the edge forms a cycle with the spanning tree formed so far
                MST.add(current); //If it isn't, put it to the MST
                cost+=current.weight;
                v1.addAll(v2); // merge 2 sets of reachable vertices
                for(Integer i : v1) // include the merged set for every vertex in this set
                    forest.put(i, v1);
            }
        }

        System.out.println("Minimum cost = " + cost);
        for(Edge e:MST)
            System.out.println(e);
    }
}
class Edge implements Comparable<Edge>{
    int v1;
    int v2;
    int weight;

    public Edge(int v1, int v2, int w){ //creates an Edge with vertex and path information
        this.v1 = v1;
        this.v2 = v2;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge o) { //used in sort method for sorting by the path
        if(o.weight==this.weight)
            return 0;
        else
            return (this.weight>o.weight) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertex1 = " + v1 +
                ", vertex2 = " + v2 +
                ", weight = " + weight +
                '}';
    }
}

//        6
//        8
//        1 6 2
//        1 2 4
//        2 6 5
//        2 3 6
//        3 6 1
//        3 4 3
//        4 5 2
//        5 6 4
