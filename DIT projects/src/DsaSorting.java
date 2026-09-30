import java.util.Random;

public class DsaSorting {

    // main method
    public static void main(String[] args) {

        // Create randomized array from 1 to 100
        int[] numbers = createRandomizedArray();

        // randomized numbers
        System.out.println("\n");
        System.out.println("RANDOMIZED NUMBERS");
        System.out.println("------------------");
        
        printArray(numbers);


        
        // bubble sort
        int[] bubbleArray = copyArray(numbers);

        bubbleSort(bubbleArray);

        System.out.println("\n");
        System.out.println("BUBBLE SORT");
        System.out.println("------------------");
        printArray(bubbleArray);


        // selection sort
        int[] selectionArray = copyArray(numbers);

        selectionSort(selectionArray);

        System.out.println("\n");
        System.out.println("SELECTION SORT");
        System.out.println("------------------");
        printArray(selectionArray);


        // insertion sort
        int[] insertionArray = copyArray(numbers);

        insertionSort(insertionArray);

        System.out.println("\n");
        System.out.println("INSERTION SORT");
        System.out.println("------------------");
        printArray(insertionArray);


        // merge sort
        int[] mergeArray = copyArray(numbers);

        mergeSort(mergeArray, 0, mergeArray.length - 1);

        System.out.println("\n");
        System.out.println("MERGE SORT");
        System.out.println("------------------");
        printArray(mergeArray);


        // quick sort
        int[] quickArray = copyArray(numbers);

        quickSort(quickArray, 0, quickArray.length - 1);

        System.out.println("\n");
        System.out.println("QUICK SORT");
        System.out.println("------------------");
        printArray(quickArray);


        // heap sort
        int[] heapArray = copyArray(numbers);

        heapSort(heapArray);

        System.out.println("\n");
        System.out.println("HEAP SORT");
        System.out.println("------------------");
        printArray(heapArray);
    }


    // randomizer array
    public static int[] createRandomizedArray() {

        int[] array = new int[100];

        // inputs the 1 to 100 in the array
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        // randomize using fisher-yates shuffle
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);

            swap(array, i, j);
        }

        return array;
    }


    // copy array
    public static int[] copyArray(int[] original) {

        int[] copy = new int[original.length];

        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }


    // print array
    public static void printArray(int[] array) {

        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i] + " ");

            // put 5 numbers per line
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }


    // swap helper
    public static void swap(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // bubble sort
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

            // stop if no swapping happened
            if (!swapped) {
                break;
            }
        }
    }


    // selection sort
    public static void selectionSort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            // find smallest element
            for (int j = i + 1; j < n; j++) {

                if (array[j] < array[minIndex]) {

                    minIndex = j;
                }
            }

            // swap smallest element
            swap(array, i, minIndex);
        }
    }


    // insertion sort
    public static void insertionSort(int[] array) {

        int n = array.length;

        for (int i = 1; i < n; i++) {

            int key = array[i];

            int j = i - 1;

            // shift larger elements to the right
            while (j >= 0 && array[j] > key) {

                array[j + 1] = array[j];

                j--;
            }

            // Put key into correct position
            array[j + 1] = key;
        }
    }


    // merge sort
    public static void mergeSort(int[] array, int left, int right) {

        if (left < right) {

            int middle = left + (right - left) / 2;

            // sort left half
            mergeSort(array, left, middle);

            // sort right half
            mergeSort(array, middle + 1, right);

            // merge both halfs
            merge(array, left, middle, right);
        }
    }


    // MERGE HELPER
    public static void merge(int[] array, int left, int middle, int right) {

        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        // copy left half
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        // copy right half
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        // compare both arrays
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

        // copy remaining left elements
        while (i < leftSize) {

            array[k] = leftArray[i];

            i++;
            k++;
        }

        // copy remaining right elements
        while (j < rightSize) {

            array[k] = rightArray[j];

            j++;
            k++;
        }
    }


    // quick sort
    public static void quickSort(int[] array, int low, int high) {

        if (low < high) {

            // get pivot position
            int pivotIndex = partition(array, low, high);

            // sort left side
            quickSort(array, low, pivotIndex - 1);

            // sort right side
            quickSort(array, pivotIndex + 1, high);
        }
    }


    // PARTITION HELPER
    public static int partition(int[] array, int low, int high) {

        // last element is the pivot
        int pivot = array[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (array[j] <= pivot) {

                i++;

                swap(array, i, j);
            }
        }

        // put pivot in correct position
        swap(array, i + 1, high);

        return i + 1;
    }


    // heap sort
    public static void heapSort(int[] array) {

        int n = array.length;

        // build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {

            heapify(array, n, i);
        }

        // extract elements one by one
        for (int i = n - 1; i > 0; i--) {

            // move largest element to the end
            swap(array, 0, i);

            // heapify remaining elements
            heapify(array, i, 0);
        }
    }


    // HEAPIFY HELPER
    public static void heapify(int[] array, int n, int i) {

        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;


        // check left child
        if (left < n && array[left] > array[largest]) {

            largest = left;
        }


        // check right child
        if (right < n && array[right] > array[largest]) {

            largest = right;
        }


        // if largest is not the root
        if (largest != i) {

            swap(array, i, largest);

            //recursively heapify affected subtree
            heapify(array, n, largest);
        }
    }
}