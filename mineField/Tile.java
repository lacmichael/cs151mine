package mineField;

public class Tile {
    private boolean visited;
    private boolean bomb;
    private int adjBombs;
    private boolean end;

    public Tile(){
        visited = false;
        bomb = false;
        adjBombs = 0;
        end = false;
    }

    public boolean isBomb() { return bomb; }
    public boolean isVisited() { return visited; }
    public boolean isEnd() { return end; }
    public int adjBombs() { return adjBombs; }
    public void setBomb() { bomb = true; }
    public void setVisited() { visited = true; }
    public void setGoal() { end = true; }
    public void incrementAdjBombs() { adjBombs++; } 

}
