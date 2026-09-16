public class SimpleStack {
    int maxSize;
    int stackArray[];
    int top;

    // Constructor
    public SimpleStack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    // Push
    public void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value;
        } else {
            System.out.println("Stack is full. Cannot push " + value);
        }
    }

    // Pop
    public int pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        } else {
            System.out.println("Stack is empty. Cannot pop.");
            return -1;
        }
    }

    // Peek
    public int peek() {
        if (!isEmpty()) {
            return stackArray[top];
        } else {
            System.out.println("Stack is empty. Nothing to peek.");
            return -1;
        }
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Print stack
    public void printStack() {
        System.out.print("Stack: ");

        for (int i = 0; i <= top; i++) {
            System.out.print(stackArray[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        SimpleStack stack = new SimpleStack(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top item: " + stack.peek());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Top item: " + stack.peek());

        stack.push(30);
        stack.push(40);
        stack.push(50);

        stack.printStack();
    }
}