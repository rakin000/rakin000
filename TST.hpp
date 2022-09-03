
class TST{
    static const int DEFAULT_VALUE = -1;
    class node{
        public :
        char c;
        int val;
        node *left,*right,*mid;
        node(char c){
            this->c = c;
            this->val = DEFAULT_VALUE;
            left = nullptr;
            right = nullptr;
            mid = nullptr;
        }
        ~node(){
            delete left;
            delete mid ;
            delete right ;
        }
    };
    public: 
    int N = 0;
    node *root;

    node* put(node *x,std::string &s,int &val,int d){
        if( x == nullptr){
            x = new node(s[d]);
        }

        if(s[d] < x->c) x->left = put(x->left,s,val,d);
        else if(s[d] > x->c) x->right = put(x->right,s,val,d);
        else if( d < (int)s.length()-1 ) x->mid = put(x->mid,s,val,d+1);
        else{
            if(x->val == DEFAULT_VALUE) ++N;
            x->val = val;
        }
        return x;
    }
    
    node* get(node *x,std::string &s,int d){
        if(x == nullptr){
            return nullptr;
        }

        if( s[d] < x->c) return get(x->left,s,d);
        else if( s[d] > x->c ) return get(x->right,s,d);
        else if( d < (int)s.length()-1 ) return get(x->mid,s,d+1);
        else return x;
    }

    void collect(std::vector<std::string> &words, std::vector<int> &vals, node *x, std::string s){
        if( x == nullptr) return ;
        collect(words, vals, x->left, s);
        if( x->val != DEFAULT_VALUE ){
            words.push_back(s+x->c);
            vals.push_back(x->val);
        }
        collect(words, vals, x->mid, s+x->c);
        collect(words, vals, x->right,s);
        return ;        
    }

    void collect(std::vector<std::string> &words, std::vector<int> &vals, node *x, std::string s, int limit){
        if( (int)words.size() > limit ) return ;
        if( x == nullptr){
            return ;
        } 
        collect(words, vals, x->left, s, limit);
        if( x->val != DEFAULT_VALUE ){
            words.push_back(s+x->c);
            vals.push_back(x->val);
        }
        collect(words, vals, x->mid, s+x->c, limit);
        collect(words, vals, x->right,s, limit);
        return ;        
    }
    TST(){
        root = nullptr;
    }   
    ~TST(){
        delete root ;
    }

    void clear(){
        delete root ;
        root = nullptr;
    }
    void put(std::string s,int val){
        root = put(root,s,val,0);
    }
    

    int get(std::string s){
        node *x = get(root,s,0);
        if( x == nullptr) return DEFAULT_VALUE;
        return (x->val) ;
    }

    bool contains(std::string s){
        return get(root,s,0) != nullptr; 
    }
    int size(){
        return N;
    }
    void erase(std::string s){
        node *x = get(root,s,0);
        if(x->val == DEFAULT_VALUE){}
        else {
            --N;
            x->val = DEFAULT_VALUE;
        }

    }

    int operator[](std::string s){
        return get(s);
    }
    std::vector<std::string> prefixWith(std::string s){
        std::vector<std::string> sp;
        std::vector<int> vals;
        if( s.empty() ){
            collect(sp,vals,root,"");
            return sp ;
        }
        node *x = get(root,s,0);
        if( x == nullptr ) return sp;
        
        if( x->val != DEFAULT_VALUE ){
            sp.push_back(s);
            vals.push_back(x->val);
        }
        collect(sp, vals, x->mid, s);
        return sp;
    }
    std::vector<std::string> prefixWith(std::string s, int limit ){
        std::vector<std::string> sp;
        std::vector<int> vals;
        if( s.empty() ){
            collect(sp,vals,root,"",limit) ;
            return sp;
        }
        node *x = get(root,s,0); 
        if( x == nullptr ) return sp ;

        if( x->val != DEFAULT_VALUE ){
            sp.push_back(s);
            vals.push_back(x->val);
            limit--;
        }
        collect(sp, vals, x->mid, s, limit);
        return sp;
    }

    std::vector<std::string> getAll(){
        std::vector<std::string> sp;
        std::vector<int> vals;
        collect(sp, vals, root, "");
        return sp;
    }            
};
