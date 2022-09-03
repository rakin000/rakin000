#include <iostream>
using namespace std;
typedef long long ll;
const int m=1e9+9,N=2e5;
ll pp[N];
ll hash_[N];
ll hash_polynomial_rolling(string const &s){
    const int p=31;
    ll h=0;
    ll p_pow=pp[0]=1;
    int i=0;
    for(char c: s){
        h+=((c-'a'+1)*p_pow )%m;
        hash_[i]=h;
        p_pow=(p_pow*p)%m;
        pp[++i]=p_pow;
    }
    return h;
}

main(){
    string s;
    cin>>s;
    cout<<"hash_ value: " << hash_polynomial_rolling(s)<<endl;
    for(ll x:hash_){ 
        if(!x) break;
        cout<<x<<" ";
    }
    cout<<endl;
    int i;
    for(i=0;i<s.size()/2;i++){
        if( (hash_[i]*pp[s.size()-i-1])%m == (hash_[s.size()-1]-hash_[s.size()-i-2])%m) break;
    }
    cout<< i<<endl;
    ll compval=(hash_[i]*pp[s.size()-i-1])%m ;
    for(int j=1;j<s.size()-i-1;j++){
        if( ((hash_[j+i]-hash_[j-1])*pp[s.size()-i-1-j])%m==compval ) cout<<j<<" ";

    }
    cout<<endl;
}