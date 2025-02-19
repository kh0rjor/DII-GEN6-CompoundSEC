package visitor;

import access.AccessStrategy;
import access.Floor;
import access.Room;

public class AccessControl {
    private AccessStrategy accessStrategy;

    public AccessControl(AccessStrategy strategy) {
        this.accessStrategy = strategy;
    }

    public boolean grantAccess(VisitorCard card, Floor floor, String room) {
        return accessStrategy.hasAccess(card, floor, room);
    }

    public void setAccessStrategy(AccessStrategy strategy) {
        this.accessStrategy = strategy;
    }
}
