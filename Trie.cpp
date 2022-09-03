#include <iostream>
using namespace std ;

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