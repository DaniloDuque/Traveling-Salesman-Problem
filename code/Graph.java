import java.util.*;



class edge{

    public int next;
    public int cost;

    public edge(int destination, int cost){

        this.next = destination;
        this.cost = cost;

    }


}


class node{

    private int id;
    private List<edge> adjacent;

    public node(int id){

        this.id = id;
        this.adjacent = new ArrayList<>();

    }

    public void addAdjacent(int neigh, int cost){
        adjacent.add(new edge(neigh, cost));
    }

    public List<edge> getAdjacent(){
        return adjacent;
    }

    public int getId(){
        return id;
    }

}



public class Graph{

    private Map<Integer, node> nodes;
    private int size;

    public Graph(int size){
        this.size = size;
        nodes = new HashMap<>();
        for(int i = 1; i<=size; i++) nodes.put(i, new node(i));
    }

    public void addEdge(int sourceId, int destinationId, int cost){
        node source = nodes.get(sourceId);
        node destination = nodes.get(destinationId);
        source.addAdjacent(destinationId, cost);
        destination.addAdjacent(sourceId, cost);
    }


    public List<edge> getAdjacent(int nodeId){
        return nodes.get(nodeId).getAdjacent();
    }


}
