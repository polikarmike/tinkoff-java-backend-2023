package edu.hw1;

public class Task8 {

    private static final int BOARD_SIZE = 8;
    private static final int[][] KNIGHT_MOVES = {
        {-2, -1},
        {-2, 1},
        {-1, -2},
        {-1, 2},
        {1, -2},
        {1, 2},
        {2, -1},
        {2, 1}
    };

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (!isValidBoard(board)) {
            throw new IllegalArgumentException("Размер шахматной доски должен быть 8x8");
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == 1) {
                    for (int[] knightMove : KNIGHT_MOVES) {
                        int x = row + knightMove[0];
                        int y = col + knightMove[1];
                        if (x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE) {
                            if (board[x][y] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isValidBoard(int[][] board) {
        return board.length == BOARD_SIZE && board[0].length == BOARD_SIZE;
    }
}
