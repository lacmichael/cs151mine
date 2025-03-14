package mineField;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import tools.Utilities;

public class AppPanel extends JPanel implements ActionListener{
    private Mine mine;
    private JPanel controls;
    private MineView view;
    
    public AppPanel() {
        mine = new Mine();
        view = new MineView(mine);
        controls = new JPanel();
        JPanel p = new JPanel();
        JButton nw = new JButton("NW");
        JButton n = new JButton("N");
        JButton ne = new JButton("NE");
        JButton w = new JButton("W");
        JButton e = new JButton("E");
        JButton sw = new JButton("SW");
        JButton s = new JButton("S");
        JButton se = new JButton("SE");
        controls.add(nw);
        nw.addActionListener(this);
        controls.add(n);
        n.addActionListener(this);
        controls.add(ne);
        ne.addActionListener(this);
        controls.add(w);
        w.addActionListener(this);
        controls.add(e);
        e.addActionListener(this);
        controls.add(sw);
        sw.addActionListener(this);
        controls.add(s);
        s.addActionListener(this);
        controls.add(se);
        se.addActionListener(this);
        controls.setLayout(new GridLayout(0, 2));
        controls.setPreferredSize(new Dimension(500, 625));
        this.setLayout(new BorderLayout());
        this.add(controls, BorderLayout.WEST);
        this.add(view, BorderLayout.CENTER);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Mine Field");
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        menu.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"NW", "N", "NE", "W", "E", "SW", "S", "SE"}, this);
        menu.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        menu.add(helpMenu);
        return menu;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch(cmmd) {
                
                case "New": {
                    mine = new Mine();
                    view.setMine(mine);
                    break;
                }
                
                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.mine);
                    os.close();
                    break;
                }

                case "Open": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        mine = (Mine) is.readObject();
                        view.setMine(mine);
                        is.close();
                    }

                    break;
                }
                
                case "NW": {
                }
                
                case "N": {
                }

                case "NE": {
                }

                case "W": {
                }

                case "E": {
                }

                case "SW": {
                }

                case "S": {
                }

                case "SE": {
                }

                
                case "Quit": {
                    System.exit(0);
                    break;
                }
                
                case "About": {
                    Utilities.inform("Michael Lac Turtle Graphics, 2025. All rights reserved");
                    break;
                }
                
                case "Help": {
                    String[] cmmds = new String[] {
                        "N: ",
                        "E: ",
                        "W: ",
                        "S: ",
                    };
                    Utilities.inform(cmmds);
                    break;
                }

            }
        } 
        catch (Exception ex) {
            Utilities.error(ex);
        }
       
    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }
    
}
