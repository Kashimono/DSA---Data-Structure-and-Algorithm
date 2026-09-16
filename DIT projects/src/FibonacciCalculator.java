public class FibonacciCalculator {

    // Recursive method to calculate Fibonacci
    public static int fibonacci(int n) {
        // Print the current call to the Fibonacci function
        System.out.println("fibonacci(" + n + ")");
        if (n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            int result = fibonacci(n - 1) + fibonacci(n - 2);
            System.out.println("fibonacci(" + n + ") = fibonacci(" + (n - 1) + ") + fibonacci(" + (n - 2) + ") = " + result);
            return result;
        }
    }

    public static void main(String[] args) {
        int number = 2;
        System.out.println("Fibonacci of " + number + " is: " + fibonacci(number));
    }
}