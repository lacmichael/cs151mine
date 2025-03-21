package mineField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import mvc.Model;
import mvc.View;

public class MineFieldView extends View {

    public MineFieldView(Model model) {
        super(model);
    }

    public void drawTile(Graphics g, Tile t, int row, int col, int tileSize) {
        int x = col * tileSize;
        int y = row * tileSize;
        if(t.isVisited()) {
            g.setFont(new Font("Arial", Font.BOLD, tileSize / 2));
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
            g.drawString("?", x + tileSize / 3, y + tileSize * 2/3);
        }
        if (t.isEnd()) {
            g.setColor(Color.GREEN);
            g.drawRect(x, y, tileSize, tileSize);
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        MineField field = (MineField) model;
        int rows = field.getSize();
        int cols = field.getSize();
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int tileW = panelWidth / cols;
        int tileH = panelHeight / rows;
        int tileSize = Math.min(tileW, tileH);


        Tile[][] mineField = field.getField();
        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                drawTile(graphics, mineField[row][col], row, col, tileSize);
            }
        }

    }
}
