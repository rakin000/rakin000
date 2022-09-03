#include <iostream>
using namespace std;

int main(){
    int n_f=35;
    int res[n_f];
    long long fact[n_f],n;
    fact[0] = 1LL;
    for(int i=1;i<n_f;i++)
        fact[i] = fact[i-1]*i;

    int T;
    cin>>T;
    for(int i=1;i<=T;i++){
        cin>>n;
        int j;
        for(j=n_f-1;j>=0;--j){
//           res[j] = n/fact[j];
//            n = n%fact[j];
             if( n-fact[j] >= 0LL){
                res[j] = 1;
                n = n-fact[j];
            }
            else                
                res[j] = 0;
        }

        for(int i=0;i<35;i++)
          cout<<res[i]<<" ";
        cout<<endl;
    }
}