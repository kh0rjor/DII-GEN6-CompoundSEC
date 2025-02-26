package facade;

import logging.AccessLogger;
import java.util.Arrays;

public class VisitorFacade implements AccessFacade {
    private AccessLogger logger;

    public VisitorFacade(AccessLogger logger) {
        this.logger = logger;
    }

    @Override
    public void selectFloor(String floor) {
        System.out.println("Visitor selected floor: " + floor);
    }

    @Override
    public void accessRoom(String room) {
        boolean hasAccess = Arrays.asList("LOBBY", "RECEPTION", "MAIN CONFERENCE ROOM", "VISITOR LOUNGE").contains(room);
        logAccess(room, hasAccess);
    }

    private void logAccess(String room, boolean granted) {
        String logMessage = granted ? "Access Granted: " + room : "Access Denied: " + room;
        logger.update(logMessage);
        System.out.println(logMessage);
    }
}