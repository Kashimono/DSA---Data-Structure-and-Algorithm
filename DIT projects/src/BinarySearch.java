public class BinarySearch {
    public static int search(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int callCounter = 0;
        return binarySearchRecursive(arr, target, low, high, callCounter);
    }

    private static int binarySearchRecursive(int[] arr, int target, int low, int high, int callCounter) {
        callCounter++;
        System.out.println("Recursive call " + callCounter + " with low=" + low + ", high=" + high);

        if (low > high) {
            return -1; 
        }

        int mid = (low + high) / 2;
        if (arr[mid] == target) {
            return mid; 
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, high, callCounter);
        } else {
            return binarySearchRecursive(arr, target, low, mid - 1, callCounter);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 9;
        int result = search(arr, target);
        if (result != -1) {
            System.out.println("Element found at index " + result);
        } else {
            System.out.println("Element not found in the array");
        }
    }
}