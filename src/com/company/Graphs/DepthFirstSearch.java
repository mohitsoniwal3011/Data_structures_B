package com.company.Graphs;

import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearch {
    int n;
    int [][] adj ;
    vertexState[] state ;
    enum vertexState{
        initial,visited;
    }
    DepthFirstSearch(){
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        n= sc.nextInt();
        adj=new int[n][n];
        state =new vertexState[n];
    }
    public void createGraph(){
        Scanner sc=new Scanner(System.in);
        int graph_type,max_edges;
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
    public void DF_traversal(){
        int v;
        for(int i=0;i<n;i++){
            state[i]=vertexState.initial;
        }
        System.out.println("Enter the starting vertex for Depth First Search:");
        v=new Scanner(System.in).nextInt();
        DFS(v);
    }
    public void DFS(int v){
        Stack<Integer> st=new Stack<>();
        st.push(v);
        while(!st.isEmpty()){
            v= st.pop();
            if(state[v]  == vertexState.initial){
                System.out.print(v+"        ");
                state[v]=vertexState.visited;
            }
            for(int i=n-1;i>=0;i--){
                if(adj[v][i] == 1 && state[i] == vertexState.initial){
                    st.push(i);
                }
            }
        }
    }
}
