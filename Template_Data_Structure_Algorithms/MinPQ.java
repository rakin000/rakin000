import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key>{
    private Key pq[];
    private int n;
    private Comparator<Key> comparator = null;
    public MinPQ(int length){
        pq = (Key[]) new Comparable[length+1];
        n=0;
    }
    public MinPQ(){
        this(1);
    }
    public MinPQ(int length, Comparator<Key> comp){
        pq = (Key[]) new Comparable[length+1];
        n=0;
        comparator = comp;
    }
    public MinPQ(Key a[]){
        if(a==null || a.length==0) throw new ArrayIndexOutOfBoundsException("MinPQ(): ");
        n = a.length;
        pq = (Key[]) new Comparable[n+1];
        for(int i=1;i<=n;i++)
            pq[i] = a[i-1];
        for(int i=n/2;i>0;i--)
            sink(i);
    }
    public boolean greater(Key a[], int i, int j){
        if (comparator == null)
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        else 
            return comparator.compare(pq[i], pq[j]) > 0;
    }
    private void swap(Key a[], int i, int j){ Key t=a[i]; a[i]=a[j]; a[j]=t; }
    public boolean isEmpty(){ return n==0; }
    public int size(){return n; }
    private void swim(int k){
        while(k > 1 && greater(pq,k/2,k) ){
            swap(pq,k/2,k);
            k = k/2;
        }
    }
    private void sink(int k){
        while(2*k <= n){
            int j=2*k;
            if(j < n && greater(pq,j,j+1) ) j++;
            if( !greater(pq,k,j) ) break;
            swap(pq,k,j);
            k=j;
        }
    }
    private void resize(int cap){
        Key temp[] = (Key[]) new Comparable[cap+1];
        temp[0] = null;
        for(int i=1;i<=n;i++)
            temp[i] = pq[i];
        pq=temp;
    }
    public Key min(){
        if(n==0) throw  new NoSuchElementException("min(): ");
        return pq[1];
    }
    public void show(){
        for(int i=1;i<=n;i++)
            System.out.print(pq[i]+" ");
        System.out.println();
    }

    public void insert(Key a){
        if(n+1==pq.length) resize(2*pq.length);
        pq[++n] = a;
        swim(n);
    }
    public Key delMin(){
        if(n <= 0) throw new NoSuchElementException("get(): ");
        Key key = pq[1];
        pq[1] = pq[n];
        pq[n--] = null;
        sink(1);
        if(n > 0 && n+1==pq.length/4) resize(pq.length/2);
        return key;
    }

    public Iterator<Key> iterator(){
        return new HeapIterator();
    }

     private class HeapIterator implements Iterator<Key> {
        private MinPQ<Key> copy;
        public HeapIterator() {
            if (comparator == null) copy = new MinPQ<Key>(size());
            else                    copy = new MinPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }
        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
    public static void main(String args[]){
        MinPQ<Integer> bh= new MinPQ<Integer>(new Integer[]{45,13,25,12,15,62,13,67,126,17});
        bh.show();
        for(Integer x : bh){
            System.out.print(x+" ");
        }
    }
}