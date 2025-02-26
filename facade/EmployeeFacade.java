package facade;

import logging.AccessLogger;
import java.util.Arrays;

public class EmployeeFacade implements AccessFacade {
    private AccessLogger logger;

    public EmployeeFacade(AccessLogger logger) {
        this.logger = logger;
    }

    @Override
    public void selectFloor(String floor) {
        System.out.println("Employee selected floor: " + floor);
    }

    @Override
    public void accessRoom(String room) {
        boolean hasAccess = !Arrays.asList("CEO OFFICE", "FINANCE ROOM", "SERVER ROOM").contains(room);
        logAccess(room, hasAccess);
    }

    private void logAccess(String room, boolean granted) {
        String logMessage = granted ? "Access Granted: " + room : "Access Denied: " + room;
        logger.update(logMessage);
        System.out.println(logMessage);
    }
}