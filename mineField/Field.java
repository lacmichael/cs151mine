package mineField;

import mvc.Model;
import java.util.Random;

public class Field extends Model {
    private static final int SIZE = 20; 
    private static final int PERCENT_MINED = 5; 
    private boolean[][] mines; 
    private int playerRow, playerCol;

    public Field() {
        mines = new boolean[SIZE][SIZE];
        generateMines();
        playerRow = 0;
        playerCol = 0;
    }

    private void generateMines() {
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mines[i][j] = rand.nextInt(100) < PERCENT_MINED;
            }
        }
        mines[0][0] = false;
        mines[SIZE - 1][SIZE - 1] = false;
    }

    public boolean isMine(int row, int col) {
        return mines[row][col];
    }

    public int getNeighboringMines(int row, int col) {
        int count = 0;
        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (isValid(newRow, newCol) && mines[newRow][newCol]) {
                count++;
            }
        }
        return count;
    }

    public void movePlayer(int newRow, int newCol) throws Exception {
        if (!isValid(newRow, newCol)) {
            throw new Exception("Out of bounds move!");
        }
        if (mines[newRow][newCol]) {
            throw new Exception("Game Over! You stepped on a mine.");
        }
        if (newRow == SIZE - 1 && newCol == SIZE - 1) {
            throw new Exception("Congratulations! You reached the goal.");
        }

        playerRow = newRow;
        playerCol = newCol;
        changed(); 
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }
}
