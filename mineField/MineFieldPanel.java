package mineField;

import java.awt.*;
import java.awt.event.ActionEvent;
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
                    Command editCommand = factory.makeEditCommand(model, command, actionEvent.getSource());
                    if (editCommand != null) {
                        editCommand.execute();
                    }
            }
        } catch (MineException e) {
            Utilities.error(e.getMessage());
        } catch (EndException e) {
            Utilities.error(e.getMessage());
        } catch (InvalidMoveException e) {
            Utilities.error(e.getMessage());
        }
        catch (Exception e) {
            Utilities.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        MineFieldPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
    
}
