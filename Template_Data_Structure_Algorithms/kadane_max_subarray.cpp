#include <bits/stdc++.h>
using namespace std;
typedef long long int64;
typedef unsigned long long uint64;
template<class T> void ArrayIn(T *a, int n){
    for(int i=0;i<n;i++)
        std::cin>>a[i];
}
template<class T> void ArrayOut(T *a, int n){
    for(int i=0;i<n;i++)
        std::cout<<a[i]<<" ";
    std::cout<<endl;
}

int64 maxsubarray(vector<int> a){
    int64 m_ = 0, m_ending_here = 0;

    for(int x: a){
        m_ending_here += x;
        if(m_ending_here < 0) m_ending_here = 0;
        m_ = max(m_,m_ending_here);
    }
    return m_;
}
int main(){
    ios_base::sync_with_stdio(false);
    int t; cin>>t;
    for(int T=1;T<=t;T++){
        int n; cin>>n;
        int a[n];
        ArrayIn(a,n);
        int64 sum = 0;
        vector<int> d1,d2;
        for(int i=0,j=0,k=0;i<n;i+=2){
            sum+=a[i];
            if( i+1 < n) d1.push_back(a[i+1]-a[i]);
            if( i-1 >= 0) d2.push_back(a[i-1]-a[i]);
        }
       /* cout<<sum<<endl;
        for(int x: d1) cout<<x<<" "; cout<<endl;
        for(int x: d2) cout<<x<<" "; cout<<endl;
*/      cout<<sum+max(maxsubarray(d1),maxsubarray(d2)) << endl;
    }
}