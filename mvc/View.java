package mvc;

import javax.swing.JPanel;

public class View extends JPanel implements Subscriber{
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    public void update() {
        repaint();
    }

    public void setModel(Model model) {
        this.model.unsubscribe(this);
        this.model = model;
        this.model.subscribe(this);
        update();
    }
}
