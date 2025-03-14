package mvc;

import java.util.ArrayList;

public class Publisher {
    private ArrayList<Subscriber> subscribers = new ArrayList<>();
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        if(subscribers != null) {
            subscribers.remove(s);
        }
    }

    public void notifySubscribers() {
        for(Subscriber sub : subscribers) {
            sub.update();
        }
    }
}
