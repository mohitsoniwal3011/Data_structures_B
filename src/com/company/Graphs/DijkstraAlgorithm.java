package com.company.Graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DijkstraAlgorithm {
    int [][] adj;
    int n,Infinity,Nil;
    int [] d;  //to store the shortest distance
    int [] predecessor;
    Scanner sc=new Scanner(System.in);
    enum vertexState{
        temporary,permanent;
    }
    vertexState[] status;
    DijkstraAlgorithm(){
        System.out.println("Enter the number of vertices:");
        n=sc.nextInt();
        adj=new int[n][n];
        Infinity =Integer.MAX_VALUE;
        Nil =Integer.MIN_VALUE;
        status=new vertexState[n];
        d=new int[n];
        predecessor=new int[n];
        for(int i=0;i<n;i++){
            status[i]=vertexState.temporary;
            d[i]=Infinity;
            predecessor[i]=Nil;
        }
    }

    public void findDistance(int source){
        d[source]=0;
        int count =0,current;
        while(count <= n){
            current=findMin();
            if(current == -1){
                break;
            }
            status[current]=vertexState.permanent;
            count++;
            for(int i=0;i<n;i++){
                if(status[i] == vertexState.temporary && adj[current][i] > 0 ){
                   if(d[current] + adj[current][i] < d[i]){
                       predecessor[i]=current;
                   }
                    d[i] =Integer.min(d[current]+adj[current][i],d[i]);
                }
            }
        }
        printDist(source);
    }
    //Below function finds the shortest path between two given vertices and prints it
    public void findPath(int source, int dest){
        List<Integer> path=new LinkedList<>();
        int temp=dest;
        while(temp != Nil){
            path.add(0,temp);
            if(temp == source){
                break;
            }
            temp=predecessor[temp];
        }
        if(temp  == Nil){
            System.out.println("Given two vertices does not contain any path between them");
            return;
        }
        System.out.print(path.get(0));
        for(int i=1;i<path.size();i++){
            System.out.print(" --> "+path.get(i));
        }
        System.out.println("");
        System.out.println("Minimum distance between "+source+" and "+dest+" is : "+d[dest]);
    }
    public int findMin(){
        int k=-1;
        int min =Infinity;
        for(int i=0;i<n;i++){
            if(status[i] == vertexState.temporary && d[i] < min ){
                k=i;
                min=d[i];
            }
        }
        return k;
    }

    public void printDist(int source){
        System.out.println("Minimum distance from "+source+" to every other vertex is:");
        System.out.println("Vertex   Distance");
        for(int i=1;i<n;i++){
            if(d[i] != Infinity) {
                System.out.println(i + "        " + d[i]);
            }
            else {
                System.out.println(i + "        Infinity");
            }
        }
        System.out.println("");
    }

    public void createGraph(){
        Scanner sc=new Scanner(System.in);
        int max_edges,graph_type,origin,destination,weight;
        System.out.print("Enter 1 for undirected graph and 2 for directed graph: ");
        graph_type= sc.nextInt();
        max_edges=(graph_type==1)?n*(n-1)/2:n*(n-1);
        for(int i=1;i<=max_edges;i++) {
            System.out.println("Enter the edge " + i + " (enter -1 -1 to quit)");
            origin = sc.nextInt();
            destination = sc.nextInt();
            if (origin == -1 && destination == -1) {
                break;
            }
            if (origin >= n || origin < 0 || destination<0 ||destination >= n) {
                System.out.println("invalid vertex!!");
                i--;
            } else {
                System.out.println("Enter the weight of the vertex:");
                weight= sc.nextInt();
                adj[origin][destination] = weight;
                if (graph_type == 1) {
                    adj[destination][origin] = weight;
                }
            }
        }
    }
}
