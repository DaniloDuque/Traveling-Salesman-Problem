import java.util.*;


public class BackTSP{

    private int V;
    private Graph graph;

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

        pair<Integer, List<Integer>> rslt = new pair<>(1<<30 | 1<<29, new ArrayList<>());

        for(edge e: graph.getAdjacent(curr))

            if(!TEST(msk, e.next)) rslt = min(rslt, findMin(start, e.next, msk, new ArrayList<>(path), cost + e.cost, cont));

        return rslt;

    }


    private pair<Integer, List<Integer>> solveTSP(){

        pair<Integer, List<Integer>> rslt = new pair<>(1<<30 | 1<<29, new ArrayList<>());
        for(int i = 1; i<=V; i++) rslt = min(rslt, findMin(i, i, 0, new ArrayList<>(), 0, 0));
        return rslt;

    }


    public void showBackTSP(){

        pair<Integer, List<Integer>> rslt = solveTSP();
        System.out.println(rslt.first);
        for(Integer i: rslt.second) System.out.print(i + " ");
        System.out.println();

    }



}
