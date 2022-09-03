import java.util.NoSuchElementException;
import java.util.Iterator; 


public class BinarySearchST<Key extends Comparable<Key>,Value>{
    private static final int INIT_SIZE = 8;
    private int end,begin;
    private Key keyArray[] ;
    private Value valArray[];
    private int N;
    public BinarySearchST(){
        keyArray = (Key[]) new Comparable[INIT_SIZE];
        valArray = (Value[]) new Comparable[INIT_SIZE];
        N = 0;
    }

    private void resize(int cap){
        assert cap > 0;
        Key [] tmpk = (Key[]) new Comparable[cap];
        Value[] tmpv = (Value[]) new Comparable[cap];
        for(int i=0;i<N;i++){
            tmpk[i] = keyArray[i];
            tmpv[i] = valArray[i];
        }
        keyArray = tmpk;
        valArray = tmpv;

    }
    private void swap(int i,int j){
        Key temp = keyArray[i];
        keyArray[i] = keyArray[j];
        keyArray[j] = temp;

        Value tmp = valArray[i];
        valArray[i] = valArray[j];
        valArray[j] = tmp;
    }
    public void put(Key key,Value value){
        if( key == null ) throw new IllegalArgumentException();

        if( value == null ){
            delete(key);
            return ;
        }

        int idx = rank(key);
        if(idx != -1){
            valArray[idx] = value;
            return ;
        }
        
        if(N==keyArray.length) resize(2*keyArray.length);
        
        keyArray[N] = key;
        valArray[N++] = value;

        int pos = N-1;
        while( pos > 0 && keyArray[pos].compareTo(keyArray[pos-1]) < 0){
            swap(pos,pos-1);
            pos--;
        }
    }

    public Value get(Key key){
        int idx = rank(key) ;
        return (idx == -1) ? null : valArray[idx];
    }
    private int rank(Key key){
        begin = 0;end = N-1;
        int mid;
        while(begin<=end){
            mid = begin + (end-begin) / 2;
            int cmp = key.compareTo(keyArray[mid]);
            if      (cmp < 0)   end = mid - 1;
            else if (cmp > 0)   begin = mid + 1;
            else return mid;
        }
        return -1 ;
    }
    public Key floor(Key key){
        int idx = rank(key);
        return (idx==-1) ? keyArray[end]: keyArray[idx];
    }
    public Key ceil(Key key){
        int idx = rank(key);
        return (idx==-1) ? keyArray[begin]: keyArray[idx];
    }
    public void delete(Key key){
        int idx = rank(key);
        if(idx == -1) return ;
        while(idx < N-1){
            keyArray[idx] = keyArray[idx+1];
            valArray[idx] = valArray[idx+1];
            ++idx;
        }
        --N;
        keyArray[N] = null;
        valArray[N] = null;
        if(N==keyArray.length/4) resize(keyArray.length/2) ;
    }

    public boolean contains(Key key){
        return rank(key) != -1 ;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public Iterable<Key> keys(){
        return new Keys();
    }

    private class Keys implements Iterable<Key>{
        private Key[] keyss;
        private int len;
        private Keys() {
            keyss = (Key[]) new Comparable[N] ;
            for(int i=0;i<N;i++)
                keyss[i] = keyArray[i];
            len = N;
        }

        public Iterator<Key> iterator(){
            return new It();
        }
        
        private class It implements Iterator<Key>{
            private int curr = 0;

            public boolean hasNext(){return curr != len ;}
            public void remove() { throw new UnsupportedOperationException(); }
            public Key next(){
                if(!hasNext()) throw new NoSuchElementException();
                return keyss[curr++] ;
            }
        }
    }


    public static void main(String args[]){
        BinarySearchST<String,Double> sta = new BinarySearchST<String,Double>();

        sta.put("D",1.00);
        sta.put("F",0.00);
        sta.put("C",3.00);
        sta.put("A+",5.00);
        sta.put("A+",4.33);
        sta.put("A",4.00);
        sta.put("Z",53.5);
        sta.put("B",2.5);
        sta.put("G",3.35);
        for(String s: sta.keys())
            System.out.print(s+" ");
        System.out.println();
        System.out.println(sta.floor("A"));
        System.out.println(sta.floor("G")) ;
        System.out.println(sta.floor("E")) ;
        System.out.println(sta.floor("A+"));
        System.out.println(sta.ceil("H"));
        sta.delete("A+") ;
        sta.delete("Z") ;
        for(String s: sta.keys())
            System.out.print(s+" ");
        System.out.println();
    }
}