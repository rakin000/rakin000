import java.util.NoSuchElementException;
import java.util.Iterator; 


public class STArray<Key extends Comparable<Key>,Value>{
    private static final int INIT_SIZE = 8;
    
    private Key keyArray[] ;
    private Value valArray[];
    private int N;
    public STArray(){
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

    public void put(Key key,Value value){
        if(N==keyArray.length) resize(2*keyArray.length);
        keyArray[N] = key;
        valArray[N++] = value;
    }

    public Value get(Key key){
        int idx = _get(key) ;
        return (idx == -1) ? null : valArray[idx];
    }
    private int _get(Key key){
        for(int i=0;i<N;i++)
            if(key.compareTo(keyArray[i]) == 0) return i;
        return -1;
    }
    public void delete(Key key){
        int idx = _get(key);
        if(idx == -1) return ;
        keyArray[idx] = keyArray[--N];
        valArray[idx] = valArray[N] ;
        if(N==keyArray.length/4) resize(keyArray.length/2) ;
    }

    public boolean contains(Key key){
        return _get(key) != -1 ;
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
        STArray<String,Double> sta = new STArray<String,Double>();

        sta.put("A+",4.33);
        sta.put("D",1.00);
        sta.put("A",4.00);
        sta.put("F",0.00);

        for(String s: sta.keys())
            System.out.print(s+" ");
        System.out.println();

        sta.delete("A+") ;

        for(String s: sta.keys())
            System.out.print(s+" ");
        System.out.println();
    }
}