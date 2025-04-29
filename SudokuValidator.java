import java.util.*;

public class SudokuValidator {

    public static void main(String[] args) {
        // Step 1: Create and fill the Sudoku board
        int[][] board = new int[9][9];
        fillBoard(board);
        
        // Step 2: Print the board (for visual verification)
        printBoard(board);

        // Step 3: Check if the board is a valid Sudoku solution
        if (isValidSudoku(board)) {
            System.out.println("The Sudoku solution is valid!");
        } else {
            System.out.println("The Sudoku solution is invalid.");
        }
    }

   // Method to fill the board by filling random 3x3 subgrids
    public static void fillBoardWithSubgrids(int[][] board) {
        Random rand = new Random();
        
        // Fill each 3x3 subgrid
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                fillSubgrid(board, i, j, rand);
            }
        }
    }

    // Method to fill a 3x3 subgrid with random non-repeating numbers from 1 to 9
    public static void fillSubgrid(int[][] board, int rowStart, int colStart, Random rand) {
        List<Integer> numbers = new ArrayList<>();
        for (int num = 1; num <= 9; num++) {
            numbers.add(num);
        }
        Collections.shuffle(numbers, rand);  // Shuffle the numbers for randomness
        
        int idx = 0;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                board[i][j] = numbers.get(idx++);
            }
        }
    }

    // Method to fill the board with a valid sequence from 1 to 9 in each row
    public static void fillBoard(int[][] board) {
        Random rand = new Random();
        
        for (int i = 0; i < 9; i++) {
            List<Integer> numbers = new ArrayList<>();
            for (int num = 1; num <= 9; num++) {
                numbers.add(num);
            }
            // Shuffle the numbers to randomize
            Collections.shuffle(numbers);
            
            for (int j = 0; j < 9; j++) {
                board[i][j] = numbers.get(j);
            }
        }
    }

    // Method to print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if the Sudoku board is valid
    public static boolean isValidSudoku(int[][] board) {
        // Check each row
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i)) {
                return false;
            }
        }

        // Check each column
        for (int j = 0; j < 9; j++) {
            if (!isValidColumn(board, j)) {
                return false;
            }
        }

        // Check each 3x3 subgrid
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValidSubgrid(board, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Check if a row is valid (no repeats)
    public static boolean isValidRow(int[][] board, int row) {
        Set<Integer> seen = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            if (seen.contains(board[row][col])) {
                return false;
            }
            seen.add(board[row][col]);
        }
        return true;
    }

    // Check if a column is valid (no repeats)
    public static boolean isValidColumn(int[][] board, int col) {
        Set<Integer> seen = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            if (seen.contains(board[row][col])) {
                return false;
            }
            seen.add(board[row][col]);
        }
        return true;
    }

    // Check if a 3x3 subgrid is valid (no repeats)
    public static boolean isValidSubgrid(int[][] board, int rowStart, int colStart) {
        Set<Integer> seen = new HashSet<>();
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (seen.contains(board[i][j])) {
                    return false;
                }
                seen.add(board[i][j]);
            }
        }
        return true;
    }
}
