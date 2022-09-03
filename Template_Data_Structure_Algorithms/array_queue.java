public class array_queue<Type>{
    static final int CAPACITY = 4;
    int _front;
    int _back;
    int cap ;
    int N ;
    Type a[];

    public array_queue(int cap){
        this.cap = cap;
        N = 0;
        a = (Type[])new Object[cap];
        _front = 0;
        _back = 0;
    }
    public array_queue(){
        this(CAPACITY);
    }
    public int size(){
        return N;
    }
    public boolean empty(){
        return (N==0);
    }
    public void push(Type value){
        if( size() == cap ){
            int nCap = 2*cap;
            Type temp[] = (Type[])new Object[nCap];
            int i=0;
            temp[i++] = a[_front]; // when full, front == back... 
            _front = (_front+1)%cap;
            while( _front != _back ){
                temp[i++] = a[_front];
                _front=(_front+1)%cap;
            }

            a = temp;
            cap = nCap;
            _front = 0;
            _back = i;
        }

        a[_back] = value;
        _back = (_back+1)%cap;
        ++N;
    }
    Type pop() throws Exception {
        if( empty() ) throw new Exception("empty queue");

        if( size() > CAPACITY && size() == cap/4 ){            
            int nCap = (cap/2 > CAPACITY) ? cap/2: CAPACITY;
            Type temp[] = (Type[])new Object[nCap] ;
            int i=0;
            while( _front != _back ){
                temp[i++] = a[_front];
                _front = (_front+1)%cap;
            }

            // delete [] a;
            a = temp;
            cap = nCap;
            _front = 0;
            _back = i;
        }

        Type t = a[_front];
        _front = (_front+1)%cap;
        --N;
        return t;
    }
    public Type front() throws Exception {
        //cout<<_front<<endl;
        if( empty() ) throw new Exception("empty queue");
        return a[_front];
    }
    public Type back() throws Exception {
        //cout<<_back<<endl;
        if( empty() ) throw new Exception("empty queue");
        return a[_back] ;
    }
}