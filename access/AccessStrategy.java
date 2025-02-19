package access;

public interface AccessStrategy {
    boolean hasAccess(Floor floor, String room);
}
