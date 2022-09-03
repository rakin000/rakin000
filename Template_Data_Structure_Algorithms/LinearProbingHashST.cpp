#include <iostream>
using namespace std;

template<class Key, class Value> class LinearProbingHashST{
    const int CAP = 8;
    Key *key;
    Value *val;
    int size;
    int n;
    public:
    LinearProbingHashST(int capacity = CAP){
        n = 0;
        size = capacity;
        val = new Value[capacity];
        key = new Key[capacity];
    }
    ~LinearProbingHashST(){
        n = 0;
        delete [] val;
        delete [] key;
    }

    void insert(Key key, Value val){}

    void delete(Key key){}
    bool contains( Key key){}
    void resize(int capacity){
        assert(capacity > 0 && capacity > n);

    }
};