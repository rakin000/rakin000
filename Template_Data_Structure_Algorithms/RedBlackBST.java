public class RedBlackBST<Key extends Comparable<Key>, Value>{
	private Node root;
	private final boolean RED = true, BLACK = false ;
	private class Node {
		Key key;
		Value val;
		Node left, right;
		boolean color;

		public Node(Key key, Value val, boolean c){
			this.key = key;
			this.val = val;
			this.color = c;
		}:
	}

	public void insert(Key key, Value val){
		insert(root,key,val);
	}

	private Node put(Node h, Key key, Value val){
		if(h == null) return new Node(key,val,RED);
		int cmp = key.compareTo(h.key);
		if	(cmp > 0) 	h.right = put(h.right,key,val);
		else if	(cmp < 0)	h.left = put(h.left,key,val);
		else 			h.val = val;

		if	(isRed(h.right) && !isRed(h.left))	h = rotateLeft(h);
		else if	(isRed(h.left) && isRed(h.left.left)) 	h = rotateRight(h);
		else if	(isRed(h.left) && isRed(h.right))	flipcolors(h);

		return h;
	}

}		
