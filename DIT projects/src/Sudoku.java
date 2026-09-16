public class Sudoku {

	public static void main(String[] args) {
		int Size = 9;
		int[][] board = new int [Size][Size];

		// Getting random numbers using backtracking
		solveSudoku(board, 0, 0);
		
		// Display the board
		for(int row = 0; row < Size; row++) {
			for(int col = 0; col < Size; col++) {
				System.out.print(board[row][col] + " ");
			}
			return solveSudoku(board, row + 1, 0);
		}
		// Try random numbers from 1 to 9
		for(int attempt = 0; attempt < Size; attempt++) {
			int num = (int) (Math.random() * Size) + 1;
			
			// Check if the number already exists in the row
			boolean exists = false;
			for(int i = 0; i < Size; i++) {
				if(board[row][i] == num) {
					exists = true;
					break;
				}
			}
			if(exists) {
				continue;
			}
			// Check if the number already exists in the column
			for(int i = 0; i < Size; i++) {
				if(board[i][col] == num) {
					exists = true;
					break;
				}
			}
			if(exists) {
				continue;
			}
			// Check if the number already exists in the 3x3 box
			int startRow = (row / 3) * 3;
			int startCol = (col / 3) * 3;
			for(int i = startRow; i < startRow + 3; i++) {
				for(int j = startCol; j < startCol + 3; j++) {
					if(board[i][j] == num) {
						exists = true;
						break;
					}
				}
				if(exists) {
					break;
				}
			}
			if(exists) {
				continue;
			}
			// Put the number into the board
			board[row][col] = num;

			// Move to the next column
			if(solveSudoku(board, row, col + 1)) {
				return true;
			}
			// Backtrack if the number leads to a dead end
			board[row][col] = 0;
		}
		// No valid number was found
		return false;
	}

}