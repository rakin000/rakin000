#include <bits/stdc++.h>
using namespace std ;


struct bit{
    int n;
    int *a;

    bit(int n){
        this->n=n+1;
        a = new int[this->n];
    }

    bit()
        :bit(8)
    {}

    ~bit(){
        delete [] a ;
    }

    void update(int id,int delta){
        for(++id; id<n; id += (id&-id))
            a[id] += (delta);
    }

    int get(int id){
        int res = 0 ;
        for(++id; id > 0; id -= (id&-id))
            res += a[id];
    }
};