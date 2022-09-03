#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
    
const int m = 1e9+7 ;

ll binpow(ll a,ll b){
    if(b==0)
        return 1;
    ll res = binpow(a,b/2);
    if(b&1){
        return res*res*a ;
    }
    else 
        return res*res ;
}

ll binpow_my(ll a, ll b){
    if( b == 0) return 1ll;
    ll r = binpow_my(a,b/2ll);
    return (b&1) ? (((r*r)%m)*(a%m))%m : (r*r)%m ;
}

main(){
    ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while(t--){
        ll d;
        cin >> d;
        
        ll nodes = (binpow_my(2ll,d+1)-1+m)%m;
        ll edges = (binpow_my(2ll,d+1)-2+m)%m;
        ll q = (nodes*edges)%m ;
        q = binpow_my(q,m-2) ;
        ll p = edges;
        ll ex = 0ll;
        if(d > 2){
            int n=(d-1)/2 ;        
            ex = (3*8*(binpow_my(4ll,n)*((3*(n-1)+2)%m)+1)%m)%m ;
            if(d&1)
                ex = (ex-((9*n%m)*binpow_my(2ll,d+1))%m+m)%m;
            ex = (ex*binpow_my(9ll,m-2))%m; 
        }
        p=(p+ex)%m;

        cout << (p*q)%m << endl;
    }
}
/*probability = (1/2)*(total_number_of_edges*2/(ways_to_chose_two_nodes))

for nodes hihger than level 2
number of nodes increases following the series ---
    2^3+2^4+2*2^5+2*2^6+3*2^7+3*2^8+4*2^9+4*2^10+.........+........
    so 2^3(1+2*2^2+3*2^3+........)
       2^4(1+2*2^2+3*2^3+.........)
1+2x+3x2+4x3+5x4+.........+nx(n-1) = 
       */