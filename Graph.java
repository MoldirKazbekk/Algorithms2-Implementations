package com.Algorithms2;
import java.util.Stack;
import java.util.*;

public class Graph{
    public static void main(String[] args) {
        boolean[] isVisited = new boolean[6];
        Node v1 = new Node('A');
        Node v2 = new Node('B');
        Node v3 = new Node('C');
        Node v4 = new Node('D');
        Node v5 = new Node('E');
        Node v6 = new Node('F');
        v1.makeEdge(v2);
        v2.makeEdge(v3);
        v2.makeEdge(v5);
        v3.makeEdge(v4);
        v3.makeEdge(v5);
        v5.makeEdge(v4);
        v5.makeEdge(v6);
        //System.out.println("bfs: ");
        // bfs(v1); //start traversing from the vertex v1
        System.out.println("dfs: ");
        dfsRecursion(v1);
    }
    public static void bfs(Node node){
        ArrayList<Node> queue = new ArrayList<>();  //queue for unvisited vertices adjacent to starting vertex
        queue.add(node);
        node.setVisited();
        System.out.print(node + " ");
        while(!queue.isEmpty()){
            Node removed = queue.remove(0);
            for(Node adjNode: removed.getAdjacencyList()){
                if(!adjNode.isVisited()){
                    adjNode.setVisited();
                    queue.add(adjNode);
                    System.out.print(adjNode + " ");
                }
            }
        }
    }

    public static void dfsUsingStack(Node startingPoint){
        Stack<Node> stack = new Stack<>(); //create a stack for saving unmarked vertices adjacent to startingPoint vertex
        stack.push(startingPoint); //push the starting vertex to the stack
        startingPoint.setVisited(); //mark it as visited
        while(!stack.isEmpty()){ //dfs is executed until the stack is empty
            Node currentV = stack.pop(); //remove the vertex from the stack
            for(Node neighbor: currentV.getAdjacencyList()){ //and add its adjacent vertices to the stack
                if(!neighbor.isVisited()){ //if they are unmarked
                    stack.push(neighbor);
                    neighbor.setVisited(); //finally mark it as visited
                }
            }
            System.out.print(currentV + " "); //it means current removed vertex is discovered entirely
        }
    }
    public static void dfsRecursion(Node startingPoint){
        System.out.println(startingPoint);
        startingPoint.setVisited();
        for(Node neighbor: startingPoint.getAdjacencyList()){
            if(!neighbor.isVisited()){
                dfsRecursion(neighbor);
            }
        }
    }
}
class Node {
    private boolean visited;
    private char index;
    boolean[] isVisited = new boolean[7];
    private LinkedList<Node> adjacencyList = new LinkedList<>(); //adjacency list representation of the vertex
    public Node(char index){
        this.index = index;
    }
    public void makeEdge(Node vertex){ //connect 2 vertices
        adjacencyList.add(vertex); //add vertex to adjacency List of the object
    }
    public boolean isVisited(){
        return visited;
    }
    public void setVisited(){
        visited = !visited; //make visited if it is unvisited, otherwise vise versa
    }
    public LinkedList<Node> getAdjacencyList(){
        return adjacencyList; //returns the adjacency list of the vertex
    }
    public String toString(){
        return index + " ";
    }
}