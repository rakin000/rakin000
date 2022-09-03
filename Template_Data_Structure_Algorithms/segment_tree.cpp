#include <algorithm>

class segment_tree{
    int *t;
    int  n;
    void build(int arr[], int v, int tl, int tr){
        if(tl == tr)
            t[v] = arr[tl];
        else {
            int tm = tl+(tr-tl)/2;
            build(arr,v*2,tl,tm);
            build(arr,v*2+1,tm+1,tr);
            t[v] = t[v*2] + t[v*2+1] ;
        }
    }
    public:
    segment_tree(int arr[],int n){
        this->n = n;
        t = new int[4*n];
        build(arr,1,0,n-1);
    }
    ~segment_tree(){
        delete [] t;
    }
    int sum(int v,int tl, int tr, int l, int r){
        if(l>r) return 0;
        if(l == tl && r == tr)
            return t[v];
        int tm = tl+(tr-tl)/2;
        return sum(v*2,tl,tm,l,std::min(r,tm))+sum(v*2+1,tm+1,tr,std::max(l,tm+1),r);
    }
};
