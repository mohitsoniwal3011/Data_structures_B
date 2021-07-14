package com.company.Graphs;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimAlgorithm {
    private  static class Edge{
        int origin;
        int destination;
        Edge(int u,int v){
            origin=u;
            destination=v;
        }
    }
    enum vertexStatus {
        temporary,permanent
    }
    int n;
    vertexStatus [] status;
    int [][] adj;
    int[] predecessor,length;
    int Nil,Infinity;
    ArrayList<Edge> tree;
    PrimAlgorithm(){
        System.out.println("Enter number of vertices:");
        n=new Scanner(System.in).nextInt();
        adj=new int[n][n];
        status=new vertexStatus[n];
        predecessor=new int[n];
        length=new int[n];
        tree=new ArrayList<>();
        Nil=Integer.MIN_VALUE;
        Infinity=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            status[i]=vertexStatus.temporary;
            length[i]=Infinity;
            predecessor[i]=Nil;
        }
    }
    public void createGraph(){
        Scanner sc=new Scanner(System.in);
        int max_edges,weight,origin,destination;
        max_edges =n*(n-1)/2;
        for(int i=1;i<=max_edges;i++) {
            System.out.println("Enter the edge " + i + " (enter -1 -1 to quit)");
            origin = sc.nextInt();
            destination = sc.nextInt();
            if (origin == -1 && destination == -1) {
                break;
            }
            System.out.println("Enter the weight of the vertex:");
            weight= sc.nextInt();
            if (origin >= n || destination >= n||origin < 0 || destination < 0) {
                System.out.println("invalid vertex!!");
                i--;
            } else {
                adj[origin][destination] = weight;
                adj[destination][origin] = weight;
            }
        }
    }
    public void makeTree(int root){
        int count=0;
        length[root]=0;
        int current,i;
        while(true){
            current=minTemp();
            if(current== Nil){
                //if count != n-1 then graph is not connected
                if (count != n - 1) {
                    System.out.println("Graph is Not connected, so no spanning tree possible");
                }
                return;
            }
            status[current]=vertexStatus.permanent;
            if(current != root){
                count++;
                tree.add(new Edge(predecessor[current],current));
            }
            for(i=0;i<n;i++){
                if(status[i] == vertexStatus.temporary && adj[current][i] >0){
                    if(adj[current][i] < length[i]){
                        predecessor[i]=current;
                        length[i]= adj[current][i];
                    }
                }
            }
        }
    }
    public int minTemp(){
        int k=Nil,min =Infinity;
        for(int i=0;i<n;i++) {
            if (status[i] == vertexStatus.temporary &&  length[i]<min) {
                min = length[i];
                k = i;
            }
        }
        return k;
    }
    public void displayMST(){
        int wtTree=0;
        System.out.println("edges to be included in the Minimum Spanning Tree are :");
        System.out.println("Edge        Weight");
        Edge temp;
        for(int i=0;i< tree.size();i++){
            temp=tree.get(i);
            System.out.println("("+temp.origin+","+temp.destination+")        "+adj[temp.origin][temp.destination]);
            wtTree +=adj[temp.origin][temp.destination];
        }
        System.out.println("Weight of the minimum spanning tree is: "+wtTree);
    }


}
