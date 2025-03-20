package mineField;

import mvc.Command;
import mvc.Model;

public class MoveCommand extends Command {
    private Heading heading;

    public MoveCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    @Override
    public void execute() {
        Field field = (Field) model;
        int currentRow = field.getPlayerRow();
        int currentCol = field.getPlayerCol();

        int newRow = currentRow + heading.getRowChange();
        int newCol = currentCol + heading.getColChange();
    }
}
