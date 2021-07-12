package com.company.Graphs;

import java.util.Scanner;

public class EdgeClassifierDFS {
    int n,graph_type;
    int [][] adj ;
    int time=0;
    int [] d; //discovery time of a vertex
    int [] f; //finishing time of a vertex
    int [] predecessor;
    vertex_state [] state;
    enum vertex_state{
        initial,visited,finished;
    }
    EdgeClassifierDFS(){
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        n= sc.nextInt();
        adj=new int[n][n];
        state =new vertex_state[n];
        d=new int[n];
        f=new int[n];
        predecessor=new int[n];
    }

    public void DF_traversal() {
        int v;
        for (int i = 0; i < n; i++) {
            state[i] = vertex_state.initial;
        }
        System.out.println("Enter the starting vertex for Depth First Search:");
        v = new Scanner(System.in).nextInt();
        if(graph_type == 2){
            detectEdgeTypeDirected(v);
        }
        else {
            detectEdgeTypeUndirected(v);
        }

        for (v = 0; v < n; v++) {
            if (state[v] == vertex_state.initial) {
               if(graph_type == 2){
                   detectEdgeTypeDirected(v);
               }
               else {
                   detectEdgeTypeUndirected(v);
               }
            }
        }
    }

    public void createGraph(){
        Scanner sc=new Scanner(System.in);
        int max_edges;
        System.out.print("Enter 1 for undirected graph and 2 for directed graph: ");
        graph_type= sc.nextInt();
        int origin,destination;
        if(graph_type == 1){
            max_edges =n*(n-1)/2;
        }
        else {
            max_edges=n*(n-1);
        }
        for(int i=1;i<=max_edges;i++) {
            System.out.println("Enter the edge " + i + " (enter -1 -1 to quit)");
            origin = sc.nextInt();
            destination = sc.nextInt();
            if (origin == -1 && destination == -1) {
                break;
            }
            if (origin >= n || origin < 0 || destination < 0) {
                System.out.println("invalid vertex!!");
                i--;
            } else {
                adj[origin][destination] = 1;
                if (graph_type == 1) {
                    adj[destination][origin] = 1;
                }
            }
        }
    }


    public void detectEdgeTypeDirected(int v){
        time++;
        d[v]=time;
        state[v]=vertex_state.visited;
        for(int i=0;i<n;i++){
            if(adj[v][i] == 1){
                if(state[i] == vertex_state.initial){
                    System.out.println("("+v+","+i+") --> Tree edge");
                    detectEdgeTypeDirected(i);
                }
                else if(state[i] == vertex_state.visited){
                    System.out.println("("+v+","+i+") --> back edge");
                }
                else if(d[v] < d[i]){
                    System.out.println("("+v+","+i+") --> forward edge");
                }
                else {
                    System.out.println("("+v+","+i+") --> cross edge");
                }
            }
        }
        state[v]=vertex_state.finished;
        f[v]=++time;
    }

    public void detectEdgeTypeUndirected(int v){
        state[v]=vertex_state.visited;
        for(int i=0;i<n;i++){
            if(adj[v][i] == 1   && predecessor[v] != i){
                if(state[i] == vertex_state.initial){
                    System.out.println("("+v+","+i+") --> Tree edge");
                    predecessor[i]=v;
                    detectEdgeTypeUndirected(i);
                }
                else if(state[i] == vertex_state.visited){
                    System.out.println("("+v+","+i+") --> back edge");
                }
            }
        }
        state[v]=vertex_state.finished;
    }
}
