public class DoubleHashing {

    static final int SIZE = 13;
    static Integer[] table = new Integer[SIZE];

    static int h1(int k) {
        return k % SIZE;
    }

    static int h2(int k) {
        return 7 - (k % 7);                    
    }

    static void insert(int k) {
        int i = 0;
        int slot = (h1(k) + i * h2(k)) % SIZE;

        System.out.println("\nInsert " + k + "  (H1 = " + h1(k) + ", H2 = " + h2(k) + ")");

        while (table[slot] != null) {
            System.out.println("  i = " + i + ": slot " + slot
                    + " is occupied by " + table[slot] + ", try next");
            i++;

            if (i >= SIZE) {
                System.out.println("  Table is full, cannot insert " + k);
                return;
            }
            slot = (h1(k) + i * h2(k)) % SIZE;
        }

        table[slot] = k;
        System.out.println("  i = " + i + ": slot " + slot + " is empty -> placed " + k);
    }

    static void display() {
        System.out.println("\nFinal table:");
        for (int i = 0; i < SIZE; i++) {
            System.out.println("Slot " + i + ": " + (table[i] == null ? "-" : table[i]));
        }
    }

    public static void main(String[] args) {
        int[] keys = {5, 18, 31, 44, 57, 70, 83, 96};

        for (int k : keys) {
            insert(k);
        }

        display();
    }
}