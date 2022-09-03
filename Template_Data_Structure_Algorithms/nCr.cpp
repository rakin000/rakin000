#include <bits/stdc++.h>

#define FOR(d_type,x,n) for( d_type x=(d_type) 0; (x) < (n); ++(x) )
#define FOR1(d_type,x,n) for( d_type x=(d_type) 1; (x) <= (n); ++(x) )
#define FOREVER while(1)
#define SWAP(a,b)  {  a=a^b;b=a^b;a=a^b;  }
typedef long long ll;
typedef unsigned long long ull;
typedef unsigned int uint;
const double acc=1e-9;

using namespace std;
uint C[51][51] ;
//Calculate nCr
uint _C(uint n,uint r){
    if( C[n][r] == 0)
        C[n][r] = _C(n-1,r) + _C(n-1,r-1) ;

    return C[n][r] ;
}

int main()
{
    FOR(int,i,51)
        FOR(int,j,51)
            if(i==j || j==0)
                C[i][j] = 1;
            else if(j==1)
                C[i][j] = i;
            else
                C[i][j] = 0;

    int n,r;
    cin>>n>>r;
    cout<<_C(n,r)<<endl;
}
