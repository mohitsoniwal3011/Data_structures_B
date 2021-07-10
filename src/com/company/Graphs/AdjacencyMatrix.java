package com.company.Graphs;


import java.util.Scanner;

public class AdjacencyMatrix {
    static  int n;
    public static void main(String[] args) {
        int [][] b=new int[5][4];
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        n= sc.nextInt();
        int [][] adj =new int[n][n];
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
        for(int i=1;i<=max_edges;i++){
            System.out.println("Enter the edge "+i+" (enter -1 -1 to quit)");
            origin=sc.nextInt();
            destination= sc.nextInt();
            if(origin == -1 && destination == -1){
                break;
            }
            if(origin >=n  || origin<0 || destination <0){
                System.out.println("invalid vertex!!");
                i--;
            }
            else {
                adj[origin][destination]=1;
                if(graph_type == 1){
                    adj[destination][origin]=1;
                }
            }
        }
        System.out.println("Adjancecy matrix for the given graph is :");
        printMatrix(adj);
    }
    public static void printMatrix(int [][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.print(a[i][j]+"      ");
            }
            System.out.println("");
        }

    }
}
