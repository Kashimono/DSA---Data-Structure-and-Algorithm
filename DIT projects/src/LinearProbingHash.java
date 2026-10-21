
public class LinearProbingHash {
    public static void main(String[] args) {
        int size = 13;
        int[] hashTable = new int[size];

        // -1 empty slots
        java.util.Arrays.fill(hashTable, -1);

        int[] keys = {5, 18, 31, 44, 57, 70, 83, 96};

        for (int key : keys) {
            int index = key % size;

            // find the next empty slot
            while (hashTable[index] != -1) {
                index = (index + 1) % size;
            }

            hashTable[index] = key;

            System.out.println(
                "Key " + key + " inserted at index " + index
            );
        }

        System.out.println("\nFinal Hash Table:");

        for (int i = 0; i < size; i++) {
        	if (hashTable[i] == -1) {
        		System.out.println("Tower " + (i + 1) + " - Index " + i + " = Empty");
        	} else {
        		System.out.println(
        				"Tower " + (i + 1) + " - Index " + i + " = " + hashTable[i]
        );
    }
}
	
    }
}
