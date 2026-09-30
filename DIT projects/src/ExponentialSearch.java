public class ExponentialSearch {
    public static int search(int[] arr, int target) {
        if (arr.length == 0 || arr[0] == target) return 0;

        int i = 1;
        while (i < arr.length && arr[i] <= target) {
            i *= 2;
        }

        return binarySearch(arr, Math.max(0, i / 2), Math.min(i, arr.length - 1), target);
    }

    
    private static int binarySearch(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2; 

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 7;
        int result = search(arr, target);
        System.out.println("Element found at index: " + result);
    }
}