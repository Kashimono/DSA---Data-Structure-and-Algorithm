class Node {
	int data;
	Node left, right;
	
	Node(int value) {
		data = value;
		left = right = null;
	}
}

public class BinaryTree {
	Node root;
	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(5);
		tree.root.right = new Node(15);
		
		System.out.println("Root: " + tree.root.data);
		System.out.println("Left Child: " + tree.root.left.data);
		System.out.println("Right Child: " + tree.root.right.data);
	}
}
