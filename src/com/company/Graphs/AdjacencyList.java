package com.company.Graphs;

public class AdjacencyList {
    Vertex start;
    AdjacencyList(){
        start=null;
    }
    private class Edge{
        Vertex destVertex;
        Edge nextEdge;
    }
    private class Vertex{
        int info;
        Vertex nextVertex;
        Edge firstEdge;
    }
    
    public void insertVertex(int info){
        Vertex vertex =new Vertex();
        vertex.info=info;
        vertex.nextVertex=null;
        vertex.firstEdge=null;
        if(start == null){
            start=vertex;
            return;
        }
        Vertex temp=start;
        while(temp.nextVertex != null){
            temp=temp.nextVertex;
        }
        temp.nextVertex=vertex;
    }
    public Vertex findVertex(int info){
        Vertex temp=start,loc=null;
        while(temp != null){
            if(temp.info == info){
                loc=temp;
                return loc;
            }
            temp=temp.nextVertex;
        }
        return null;
    }
    public void insertEdge(int origin, int dest){
        Vertex locOrigin,locDest;
        locOrigin=findVertex(origin);
        locDest=findVertex(dest);
        Edge temp,newEdge;
        if(locOrigin == null) {
            System.out.println("Origin vertex not found. First insert the source vertex ");
            return;
        }
        if(locDest == null){
            System.out.println("Destination vertex not found. First insert the destination vertex");
            return;
        }
        newEdge=new Edge();
        newEdge.destVertex=locDest;
        newEdge.nextEdge=null;
        if(locOrigin.firstEdge == null){
            locOrigin.firstEdge =newEdge;
            return;
        }
        temp=locOrigin.firstEdge;
        while(temp.nextEdge != null){
            temp=temp.nextEdge;
        }
        temp.nextEdge=newEdge;
    }

    public void display(){
        if(start == null ){
            System.out.println("Graph is empty");
            return;
        }
        Vertex tempVertex =start;
        Edge tempEdge;
        while(tempVertex != null){
            System.out.print(tempVertex.info );
            tempEdge =tempVertex.firstEdge;
            while(tempEdge != null){
                System.out.print(" --> "+tempEdge.destVertex.info);
                tempEdge=tempEdge.nextEdge;
            }
            System.out.println("");
            tempVertex=tempVertex.nextVertex;
        }
    }

    public void deleteVertex(int info){
        if(start == null){
            System.out.println("Graph is empty can Not delete");
            return;
        }
        if(start.info == info){
            start=start.nextVertex;
        }
        else {
        }
    }
    
    
}
