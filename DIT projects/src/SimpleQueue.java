public class SimpleQueue {
    int maxSize;
    int queueArray[];
    int front;
    int rear;
    int itemCount;

    // Constructor
    public SimpleQueue(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        itemCount = 0;
    }

    // Enqueue: Add item to the rear
    public void enqueue(int value) {
        if (itemCount < maxSize) {
            rear = (rear + 1) % maxSize;
            queueArray[rear] = value;
            itemCount++;
        } else {
            System.out.println("Queue is full. Cannot enqueue " + value);
        }
    }

    // Dequeue: Remove item from the front
    public int dequeue() {
        if (!isEmpty()) {
            int value = queueArray[front];
            front = (front + 1) % maxSize;
            itemCount--;
            return value;
        } else {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1; // Sentinel value
        }
    }

    // Peek: View the front item
    public int peek() {
        if (!isEmpty()) {
            return queueArray[front];
        } else {
            System.out.println("Queue is empty. Nothing to peek.");
            return -1;
        }
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return itemCount == 0;
    }

    // Main method for testing
    public static void main(String[] args) {
        SimpleQueue queue = new SimpleQueue(5);
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);
        System.out.println("Front item: " + queue.peek()); // should print something
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Front item: " + queue.peek()); // should print something
    }
}