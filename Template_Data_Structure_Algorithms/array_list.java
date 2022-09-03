import java.util.NoSuchElementException;

public class array_list<Item>{
    static final int INITIAL_CAPACITY = 4;
    Item [] array;
    int N;
    int capacity = INITIAL_CAPACITY;
  
    public array_list(int capacity){
        if( capacity <= 0 )
            throw new IllegalArgumentException("constructor(capacity): invalid value "+capacity);
        N = 0;
        this.capacity = capacity ;
        array = (Item[])new Object[capacity];       
    }
    public array_list(){
        this(INITIAL_CAPACITY);
    }
    public array_list(Item A[]){
        capacity = A.length;
        array = (Item[])new Object[cap]; 
        N = 0;

        for(int i=0;i<capacity;i++){
            array[N++] = A[i];
        }
    }

    private void resize(int new_size) {
        if( new_size < N ) throw new IllegalArgumentException();

        Item [] temp = (Item[]) new Object[new_size];

        for(int i=0;i<N;i++)
            temp[i] = array[i];
        array = temp;
        capacity = new_size;
    }
    public int length(){
        return N;
    }
    public boolean empty(){
        return (N == 0);
    }

    public Item get(int i) throws NoSuchElementException {
        if( i < 0 || i >= sz )
            throw new NoSuchElementException("Invalid index");
        return a[i];
    }
    public Item get_last() throws NoSuchElementException {
        if( empty() ) throw new NoSuchElementException("empty arraylist");
        return a[N-1];
    }
    public void add(Item e){
        if( N == cap )
            resize(cap*2);
        
        a[N++] = e;
    }

    public void add(int i,Item e){
        add(e);

        for(int j=N-1;j>i;j--){
            Item temp = array[j];
            array[j] = array[j-1];
            array[j-1] = temp;
        }
    }

    public Item pop_back(){
        if( empty() ) throw new NoSuchElementException("Empty arraylist!!");

        Item t = array[--N];

        if( capacity/2 > INITIAL_CAPACITY && N <= capacity/4 ){
            capacity = (capacity/2>0)?capacity/2:INITIAL_CAPACITY;
            resize(capacity);
        }

        return t;
    }

    public array_list<Integer> findIndex(Item e){
        array_list<Integer> id = new array_list<>();

        for(int i=0;i<sz;i++)
            if( a[i].equals(e) )
                id.add(i);
        return id;
    }
   
}