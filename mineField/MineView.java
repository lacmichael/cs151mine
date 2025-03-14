package mineField;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import tools.Subscriber;

public class MineView extends JPanel implements Subscriber{
    private Mine mine;
    public MineView(Mine mine) {
        this.mine = mine;
        mine.subscribe(this);
        this.setPreferredSize(new Dimension(500, 625));
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(blackline);
        setBackground(Color.WHITE);
    }
    public void update() {
        repaint();
    }
    public void setMine(Mine newMine) {
        mine.unsubscribe(this);
        mine = newMine;
        mine.subscribe(this);
        repaint();
    }
}
