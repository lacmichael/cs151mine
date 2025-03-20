package mineField;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class MineFieldFactory implements AppFactory{
    
    public Model makeModel() {
        return new MineField();
    }

    public View makeView(Model model) {
        return new MineFieldView((MineField) model);
    }

    public String[] getEditCommands() {
        return new String[] { "N", "S", "W", "E",
        "NW", "NE", "SW", "SE" };
    }
    
    public Command makeEditCommand(Model model, String type, Object source) {
        Heading heading = null;
        switch (type) {
            case "N":
                heading = Heading.NORTH;
                break;
            case "S":
                heading = Heading.SOUTH;
                break;
            case "E":
                heading = Heading.EAST;
                break;
            case "W":
                heading = Heading.WEST;
                break;
            case "NE":
                heading = Heading.NORTHEAST;
                break;
            case "NW":
                heading = Heading.NORTHWEST;
                break;
            case "SE":
                heading = Heading.SOUTHEAST;
                break;
            case "SW":
                heading = Heading.SOUTHWEST;
                break;
        }
        if (heading != null) {
            return new MoveCommand(model, heading);
        }
        return null;
    }

    public String getTitle() {
        return "MineField";
    }

    public String[] getHelp() {
        return new String[] {
            "N: Move North",  
            "S: Move South",  
            "E: Move East",  
            "W: Move West",  
            "NE: Move Northeast",  
            "NW: Move Northwest",  
            "SE: Move Southeast",  
            "SW: Move Southwest"  
        };
    }

    public String about() {
        return "Minefield Game v1.0, By: Michael, Nolan, Jonah";
    }


}
