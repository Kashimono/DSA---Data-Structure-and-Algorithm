public class RegistrarVault {
    // 1. binary
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    // 2. linear
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    // 3. expo
    public static int exponentialSearch(int[] arr, int target) {
        
        if (arr[0] == target) {
            return 0;
        }
        int i = 1;
        
        while (i < arr.length && arr[i] <= target) {
            i = i * 2;
        }
        
        int left = i / 2;
        int right = Math.min(i, arr.length - 1);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    // 4. interpo
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high &&
               target >= arr[low] &&
               target <= arr[high]) {
            
            int pos = low + ((target - arr[low]) * (high - low))
                    / (arr[high] - arr[low]);
            if (arr[pos] == target) {
                return pos;
            }
            if (arr[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1;
    }
    // program
    public static void main(String[] args) {
        // lock1
        int[] lock1 = {
            101, 203, 305, 407, 509,
            611, 713, 815, 917
        };
        int target1 = 611;
        int index1 = binarySearch(lock1, target1);
        System.out.println("\nLOCK 1");
        System.out.println("Algorithm: Binary Search");
        System.out.println("Target ID: " + target1);
        System.out.println("Index: " + index1);
        // lock2
        int[] lock2 = {
            12, 23, 34, 45, 56,
            67, 78, 89, 90
        };
        int target2 = 56;
        int index2 = linearSearch(lock2, target2);
        System.out.println("\nLOCK 2");
        System.out.println("Algorithm: Linear Search");
        System.out.println("Target ID: " + target2);
        System.out.println("Index: " + index2);
        // lock3
        int[] lock3 = {
            5, 10, 20, 40, 80,
            160, 320, 640, 1280
        };
        int target3 = 320;
        int index3 = exponentialSearch(lock3, target3);
        System.out.println("\n LOCK 3");
        System.out.println("Algorithm: Exponential Search");
        System.out.println("Target ID: " + target3);
        System.out.println("Index: " + index3);
        // lock4
        int[] lock4 = {
            1000, 2000, 3000, 4000, 5000,
            6000, 7000, 8000, 9000
        };
        int target4 = 7000;
        int index4 = interpolationSearch(lock4, target4);
        System.out.println("\nLOCK 4");
        System.out.println("Algorithm: Interpolation Search");
        System.out.println("Target ID: " + target4);
        System.out.println("Index: " + index4);
        
        String vaultID = "" + target1 + index1 + target2 + index2 + target3 + index3 + target4 + index4;
        
        System.out.println();
        System.out.println("The complete vault ID is: " + vaultID);
        

    }
}