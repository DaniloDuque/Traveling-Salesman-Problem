#include <bits/stdc++.h>
using namespace std;

int V, E, start, weight, vis;

#define vi vector<int>
#define vvi vector<vector<pair<int, int>>>
#define duo pair<int, vi>
#define TEST(msk, i) ((msk)&(1<<(i)))
#define SET(msk, i) (msk) = ((msk)|(1<<(i)))


vvi graph;






duo min(duo curr, duo newP){

    if(curr.first < newP.first) return curr;
    return newP;

}




duo findMin(int curr, int msk, vi path, int cost = 0, int cont = 0){

    (curr == start)? cont++: SET(msk, curr);
    path.push_back(curr);

    if(curr == start && cont == 2 && path.size() == V+1) return {cost, path};

    duo result = {INT_MAX, {}};
    for(pair<int, int> neigh: graph[curr])
        if(!TEST(msk, neigh.first)) 
            result = min(findMin(neigh.first, msk, path, cost + neigh.second, cont), result);

    return result;

}




duo backTSP(vvi &graph){

    duo minRoute = {INT_MAX, {}};
    for(int i = 0; i<V; i++){

        start = i;
        duo newSol = findMin(i, 0, {});
        if(newSol.first < minRoute.first) minRoute = newSol;

    }return minRoute;

}






int main(){

    int node1, node2;
    cin>>V>>E;
    graph.assign(V, {});
    for(int i = 0; i < E; i++){

        cin>>node1>>node2>>weight;
        graph[--node1].push_back({--node2, weight});
        graph[node2].push_back({node1, weight});


    }duo tsp = backTSP(graph);
    cout<<tsp.first<<'\n';
    for(int i: tsp.second) cout<<i+1<<" ";
    cout<<'\n';
    return 0;

}
