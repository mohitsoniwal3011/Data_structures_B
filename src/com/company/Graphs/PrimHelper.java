package com.company.Graphs;

import java.util.Scanner;

public class PrimHelper {
    public static void main(String[] args) {
        PrimAlgorithm prim=new PrimAlgorithm();
        prim.createGraph();
        System.out.println("Enter root vertex :");
        int root =new Scanner(System.in).nextInt();
        prim.makeTree(root);
        prim.displayMST();
    }
}
