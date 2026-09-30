public class LinearSearch {
    public static int search(int[] arr, int target) {
        int comparisons = 0; 
        for (int i = 0; i < arr.length; i++) {
            comparisons++; 
            if (arr[i] == target) {
                System.out.println("Number of comparisons made: " + comparisons);
                return i; 
            }
        }
        System.out.println("Number of comparisons made: " + comparisons);
        return -1; 
    }

    public static void main(String[] args) {
        int[] array = {10, 20, 80, 30, 60, 50, 110, 100, 130, 170};
        int target = 110;
        int result = search(array, target);

        if (result == -1) {
            System.out.println("Element not present in array");
        } else {
            System.out.println("Element found at index " + result);
        }
    }
}