#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FOR(t,i,n) for(t i=(t)0;(i)<(n);(i)++)
template<class T>void printarray(T a,int n){
    FOR(int,i,n) cout << a[i] << " " ;
    cout << endl;
}
template<class A,class V>int binsearch(A a,int lo,int hi,V x){
    int mid=lo+(hi-lo)/2;
    if(lo<=hi){ 
        if(a[mid]>x) return binsearch(a,lo,mid-1,x);
        if(a[mid]<x) return binsearch(a,mid+1,hi,x);
        else return x;
    }
    return -1;
}


main(){
    ios_base::sync_with_stdio(false) ;
    int t;
    cin >> t;
    while(t--){
        unsigned int n,k;
        cin>>n>>k;
        int ans=k^(k>>1);
        cout << ans << endl;
    }
}
/*
1 0  0
1 1  1
2 0  0 
2 1  1
2 2  3 
2 3  2 

3 0  0 
3 1  1
3 2  3
3 3  2 
3 4  6
3 5  7
3 6  5
3 7  4 */