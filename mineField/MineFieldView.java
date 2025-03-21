package mineField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import mvc.Model;
import mvc.View;

public class MineFieldView extends View implements Serializable{
    private int tileSize = 12;

    public MineFieldView(Model model) {
        super(model);
        initView(model);
        update();
    }

    public void initView(Model model) {
        MineField field = (MineField) model;
        int rows = field.getSize();
        int cols = field.getSize();
        setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
    }

    public void drawTile(Graphics g, Tile t, int row, int col, MineField f) {
        int x = col * tileSize;
        int y = row * tileSize;
        if(t.isVisited()) {
            g.setFont(new Font("Arial", Font.ITALIC, 8));
            g.setColor(Color.GRAY);
            g.fillRect(x, y, tileSize, tileSize);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, tileSize, tileSize);
            if (t.isBomb()) {
                g.setColor(Color.RED);
                g.drawString("b", x + tileSize/2, y + tileSize);
            }
            else {
                int bombCount = t.adjBombs();
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(bombCount), x + tileSize/2, y + tileSize);
            }
        }
        else {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, tileSize, tileSize);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, tileSize, tileSize);
            g.setColor(Color.BLACK);
            g.drawString("?", x + tileSize / 2, y + tileSize);
        }
        if (t.isEnd()) {
            g.setColor(Color.GREEN);
            g.drawRect(x, y, tileSize, tileSize);
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        MineField field = (MineField) model;
        int size = field.getSize();
        Tile[][] mineField = field.getField();
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                drawTile(graphics, mineField[i][j], i, j, field);
            }
        }

    }
}
