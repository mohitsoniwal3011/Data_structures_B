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

    public void deleteVertex(int info){
        Vertex temp,ptr;
        Edge tempEdge;
        if(start == null){
            System.out.println("Graph does not contain any vertices yet");
            return;
        }
        if(start.info == info){
            temp=start;
            start=start.nextVertex;
        }
        else {
            temp=start;
            while(temp.nextVertex != null){
                if(temp.nextVertex.info == info){
                    //ptr=temp.nextVertex;
                    //temp.nextVertex=temp.nextVertex.nextVertex;
                    break;
                }
                temp=temp.nextVertex;
            }
            if(temp.nextVertex == null){
                System.out.println("Vertex not present in the graph");
            }
            else {
               ptr=temp.nextVertex;
               temp.nextVertex=ptr.nextVertex;
            }
        }
    }

    public void deleteEdge(int origin, int destination){
        Edge tempEdge;
        Vertex temp=findVertex(origin);
        if(temp == null){
            System.out.println("Origin vertex not found, insert the origin vertex first");
            return;
        }
        System.out.println("temp = "+temp.info);
        if(temp.firstEdge == null){
            System.out.println("There is no such edge present in the graph");
            return;
        }
        if(temp.firstEdge.destVertex.info == destination){
            temp.firstEdge=temp.firstEdge.nextEdge;
            return;
        }
        tempEdge =temp.firstEdge;
        while(tempEdge.nextEdge != null){
            if(tempEdge.nextEdge.destVertex.info == destination){
                tempEdge.nextEdge=tempEdge.nextEdge.nextEdge;
                return;
            }
            tempEdge=tempEdge.nextEdge;
        }
        System.out.println("There is no such edge present in the graph");
    }

    public void deleteIncomingEdges(int info){
        Vertex temp=start;
        Edge q;
        while(temp != null){
            if(temp.firstEdge == null){
                temp=temp.nextVertex;
                continue;
            }
            if(temp.firstEdge.destVertex.info == info){
                temp.firstEdge=temp.firstEdge.nextEdge;
                continue;
            }
            q=temp.firstEdge;
            while(q.nextEdge != null){
                if(q.nextEdge.destVertex.info == info){
                    q.nextEdge=q.nextEdge.nextEdge;
                    continue;
                }
                q=q.nextEdge;
            }
            temp=temp.nextVertex;
        }
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

}
