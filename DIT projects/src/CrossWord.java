import java.util.Random;
import java.util.Stack;

/*
 * ASSIGNMENT: Search for a word in a 2D character grid
 * using RECURSION and BACKTRACKING.
 *
 * The program:
 *  - Uses recursive logic to explore each character in the grid.
 *  - Traces the call stack using print statements.
 *  - Applies backtracking to undo invalid paths.
 */
public class CrossWord {

    static final int SIZE = 10;

    static char[][] grid = new char[SIZE][SIZE];
    static boolean[][] visited = new boolean[SIZE][SIZE];
    static Stack<String> callStack = new Stack<>();
    static Random random = new Random();

    // STACK is placed first so it is already visible/given in the grid.
    static String[] words = { "STACK", "JAVA", "CODE", "TYPHOON" };

    static int[][] directions = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     CROSS WORD - RECURSION DEMO");
        System.out.println("======================================");

        generateGrid();
        displayGrid();

        for (String word : words) {
            System.out.println("\n======================================");
            System.out.println("Searching for: " + word);
            System.out.println("======================================");

            boolean found = findWord(word);

            System.out.println(found
                ? "\nWord '" + word + "' found in the grid."
                : "\nWord '" + word + "' NOT found in the grid.");
        }
    }

    /*
     * Builds an empty grid, places every word into it in a straight
     * line (so the puzzle is always solvable), then fills whatever
     * cells are still empty with random letters.
     */
    static void generateGrid() {

        for (char[] row : grid) {
            java.util.Arrays.fill(row, '\0');
        }

        for (String word : words) {
            placeWord(word);
        }

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c] == '\0') {
                    grid[r][c] = (char) ('A' + random.nextInt(26));
                }
            }
        }
    }

    /*
     * Picks a random starting cell and direction, and places the
     * word there if every cell along the way is either empty or
     * already holds the matching letter.
     */
    static void placeWord(String word) {

        while (true) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            int[] dir = directions[random.nextInt(directions.length)];

            int endRow = row + dir[0] * (word.length() - 1);
            int endCol = col + dir[1] * (word.length() - 1);

            if (endRow < 0 || endRow >= SIZE || endCol < 0 || endCol >= SIZE) {
                continue;
            }

            boolean possible = true;
            for (int i = 0; i < word.length(); i++) {
                char cell = grid[row + dir[0] * i][col + dir[1] * i];
                if (cell != '\0' && cell != word.charAt(i)) {
                    possible = false;
                    break;
                }
            }
            if (!possible) {
                continue;
            }

            for (int i = 0; i < word.length(); i++) {
                grid[row + dir[0] * i][col + dir[1] * i] = word.charAt(i);
            }

            System.out.println("Placed " + word + " at (" + (row + 1) + ", " + (col + 1) + ")");
            return;
        }
    }

    static void displayGrid() {
        System.out.println("\n10 x 10 GRID:");
        System.out.println("---------------------");
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }
     //Tries every cell as a possible starting point for the word.
    static boolean findWord(String word) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c] == word.charAt(0)) {

                    clearVisited();
                    callStack.clear();

                    System.out.println("\nStarting search at (" + (r + 1) + ", " + (c + 1) + ")");

                    if (search(r, c, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
     * RECURSIVE search.
     *
     * Each call tries to match one character of the word at (row, col).
     * If it matches, the cell is pushed onto the call stack and marked
     * visited, then the function recurses into all 8 neighboring cells
     * looking for the next character.
     *
     * BASE CASE: index reaches the last character -> word is found.
     *
     * BACKTRACKING: if none of the neighbors continue the word, the
     * current cell's match was a dead end. It gets un-visited and
     * popped off the call stack before returning false, so the search
     * can try a different path.
     */
    static boolean search(int row, int col, String word, int index) {

        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        if (grid[row][col] != word.charAt(index)) {
            return false;
        }

        String position = "(" + (row + 1) + ", " + (col + 1) + ")";

        callStack.push(word.charAt(index) + " at " + position);
        visited[row][col] = true;

        System.out.println("Searching '" + word.charAt(index) + "' at " + position);
        System.out.println("Call Stack: " + callStack);

        // BASE CASE: last character matched.
        if (index == word.length() - 1) {
            System.out.println("All characters matched for '" + word + "'!");
            return true;
        }

        for (int[] dir : directions) {
            if (search(row + dir[0], col + dir[1], word, index + 1)) {
                return true;
            }
        }

        // BACKTRACK: this path failed, undo the decision.
        visited[row][col] = false;
        callStack.pop();
        System.out.println("Backtracking from " + position);
        System.out.println("Call Stack after undo: " + callStack);

        return false;
    }

    static void clearVisited() {
        for (boolean[] row : visited) {
            java.util.Arrays.fill(row, false);
        }
    }
}