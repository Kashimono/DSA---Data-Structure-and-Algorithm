import java.util.PriorityQueue;

public class HeapExample {
	public static void main(String[] args) {
		// java's PriorityQueue is a Min-heap by default
		// make this into a AVL rotation. It should show each rotation
		//AVLTree
		PriorityQueue<Integer> minHeap = new PriorityQueue();
		
		minHeap.add(20);
		minHeap.add(5);
		minHeap.add(15);
		
		System.out.println("Min element: " + minHeap.peek()); // 5
		System.out.println("Extract min: " + minHeap.poll()); // 5
		System.out.println("Next min: " + minHeap.peek());    // 15
	}

}
