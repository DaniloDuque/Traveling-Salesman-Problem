import java.util.*;


public class BackTSP{

    private int V;
    private Graph graph;
    private final int INF = 1<<20;

    public BackTSP(Graph graph, int size){

        this.graph = graph;
        V = size;

    }

    private boolean TEST(int msk, int i){
        return (msk&(1<<i)) != 0;
    }


    private int SET(int msk, int i){
        return msk|(1<<i);
    }


    private pair<Integer, List<Integer>> min(pair<Integer, List<Integer>> x, pair<Integer, List<Integer>> y){

        if(x.first < y.first) return x;
        return y;

    }


    private pair<Integer, List<Integer>> findMin(int start, int curr, int msk, List<Integer> path, int cost, int cont){

        if(curr == start) cont++;
        else msk = SET(msk, curr);

        path.add(curr);

        if(curr == start && cont == 2 && path.size() > V) return new pair<>(cost, path);

        pair<Integer, List<Integer>> rslt = new pair<>(INF, new ArrayList<>());

        for(int i = 0; i<V; i++)

            if(graph.getCost(curr, i) != INF && !TEST(msk, i)) 

                rslt = min(rslt, findMin(start, i, msk, new ArrayList<>(path), cost + graph.getCost(curr, i), cont));

        return rslt;

    }


    private pair<Integer, List<Integer>> solveTSP(){

        pair<Integer, List<Integer>> rslt = new pair<>(INF, new ArrayList<>());
        for(int i = 0; i<V; i++) rslt = min(rslt, findMin(i, i, 0, new ArrayList<>(), 0, 0));
        return rslt;

    }


    public void showBackTSP(){

        pair<Integer, List<Integer>> rslt = solveTSP();
        System.out.println(rslt.first);
        for(Integer i: rslt.second) System.out.print(i+1 + " ");
        System.out.println();

    }




}
