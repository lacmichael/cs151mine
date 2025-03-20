package mineField;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import mvc.*;

public class MineFieldPanel extends AppPanel{
    public MineFieldPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout(new GridLayout(4, 2));

        for (String cmd : factory.getEditCommands()) {
            JPanel p = new JPanel();
            JButton b = new JButton(cmd);
            p.add(b);
            b.addActionListener(this);
            controlPanel.add(p);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            String command = actionEvent.getActionCommand();
            switch (command) {
                case "New":
                    if (Utilities.confirm("You have unsaved changes that will be lost, continue?")) {
                        setModel(factory.makeModel());
                        model.setUnsavedChanges(false);
                    }
                    break;
                case "Open":
                    Model newModel = Utilities.open(model);
                    if (newModel != null) {
                        setModel(newModel);
                        model.setUnsavedChanges(false);
                    }
                    break;
                case "Save":
                    Utilities.save(model, false);
                    break;
                case "Save As":
                    Utilities.save(model, true);
                    break;
                case "Quit":
                    if (Utilities.confirm("You have unsaved changes that will be lost, continue?"))
                        System.exit(0);
                    break;

                case "About":
                    Utilities.inform(factory.about());
                    break;
                case "Help":
                    Utilities.inform(factory.getHelp());
                    break;

                default:
                    if (model instanceof MineField && ((MineField) model).isEnd()) {
                        JOptionPane.showMessageDialog(this, "Game is over! Cannot move.", "Game Over",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Command editCommand = factory.makeEditCommand(model, command, actionEvent.getSource());
                    if (editCommand != null) {
                        editCommand.execute();
                    }
            }
        // } catch (MineHitException e) {
        //     JOptionPane.showMessageDialog(this, e.getMessage(), "Game Over", JOptionPane.ERROR_MESSAGE);
        // } catch (GoalReachedException e) {
        //     JOptionPane.showMessageDialog(this, e.getMessage(), "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        MineFieldPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
    
}
