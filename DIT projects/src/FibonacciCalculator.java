public class FibonacciCalculator {

    // Recursive method to calculate Fibonacci
    public static int fibonacci(int n) {
        // Print the current call to the Fibonacci function
        System.out.println("fibonacci(" + n + ")");
        if (n == 0) {
            return 0;
        }