package mvc;

import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;


public class Utilities {
    
    public static boolean confirm(String query) {
        int result = JOptionPane.showConfirmDialog(null,
                query, "choose one", JOptionPane.YES_NO_OPTION);
        return result == 0;
    }

    public static void inform(String info) {
        JOptionPane.showMessageDialog(null, info);
    }

    public static void inform(String[] info) {
        String helpString = "";
        for(int i = 0; i < info.length; i++) {
            helpString = helpString + "\n" + info[i];
        }
        inform(helpString);
    }

    public static String getFileName(String fName, Boolean open) {
        JFileChooser chooser = new JFileChooser();
        String result = null;
        if (fName != null) {
            // open chooser in directory of fName
            chooser.setCurrentDirectory(new File(fName));
        }
        if (open) {
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                result= chooser.getSelectedFile().getPath();
            }
        } else {
            int returnVal = chooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                result= chooser.getSelectedFile().getPath();
            }
        }
        return result;
    }
    
    public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
        JMenu menu = new JMenu(name);
        for(int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.addActionListener(handler);
            menu.add(item);
        }
        return menu;
    }
    
    public static void error(String gripe) {
        JOptionPane.showMessageDialog(null,
                gripe,
                "OOPS!",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void error(Exception gripe) {
        gripe.printStackTrace();
        JOptionPane.showMessageDialog(null,
                gripe.getMessage(),
                "OOPS!",
                JOptionPane.ERROR_MESSAGE);
    }

}
