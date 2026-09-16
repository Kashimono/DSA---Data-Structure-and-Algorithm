import java.util.Stack;

public class PalindromeChecker {
    public static boolean isPalindrome(String input) {
        // remove non-alphanumeric characters and convert to lowercase
        String cleanedInput = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        Stack<Character> stack = new Stack<>();

        // push characters from the cleaned input onto the stack
        for (char ch : cleanedInput.toCharArray()) {
            stack.push(ch);
        }

        // check if the palindrome is correct by popping characters from the stack
        for (int i = 0; i < cleanedInput.length(); i++) {
            if (stack.pop() != cleanedInput.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String testInput = "adsfasdfasd bsdfasdfa";
        if (isPalindrome(testInput)) {
            System.out.println(testInput + " is a palindrome.");
        } else {
            System.out.println(testInput + " is not a palindrome.");
        }
    }
}