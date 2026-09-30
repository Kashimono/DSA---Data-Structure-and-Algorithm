import java.util.Random;

public class DsaSorting {

    // ==========================================
    // MAIN METHOD
    // ==========================================
    public static void main(String[] args) {

        // Create randomized array from 1 to 100
        int[] numbers = createRandomizedArray();

        // Display original randomized numbers
        System.out.println("========================================");
        System.out.println("ORIGINAL RANDOMIZED NUMBERS");
        System.out.println("========================================");
        printArray(numbers);


        // ==========================================
        // BUBBLE SORT
        // ==========================================
        int[] bubbleArray = copyArray(numbers);

        bubbleSort(bubbleArray);

        System.out.println("\n========================================");
        System.out.println("BUBBLE SORT");
        System.out.println("========================================");
        printArray(bubbleArray);


        // ==========================================
        // SELECTION SORT
        // ==========================================
        int[] selectionArray = copyArray(numbers);

        selectionSort(selectionArray);

        System.out.println("\n========================================");
        System.out.println("SELECTION SORT");
        System.out.println("========================================");
        printArray(selectionArray);


        // ==========================================
        // INSERTION SORT
        // ==========================================
        int[] insertionArray = copyArray(numbers);

        insertionSort(insertionArray);

        System.out.println("\n========================================");
        System.out.println("INSERTION SORT");
        System.out.println("========================================");
        printArray(insertionArray);


        // ==========================================
        // MERGE SORT
        // ==========================================
        int[] mergeArray = copyArray(numbers);

        mergeSort(mergeArray, 0, mergeArray.length - 1);

        System.out.println("\n========================================");
        System.out.println("MERGE SORT");
        System.out.println("========================================");
        printArray(mergeArray);


        // ==========================================
        // QUICK SORT
        // ==========================================
        int[] quickArray = copyArray(numbers);

        quickSort(quickArray, 0, quickArray.length - 1);

        System.out.println("\n========================================");
        System.out.println("QUICK SORT");
        System.out.println("========================================");
        printArray(quickArray);


        // ==========================================
        // HEAP SORT
        // ==========================================
        int[] heapArray = copyArray(numbers);

        heapSort(heapArray);

        System.out.println("\n========================================");
        System.out.println("HEAP SORT");
        System.out.println("========================================");
        printArray(heapArray);
    }


    // ==========================================
    // CREATE RANDOMIZED ARRAY
    // ==========================================
    public static int[] createRandomizedArray() {

        int[] array = new int[100];

        // Put numbers 1 to 100 into the array
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        // Randomize using Fisher-Yates Shuffle
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);

            swap(array, i, j);
        }

        return array;
    }


    // ==========================================
    // COPY ARRAY
    // ==========================================
    public static int[] copyArray(int[] original) {

        int[] copy = new int[original.length];

        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }


    // ==========================================
    // PRINT ARRAY
    // ==========================================
    public static void printArray(int[] array) {

        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i] + " ");

            // Put 10 numbers per line
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }


    // ==========================================
    // SWAP HELPER
    // ==========================================
    public static void swap(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // ==========================================
    // 1. BUBBLE SORT
    // ==========================================
    public static void bubbleSort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {

                if (array[j] > array[j + 1]) {

                    swap(array, j, j + 1);

                    swapped = true;
                }
            }

            // Stop if no swapping happened
            if (!swapped) {
                break;
            }
        }
    }


    // ==========================================
    // 2. SELECTION SORT
    // ==========================================
    public static void selectionSort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            // Find smallest element
            for (int j = i + 1; j < n; j++) {

                if (array[j] < array[minIndex]) {

                    minIndex = j;
                }
            }

            // Swap smallest element
            swap(array, i, minIndex);
        }
    }


    // ==========================================
    // 3. INSERTION SORT
    // ==========================================
    public static void insertionSort(int[] array) {

        int n = array.length;

        for (int i = 1; i < n; i++) {

            int key = array[i];

            int j = i - 1;

            // Shift larger elements to the right
            while (j >= 0 && array[j] > key) {

                array[j + 1] = array[j];

                j--;
            }

            // Put key into correct position
            array[j + 1] = key;
        }
    }


    // ==========================================
    // 4. MERGE SORT
    // ==========================================
    public static void mergeSort(int[] array, int left, int right) {

        if (left < right) {

            int middle = left + (right - left) / 2;

            // Sort left half
            mergeSort(array, left, middle);

            // Sort right half
            mergeSort(array, middle + 1, right);

            // Merge both halves
            merge(array, left, middle, right);
        }
    }


    // MERGE HELPER
    public static void merge(int[] array, int left, int middle, int right) {

        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        // Copy left half
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        // Copy right half
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        // Compare both arrays
        while (i < leftSize && j < rightSize) {

            if (leftArray[i] <= rightArray[j]) {

                array[k] = leftArray[i];
                i++;

            } else {

                array[k] = rightArray[j];
                j++;
            }

            k++;
        }

        // Copy remaining left elements
        while (i < leftSize) {

            array[k] = leftArray[i];

            i++;
            k++;
        }

        // Copy remaining right elements
        while (j < rightSize) {

            array[k] = rightArray[j];

            j++;
            k++;
        }
    }


    // ==========================================
    // 5. QUICK SORT
    // ==========================================
    public static void quickSort(int[] array, int low, int high) {

        if (low < high) {

            // Get pivot position
            int pivotIndex = partition(array, low, high);

            // Sort left side
            quickSort(array, low, pivotIndex - 1);

            // Sort right side
            quickSort(array, pivotIndex + 1, high);
        }
    }


    // PARTITION HELPER
    public static int partition(int[] array, int low, int high) {

        // Last element is the pivot
        int pivot = array[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (array[j] <= pivot) {

                i++;

                swap(array, i, j);
            }
        }

        // Put pivot in correct position
        swap(array, i + 1, high);

        return i + 1;
    }


    // ==========================================
    // 6. HEAP SORT
    // ==========================================
    public static void heapSort(int[] array) {

        int n = array.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {

            heapify(array, n, i);
        }

        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {

            // Move largest element to the end
            swap(array, 0, i);

            // Heapify remaining elements
            heapify(array, i, 0);
        }
    }


    // HEAPIFY HELPER
    public static void heapify(int[] array, int n, int i) {

        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;


        // Check left child
        if (left < n && array[left] > array[largest]) {

            largest = left;
        }


        // Check right child
        if (right < n && array[right] > array[largest]) {

            largest = right;
        }


        // If largest is not the root
        if (largest != i) {

            swap(array, i, largest);

            // Recursively heapify affected subtree
            heapify(array, n, largest);
        }
    }
}