package com.company.Graphs;

import javax.print.DocFlavor;
import java.util.Scanner;

public class AdjacencyListHelper {
    public static void main(String[] args) {
        AdjacencyList adjacencyList=new AdjacencyList();
        int choice,u,origin, destination;
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1.Insert a vertex");
            System.out.println("2.Insert an edge");
            System.out.println("3.Delete a vertex");
            System.out.println("4.Delete an edge ");
            System.out.println("5.Display");
            System.out.println("6.Exit");
            System.out.println("Enter your choice");
            choice=sc.nextInt();
            switch(choice){
                case 1 ->{
                    System.out.println("Enter the vertex to be inserted:");
                    u= sc.nextInt();
                    adjacencyList.insertVertex(u);
                }
                case 2 ->{
                    System.out.println("Enter the edge to be inserted:");
                    origin= sc.nextInt();
                    destination= sc.nextInt();
                    adjacencyList.insertEdge(origin,destination);
                }
                case 3 ->{
                    System.out.println("Enter the vertex to be deleted:");
                    u= sc.nextInt();
                }
                case 4 ->{
                    System.out.println("Enter the edge to be deleted");
                    origin= sc.nextInt();
                    destination=sc.nextInt();
                }
                case 5 ->{
                    adjacencyList.display();
                }
                case 6 ->{
                    System.out.println("THANK YOU HAVE A NICE DAY");
                    return;
                }
                default -> {
                    System.out.println("Wrong choice entered. Enter again");
                }
            }
        }
    }
}
