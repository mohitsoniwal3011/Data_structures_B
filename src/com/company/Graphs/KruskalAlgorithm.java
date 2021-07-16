package com.company.Graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KruskalAlgorithm {
    int [] father;
    int Nil,n;
    PriorityQueue<Edge> pQueue ;
    ArrayList<Edge> tree;
    class Edge {
        int origin,destination,weight;
        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "("+this.origin+","+this.destination+")      "+this.weight;
        }

        public Edge(int u, int v, int w) {
            origin = u;
            destination = v;
            weight = w;
        }

    }
    KruskalAlgorithm(){
        System.out.println("Enter number of vertices:");
        n=new Scanner(System.in).nextInt();
        father=new int[n];
        Nil=-1;
        pQueue=new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        tree=new ArrayList<>();
        for(int i=0;i<n;i++){
            father[i]=Nil;
        }
    }

    public void makeTree(){
        int rootOrigin,rootDest;
        Edge temp;
        while(!pQueue.isEmpty()   && tree.size() < n-1){
            temp=pQueue.poll();
            rootOrigin=findRoot(temp.origin);
            rootDest=findRoot(temp.destination);
            if(rootOrigin != rootDest ){ //insert the edge (v1 , v2)
                tree.add(new Edge(temp.origin,temp.destination,temp.weight));
                father[rootDest]=rootOrigin;
            }
        }
        if(tree.size() < n-1){
            System.out.println("Graph is not connected, no spanning tree possible");
            tree.clear();   // remove all the edges form List<Edge> tree
        }
    }
    public int findRoot(int v){
        while(father[v] !=Nil){
            v=father[v];
        }
        return v;
    }

    public void createGraph(){
        int maxEdges=n*(n-1)/2 ;  //For undirected graph
        int origin, destination,weight;
        Scanner sc=new Scanner(System.in);
        for(int i=1;i<=maxEdges ;i++){
            System.out.println("Enter edge "+i+" (-1 -1 to quit) : ");
            origin=sc.nextInt();
            destination=sc.nextInt();
            if(origin == -1 && destination == -1){
                return;
            }
            if(origin < 0 || destination< 0 || origin >= n || destination >= n){
                System.out.println("Either source vertex or destination vertex is not correct, enter again");
                i--;
            }
            else {
                System.out.println("Enter weight of the Edge");
                weight = sc.nextInt();
                pQueue.add(new Edge(origin, destination, weight));
            }
        }
    }
    public void print(){
        System.out.println("The edges included in minimum spanning tree are:");
        System.out.println("Edge     weight");
        tree.forEach(System.out::println);
        int weight=0;
        for (Edge edge : tree) {
            weight = weight + edge.weight;
        }
        System.out.println("Weight of Minimum Spanning Tree : "+weight);

    }

}
