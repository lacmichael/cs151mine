package mineField;

import java.awt.*;
import javax.swing.*;
import mvc.*;

public class MineFieldPanel extends AppPanel{
    public MineFieldPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout(new GridLayout(4, 2, 15, 15));
        view.setPreferredSize(new Dimension(300,300));
        controlPanel.setPreferredSize(new Dimension(300, 300));
        frame.pack();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        for (String cmd : factory.getEditCommands()) {
            JButton b = new JButton(cmd);
            b.addActionListener(this);
            controlPanel.add(b);
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        MineFieldPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
    
}
