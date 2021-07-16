package com.company.Graphs;

public class KruskalHelper {
    public static void main(String[] args) {
        KruskalAlgorithm kruskalAlgorithm=new KruskalAlgorithm();
        kruskalAlgorithm.createGraph();
        kruskalAlgorithm.makeTree();
        kruskalAlgorithm.print();
    }
}
