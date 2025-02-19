package observer;

public interface AccessObserver {
    void notifyAccess(String visitorName, String room, boolean granted);
}
