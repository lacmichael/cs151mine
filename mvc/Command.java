package mvc;

public abstract class Command {
    private Model model;

    public Command(Model model) {
        this.model = model;
    }

    protected abstract void execute() throws Exception;
}
