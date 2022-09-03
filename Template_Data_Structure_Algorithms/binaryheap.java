public class binaryheap<Key extends Comparable<Key>>{
    private Key arr[] ;
    private int n;

    public binaryheap(){
        arr = (Key[]) new Comparable[1];
        n=0;
    }
    public binaryheap(Key arr[]){
        this.arr = (Key[]) new Comparable[arr.length+1];
        n = arr.length;
        for(int i=1;i<=n;i++)
            this.arr[i] = arr[i-1];
        for(int k=n/2;k>0;k--){
            sink(k);
        }
    }
    public boolean isEmpty(){ return n==0; }
    private void swap(int a,int b){
        Key temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    private boolean less(Key a, Key b){
        return a.compareTo(b) < 0;
    }
    public void insert(Key key){
        if(n+1==arr.length){
            resize(2*arr.length);
        }
        arr[++n] = key;
        swim(n);
    }
    public Key get(){
        if(n<=0){
            System.out.println("Empty !!");
            return null;
        }
        Key val=arr[1];
        arr[1] = arr[n];
        arr[n] = null;
        n--;
        sink(1);
        return val;
    }

    private void swim(int k){
        while( k>1 && less(arr[k/2],arr[k]) ){
            swap(k,k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while(2*k <= n){
            int j=2*k;
            if(j < n && less(arr[j],arr[j+1]) ) ++j;
            if( !less(arr[k],arr[j]) ) break;
            swap(j,k);
            k=j;
        }
    }
    private void resize(int cap){
        Key temp[] = (Key[]) new Comparable[cap+1];
        temp[0] = null;
        for(int i=1;i<=n;i++)
            temp[i] = arr[i];
        arr=temp;
    }
    public void show(){
        for(int i=1;i<=n;i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
    public static void main(String args[]){
        binaryheap<Integer> bh= new binaryheap<Integer>(new Integer[]{45,13,25,12,15,62,13,67,126,17});
        bh.show();
    }
}