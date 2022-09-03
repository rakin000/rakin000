import java.util.NoSuchElementException;

public class DynamicMedian<Key extends Comparable<Key> >{
    private MinPQ<Key> minpq;
    private MaxPQ<Key> maxpq;
    private Key med;
    private int size;
    public DynamicMedian(){
        minpq = new MinPQ<Key>();
        maxpq = new MaxPQ<Key>();
        size=0;
    }
    public boolean isEmpty(){ return size==0; }
    public Key median(){
        if(isEmpty()) throw new NoSuchElementException("median(): ");
        return med; 
    }

    public void insert(Key key){
        if(med ==null || key.compareTo(med) > 0){
            minpq.insert(key);
            if(minpq.size()-maxpq.size() > 1)
                maxpq.insert(minpq.delMin());
        }
        else {
            maxpq.insert(key);
            if(maxpq.size()-minpq.size() > 0)
                minpq.insert(maxpq.delMax());
        }
        med = minpq.min();
        size++;
        
    }
    
    public void remove(){
        if(isEmpty()) throw new NoSuchElementException("remove(): ");
        minpq.delMin();
        if(maxpq.size()-minpq.size() > 0)
            minpq.insert(maxpq.delMax());

        med = minpq.min();
        size--;
    }

    public static void main(String args[]){
        DynamicMedian<Integer> dm = new DynamicMedian<Integer>();
        dm.insert(1);
        dm.insert(2); 
        dm.insert(3);
        dm.insert(4);
        dm.insert(5);
        dm.insert(6); // 1 2 3 4 5 6
        System.out.println(dm.median());
        dm.remove(); // 1 2 3 5 6
        dm.insert(190); /// 1 2 3 5 6 190
        System.out.println(dm.median());
        dm.insert(20); // 1 2 3 5 6 20 190
        System.out.println(dm.median());
        dm.insert(39); // 1 2 3 5 6 20 39 190
        dm.insert(4); // 1 2 3 4 5 6 20 39 190
        System.out.println(dm.median());
    }

}