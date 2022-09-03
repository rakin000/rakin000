#include <bits/stdc++.h>
using namespace std;



vector<vector<int>> adj, adj_r ;
vector<int> topological, scc;
vector<bool> vis;

void dfs_toposort(int s){
    vis[s] = true;
    for(int v: adj[s] ){
        if( !vis[v] )
            dfs_toposort(v);
    }

    topological.push_back(s);
}

void dfs_scc(int s){
    vis[s] = true;
    scc.push_back(s);

    for(int v: adj_r[s] ){
        if( !vis[v] )
            dfs_scc(v);
    }
}


int main(){
    int n;
    cin>>n;

    adj.resize(n+1);
    adj_r.resize(n+1);

    int m;
    cin>>m;
    for(int i=0;i<m;i++){
        int u,v;
        cin>>u>>v;
        adj[u].push_back(v);
        adj_r[v].push_back(u);
    }

    vis.assign(n+1, false);

    for(int u=1;u<=n;u++){
        if( !vis[u] )
            dfs_toposort(u);
    }

    vis.assign(n+1,false);
    reverse(topological.begin(),topological.end());
    

    for(int v: topological ){
        if( !vis[v] ){
            dfs_scc(v);
            for(int u: scc)
                cout<<u<<" "; cout<<endl;
            scc.clear();
        }
    }
}