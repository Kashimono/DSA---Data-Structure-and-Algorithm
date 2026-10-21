class BST {
	Node root;
	
	void insert(int key) {
		root = insertRec(root, key);
	}
	
	Node insertRec(Node root, int key) {
		if (root == null) return new Node(key);
		if (key < root.data) root.left = insertRec(root.left, key);
		
		else if (key > root.data) root.right = insertRec(root.right, key);
		return root;
	}
	
	boolean search(Node root, int key) {
		if (root == null) return false;
		if (root.data == key) return true;
		return key < root.data ? search(root.left, key) : search(root.right, key); 
	}
	
	public static void main(String[] args) {
		BST tree = new BST();
		tree.insert(50);
		tree.insert(30);
		tree.insert(70);
		
		System.out.println("Search 30: " + tree.search(tree.root, 30));
		System.out.println("Search 90: " + tree.search(tree.root, 90));
	}
}
