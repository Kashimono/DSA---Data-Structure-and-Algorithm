class AVLNode {
	int data, height;
	AVLNode left, right;
	AVLNode(int value) { data = value; height = 1;}
}

class AVLTree {
	AVLNode rightRotate(AVLNode y) {
		AVLNode x = y.left;
		AVLNode T2 = x.right;
		
		// Perform rotation
		x.right = y;
		y.left = T2;
		
		// Update heights/
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		
		return x; // new root
	}
	int height(AVLNode n) { return n == null ? 0 : n.height; }
}
