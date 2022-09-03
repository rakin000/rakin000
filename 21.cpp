#pragma GCC target("popcnt")
#include <bits/stdc++.h>
// #include "Trie.cpp"
using namespace std;

#define pb push_back
#define rsz resize
#define all(x) begin(x), end(x)
#define sz(x) (int)(x).size()
#define fi first
#define se second
#define mp make_pair

int64_t pow_(int64_t a, int64_t b, int64_t M = -1){
    if (a == 0 || a == 1)
        return a % M;
    if (b == 0)
        return 1 % M;
    if (b == 1)
        return a % M;

    int64_t res = pow_(a, b >> 1ll, M);
    res = (res * res) % M;
    return (b & 1ll) ? ((a % M) * res % M) : res;
}

struct Trie{
    static const int ALPHABET_SIZE = 26;
    struct node{
        struct node *children[ALPHABET_SIZE];
        bool is_end;

        node () {
            this->is_end = false ;
            for(int i=0;i<ALPHABET_SIZE;i++)
                this->children[i] = nullptr ;
        }
    };
    node *root ;

    Trie(){
        root = new node();
    }
    void insert(struct node *root, string key){
        struct node *head = root;

        for(int i = 0; i < key.length(); i++){
            int index = key[i] - 'a';
            if (!head->children[index])
                head->children[index] = new node();
            head = head->children[index];
        }
        head->is_end = true;
    }
    bool search(struct node *root, string key){
        struct node *head = root;

        for(int i = 0; i < key.length(); i++){
            int index = key[i] - 'a';
            if (!head->children[index])
                return false;
            head = head->children[index];
        }

        return (head->is_end);
    }
};


void solve() {
    int n;
    cin>>n;
    vector<string> v(n), triv_ans(n);
    for(int i=0;i<n;i++){
        cin>>v[i];
        if( i == 0 ) triv_ans[i] = v[i];
        else triv_ans[i] = min(triv_ans[i-1],v[i]);
    }
    
    int q; 
    cin>>q;
    struct qr {
        int id;
        int r;
        string p ;
    } ;
    vector<qr> qry(q);
    vector<string> ans(q) ;
    // cout<<"came here\n";
    for(int i=0;i<q;i++){
        cin>>qry[i].r>>qry[i].p;
        qry[i].id = i ;
    }

    sort(all(qry), [] (qr &a,qr &b){
        return a.r <= b.r ;
    });


    // TST tst;
    Trie trie ;
    int l = 0;
    for(int i=0;i<qry.size();i++){
        while( l<qry[i].r ){
            // tst.put(v[l++],3);
            trie.insert(trie.root,v[l++]);
        }

        Trie::node *x = trie.root ;
        string ans_ = "";
        for(int j=0;j<qry[i].p.size();j++){
            int id = qry[i].p[j]-'a' ;
            if( x->children[id] == nullptr ){
                break ;
            }
            ans_ += char('a'+id) ;
            x = x->children[id] ;
        }

        while( !x->is_end ){

            for(int j=0;j<26;j++){
                if( x->children[j] != nullptr ){
                    ans_ += char(j+'a');
                    x = x->children[j] ;
                    break ;
                }
            }
        }
        ans[qry[i].id] = ans_;
    } 

    for(int i=0;i<q;i++){
        cout<<ans[i]<<endl;
    }
}
int main()
{
    ios_base::sync_with_stdio(false);
    int t;
    // init();
    t = 1;
    // cin >> t;
    while (t--)
    {
        solve();
    }
}