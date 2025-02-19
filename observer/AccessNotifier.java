package observer;

import java.util.ArrayList;
import java.util.List;

public class AccessNotifier {
    private List<AccessObserver> observers = new ArrayList<>();

    public void addObserver(AccessObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String visitorName, String room, boolean granted) {
        for (AccessObserver observer : observers) {
            observer.notifyAccess(visitorName, room, granted);
        }
    }
}
