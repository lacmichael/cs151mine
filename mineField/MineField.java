package mineField;

import mvc.Model;
import mvc.Utilities;

public class MineField extends Model {
    private static final int SIZE = 20; 
    private Tile[][] field; 
    private int playerRow, playerCol;
    private boolean end = false;

    public MineField() {
        field = new Tile[SIZE][SIZE];
        initField();
        generateMines();
        playerRow = 0;
        playerCol = 0;
    }

    public void initField() {
        field = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = new Tile();
            }
        }
        field[0][0].setVisited();
        field[SIZE-1][SIZE-1].setGoal();
    }

    private void generateMines() {
        int mineCount = 0;
        while (mineCount < 20) {
            int xRand = Utilities.rng.nextInt(SIZE-1);
            int yRand = Utilities.rng.nextInt(SIZE-1);
            if(!field[xRand][yRand].isBomb() && !(xRand == 0 && yRand == 0) && !(xRand == SIZE-1 && yRand == SIZE-1)) {
                field[xRand][yRand].setBomb();
                mineCount++;
                updateAdjacentBombCounts(xRand, yRand);
            }
        }
        field[SIZE - 1][SIZE - 1].setGoal();
    }

    private void updateAdjacentBombCounts(int row, int col) {
        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (isValid(newRow, newCol)) {
                field[newRow][newCol].incrementAdjBombs();
            }
        }
    }

    public int getNeighboringMines(int row, int col) {
        int count = 0;
        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (isValid(newRow, newCol) && field[newRow][newCol].isBomb()) {
                count++;
            }
        }
        return count;
    }

    public void uncoverTiles() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].setVisited();
            }
        }
    }

    public void movePlayer(int newRow, int newCol) throws Exception {
        if(end) {
            throw new InvalidMoveException("Game is over, can't move");
        }
        if (!isValid(newRow, newCol)) {
            throw new InvalidMoveException("Out of bounds move!");
        }
        if (field[newRow][newCol].isBomb()) {
            field[newRow][newCol].setVisited();
            uncoverTiles();
            end = true;
            changed();
            throw new MineException("Game Over! You stepped on a mine.");
        }
        if (field[newRow][newCol].isEnd()) {
            field[newRow][newCol].setVisited();
            uncoverTiles();
            end = true;
            changed();
            throw new EndException("Congratulations! You reached the goal.");
        }
        field[newRow][newCol].setVisited();
        playerRow = newRow;
        playerCol = newCol;
        changed(); 
    }

    public Tile[][] getField() {
        return field;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public int getSize() {
        return SIZE;
    }

    public boolean isEnd() {
        return end;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }
}
