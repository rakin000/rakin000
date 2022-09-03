#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

class GraphAlgo{
    int *parent;
    int *depth;
    bool *vis ;
    int nodes;
    vector<int> *adj ;
    public:
    GraphAlgo(vector<int> *p, int n){ 
        adj = p;
        nodes = n;
        if(n!=0){
            parent = new int[nodes]{0};
            depth = new int[nodes]{0};
            vis = new bool[nodes]{0};
        }
    }
    int getNodes(){return nodes;}
    int getDepth(int i){return (i<0||i>=nodes)?0:*(depth+i);}
    int getParent(int i){return (i<0||i>=nodes)?0:*(parent+i);}
    bool getVis(int i){return (i<0||i>=nodes)?0:*(vis+i);}
    void cleardata(){
        for(int i=0;i<nodes;i++)
            parent[i] = depth[i] = vis[i] = 0;
    }
    ~GraphAlgo(){
        delete [] parent;
        delete [] depth;
        delete [] vis;
    }

    void bfs(int s,int p=-1,int d=0){
        cleardata();
        queue<int> q;
        q.push(s);
        parent[s] = p;
        depth[s] = d;
        vis[s] = true;
        while(!q.empty()){
            int top = q.front();
            q.pop();
            for(int u:adj[s]){
                if( !vis[u] ){
                    q.push(u);
                    parent[u] = s;
                    depth[u] = depth[s]+1;
                    vis[u] - true;
                }
            }
        }
        return;
    }


    void dfs_(int s,int p=-1,int d=0){
        parent[s] = p;
        depth[s] = d;
        vis[s] = true;
        for(int u:adj[s])
            if( !vis[u] )
                dfs_(u,s,d+1);
    }
    void dfs(int s,int p=-1,int d=0){
        cleardata();
        dfs_(s,p,d);
    }
};


void longest_path_demo(){
    int n;
    cin >> n;
    UnweightedGraph g(n);
    WeightedGraph g2(n) ; 
    int u,v,d;
    Graph_Algorithms algo(g2);
    do{
        cin >> u >> v >> d;
    }while( g2.addEdge(u,v,d) ) ;
    int source ;
    cout << "Source to start with: ";
    cin >> source;
    algo.dfs(g2,source);
    int max_dist = 0,idx,idx2;
    for(int i=0;i<n;i++){
        if( max_dist <= algo.getDepth()[i]){
            max_dist = algo.getDepth()[i] ;
            idx = i;
        }
    }
    algo.dfs(g2,idx) ;
    max_dist = 0;
    for(int i=0;i<n;i++){
        if( max_dist <= algo.getDepth()[i]){
            max_dist = algo.getDepth()[i] ;
            idx2 = i;
        }
    }
    cout << "Longest Path: ";
    cout << idx2  ;
    do{
        cout << "->" << (idx2 = algo.getParent()[idx2]) ;
        
    }while( algo.getParent()[idx2] != -1 );
    cout << endl;
    cout << "Length: " << max_dist << endl;
    return;
}

main(){
    longest_path_demo() ;
}