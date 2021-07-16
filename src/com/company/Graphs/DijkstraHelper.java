package com.company.Graphs;

import java.util.Scanner;

public class DijkstraHelper {
    public static void main(String[] args) {
        DijkstraAlgorithm d=new DijkstraAlgorithm();
        Scanner sc=new Scanner(System.in);
        d.createGraph();
        System.out.println("Enter the source vertex : ");
        int source= sc.nextInt(),dest;
        d.findDistance(source);
        while(true){
            System.out.println("Enter destination vertex (-1 to quit ): ");
            dest= sc.nextInt();
            if(dest == -1 ){
                break;
            }
            if(dest< 0 || dest >= d.n){
                System.out.println("Destination vertex does not exist");
            }
            else if(dest == source){
                System.out.println("Source and destination vertices are the same");
            }
            else {
                d.findPath(source,dest);
            }
        }
    }
}
