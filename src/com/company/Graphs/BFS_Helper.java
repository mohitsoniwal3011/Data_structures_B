package com.company.Graphs;

import java.util.Scanner;

public class BFS_Helper {
    static int max =100;
    public static void main(String[] args) {
        BreadthFirstSearch bfs=new BreadthFirstSearch();
        bfs.createGraph();
        bfs.BF_Traversal();
    }
}
