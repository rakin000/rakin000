#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

vector<bool> vis;
vector<int> parent;

void dfs(vector<int> grid[], int source){
    vis[source] = true ;
    for(int u: grid[source] ){
        if( !vis[u] ){
            dfs(grid,u);
            parent[u] = source;
        }
    }
    return;
}

main(){
    int n;
    cout << "Number of nodes: ";
    cin >> n;
    vector<int> grid[n+1];
    for(int i=0;i<=n;i++){
        vis.push_back(0);
        parent.push_back(0) ;
    }
    while(1){
        int a,b;
        cin >> a >> b;
        if(a > n || b > n || a <= 0 || b<=0)
            break;
        grid[a].push_back(b);
        grid[b].push_back(a);
    }
    cout << "Source node: "; int source ;
    cin >> source ;
    cout << "Before traversal = ";
    for(bool i:vis)
        cout << i << " " ;
    cout << endl << endl;

    dfs(grid,source);
    cout << "After traversal = ";
    for(bool i:vis)
        cout << i << " " ;
    cout << endl;

    cout << "Parent array: " ;
    for(int  i:parent)
        cout << i << " " ;
    cout << endl;
    return 0 ;
}