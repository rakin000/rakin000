#include <vector>
using namespace std;

template<class Value> class SegmentTree{
    vector<Value> st, A;
    int n;
    int left(int p) { return p<<1; }
    int right(int p) { return (p<<1)+1; }

    void build(int p,int L, int R){
        if( L == R) st[p] = L;
        else{
            build(left(p), L, (L+R)/2);
            build(right(p), (L+R)/2+1, R);

            int p1 = st[left(p)], p2 = st[right(p)];
            st[p] = (A[p1] <= A[p2]) ? p1 : p2;
        }
    } 

    public:
    SegmentTree(const vector<Value> &_A){
        A =_A;
        n = A.size();
        st.assign(4*n, Value() );
        build(1,0,n-1);
    }    

    int rmq(int p, int L, int R, int i, int j){
        if( i > R || j < L ) return -1;
        if( L >= i && R <= j ) return st[p];

        int p1 = rmq(left(p), L, (L+R)/2, i, j);
        int p2 = rmq(right(p), (L+R)/2+1, R, i, j);

        if( p1 == -1 ) return p2;
        if( p2 == -1 ) return p1;

        return (A[p1] <= A[p2] ) ? p1 : p2;
    }

};




class SegTree{
    int *segt; // 0 , 1 , -1 for zero, positive or negative 
    int *arr;
    int len;

    void build(int pos, int *arr, int left, int right){   
        if( left > right ) return;

        if( left == right ){
            if(arr[left] > 0) segt[pos] = 1;
            else if( arr[left] < 0 ) segt[pos] = -1;
            else segt[pos] = 0;            
        }
        else {
            build( pos<<1, arr, left, (left+right)/2 );
            build( 1 + (pos<<1), arr, (left+right)/2+1, right);

            segt[pos] = segt[pos<<1]*segt[1+(pos<<1)] ;

            if( segt[pos] > 0 ) segt[pos] = 1;
            else if( segt[pos] < 0 ) segt[pos] = -1; 
        }
    }
    public:
    SegTree(int *arr, int n){
        segt = new int[4*n] ;
        this->arr = arr;
        len = n;

        build(1,arr,0,n-1);
    }

    SegTree(const SegTree& r):
        SegTree(r.arr,r.len)
    {}

    ~SegTree(){
        delete [] segt;
    }
    void set(int *arr, int n){
        this->arr=arr;
        len = n;
        build(1,arr,0,n-1);
    }

    int get(int pos, int l, int r, int left, int right ){
        if( right < l || left > r  ) return 1;

        if( l > r ) return 1;

        if( left >= l && right <= r ) 
            return segt[pos];
        

        int ll = get( pos<<1, l, r, left, (left+right)/2 );
        int rr = get( 1 + (pos<<1), l, r, (left+right)/2+1, right);

        return ll*rr;    
    }

    void change(int pos, int posArr, int val, int left, int right){
        if( posArr < left || posArr > right) return ;

        if( left == right ){
            arr[posArr] = val ;

            if( val > 0 ) segt[pos] = 1;
            else if( val < 0 ) segt[pos] = -1;
            else segt[pos] = 0;

            return ;
        }
        change( pos<<1, posArr, val, left, (left+right)/2 );
        change(1 + (pos<<1) , posArr, val, (left+right)/2+1, right);

        segt[pos] = segt[pos<<1] * segt[1 + (pos<<1) ] ;
        
        return ;
    }

    void print(){
        aOut(segt,2*len);
    }

};
