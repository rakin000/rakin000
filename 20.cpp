#include <bits/stdc++.h>
#pragma GCC target("popcnt")
using namespace std;

#define pb push_back
#define rsz resize
#define all(x) begin(x), end(x)
#define sz(x) (int)(x).size()
#define fi first
#define se second
#define mp make_pair

int64_t pow_(int64_t a, int64_t b, int64_t M = -1)
{
    if (a == 0 || a == 1)
        return a % M;
    if (b == 0)
        return 1 % M;
    if (b == 1)
        return a % M;

    int64_t res = pow_(a, b >> 1ll, M);
    res = (res * res) % M;
    return (b & 1ll) ? ((a % M) * res % M) : res;
}

void init() {}


void solve(){
    int n;
    cin>>n;
    vector<int> v(n);
    for(int i=0;i<n;i++) cin>>v[i];

    vector<pair<pair<int,int>,int>> intvl;
    for(int i=0;i<n;i++){
        // if( !v[i] ) continue ;
        if( !v[i] )
            intvl.pb({{i+2,n},i+1});
        else intvl.pb({{(i+1)/(v[i]+1)+1,(i+1)/v[i]},i+1});
    }

    set<int> marked ;
    for(int i=1;i<=n;i++) marked.insert(i);

    sort(all(intvl) );
    // for(auto p: intvl){
    //     cout<<p.fi.fi<<" "<<p.fi.se<<endl;
    // }

    vector<int> ans(n+1);
    int i = 1;
    for(auto &p: intvl){
        auto it = marked.lower_bound(p.fi.fi);
        ans[p.se] = *it ;
        marked.erase(it) ;
    }

    for(int i=1;i<=n;i++) cout<<ans[i]<<" "; cout<<endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    int t;
    init();
    t = 1;
    cin >> t;
    while (t--)
    {
        solve();
    }
}