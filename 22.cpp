#include <bits/stdc++.h>
#pragma GCC target("popcnt")

#include "unionfind.hpp"
using namespace std;

#define pb push_back
#define rsz resize
#define all(x) begin(x), end(x) 
#define sz(x) (int) (x).size()
#define fi first
#define se second
#define mp make_pair 

int64_t pow_(int64_t a, int64_t b, int64_t M=-1){
    if( a == 0 || a == 1) return a%M;
    if( b == 0 ) return 1%M;
    if( b == 1 ) return a%M;

    int64_t res = pow_(a,b>>1ll,M);
    res = (res*res)%M;
    return (b&1ll) ? ((a%M)*res%M) : res ; 
}


void init(){
    
}
int n,k;

void solve(){
    cin>>n>>k; 
    unionfind uf(n+1);

    for(int i=2;i*i<=n;i++){
        for(int j=i+i;j<n;j+=i){
            uf.connect(i,j);
        }
    }

    
}


int main(){
    ios_base::sync_with_stdio(false);
    int t;
    init();
    t=1;
    // cin>>t;
    while(t--){
        solve();
    }
}