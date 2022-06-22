package okres_2016.q3;

import java.util.Arrays;
import java.util.Random;

/**
 * Program generates a sudoku board with random values
 */
public class Main {

    private static final int BOARD_SIZE = 9;
    private static int[][] board;

    public static void main(String[] args) {
        boolean generated = false;
        while (!generated) {
            board = new int[BOARD_SIZE][BOARD_SIZE];
            generated = generateSudokuBoard();
        }
        printBoard();
    }

    private static void printBoard() {
        System.out.println("---------------------------------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(" | " + board[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------------------------------------");
    }

    private static boolean generateSudokuBoard() {
        Random rng = new Random();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boolean isValid = false;
                int insert = 0;
                int numberOfTries = 0;
                while (!isValid) {
                    insert = rng.nextInt(10);
                    isValid = canBeInserted(i, j, insert);
                    numberOfTries++;
                    if (numberOfTries > 50) {
                        return false;
                    }
                }
                board[i][j] = insert;
            }
        }
        return true;
    }

    private static boolean canBeInserted(int i, int j, int insert) {
        for (int k = 0; k < BOARD_SIZE; k++) {
            if (board[k][j] == insert) return false;
        }
        for (int k = 0; k < BOARD_SIZE; k++) {
            if (board[i][k] == insert) return false;
        }
        return true;
    }

}
