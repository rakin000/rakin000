import java.util.*;

public class BST<Key extends Comparable<Key>,Value>{
    private Node root;
    
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int count;
        public Node(Key key, Value val, int count){
            this.key = key;
            this.val = val;
            this.count = count;
        }

    }
    public boolean isEmpty(){   
        return root == null ;
    }
    public void put(Key key, Value val){
        root = put(root,key,val);
    }

    public Node put(Node x,Key key,Value val){
        if(x == null) return new Node(key,val,1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)  x.left = put(x.left, key, val);
        else if(cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.count = 1 + size(x.left) + size(x.right) ;
        return x;
    }

    public Value get(Key key){
        Node x = root ;
        while(x!=null){
            int cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if (cmp > 0)   x = x.right;
            else return x.val;
        }
        return null;
    }

    public Key floor(Key key){
        Node x = floor(root,key);
        if(x == null) return null;
        return x.key;
    }

    private Node floor(Node x,Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);

        if(cmp == 0) return x;
        if(cmp < 0 ) return floor(x.left, key);

        Node t = floor(x.right, key);
        if(  t != null) return t;
        else return x;
    }

    public Key ceil(Key key){
        Node x = ceil(root,key);
        return (x==null) ? null : x.key;
    }

    private Node ceil(Node x, Key key){
        if(x==null) return null;
        int cmp = key.compareTo(x.key);

        if  (cmp == 0) return x;
        if  (cmp > 0 ) return ceil(x.right, key);

        Node t = ceil(x.left,key);
        return (t==null) ? x:t;
    }

    public int size(){
        return size(root) ;
    }
    private int size(Node x){
        if(x==null) return 0;
        return x.count ;
    }

    public int rank(Key key){
        return rank(root,key);
    }
    private int rank(Node x, Key key){
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if  (cmp < 0)   return rank(x.left,key);
        if  (cmp > 0)   return rank(x.right,key);
        else return size(x.left) ;
    }


    public void delete(Key key){ 
        if( key == null ) throw new IllegalArgumentException();
        root = delete(root,key);
        
    }

    public Node delete(Node x, Key key){// hibbard deletion 
        if( x == null ) return null;
        
        int cmp = key.compareTo(x.key);
        if      (cmp > 0)   x.right = delete(x.right,key);
        else if (cmp < 0)   x.left =  delete(x.left,key);
        else{
            if  (x.right == null)   return x.left;
            if  (x.left == null)    return x.right;  
            
            Node t = x ;
            x = min(t.right) ;
            x.right = deleteMin(t.right) ;
            x.left = t.left;
        }
        x.count = 1 + size(x.left) + size(x.right) ;
        return x ;
    }


    public void deleteMin(){
        root = deleteMin(root) ;
    }

    private Node deleteMin(Node x){
        if(x.left == null ) return x.right;
        x.left = deleteMin(x.left) ;
        x.count = 1 + size(x.left) + size(x.right) ;
        return x;
    }

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }
    private Node min(Node x){
        if( x.left == null ) return x;
        else                return min(x.left) ;
    }
    public Iterable<Key> iterator(){
        Queue<Key> q = new ArrayDeque<Key>();
        inorder(root,q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q){
        if(x == null) return ;
        inorder(x.left,q);
        q.add(x.key) ;
        inorder(x.right,q);
    }

    public static void main(String args[]){
        BST<String,Double> sta = new BST<String,Double>();

        sta.put("D",1.00);
        sta.put("F",0.00);
        sta.put("C",3.00);
        sta.put("A+",5.00);
        sta.put("A+",4.33);
        sta.put("A",4.00);
        sta.put("Z",53.5);
        sta.put("B",2.5);
        sta.put("G",3.35);
        for(String s: sta.iterator())
            System.out.print(s+" ");
        System.out.println();
        System.out.println(sta.floor("A"));
        System.out.println(sta.floor("G")) ;
        System.out.println(sta.floor("E")) ;
        System.out.println(sta.floor("A+"));
        System.out.println(sta.ceil("H"));
        sta.delete("A+") ;
        sta.delete("Z") ;
        for(String s: sta.iterator())
            System.out.print(s+" ");
        System.out.println();
    }
}