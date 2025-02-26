package facade;

import logging.AccessLogger;

public class AdminFacade implements AccessFacade {
    private AccessLogger logger;

    public AdminFacade(AccessLogger logger) {
        this.logger = logger;
    }

    @Override
    public void selectFloor(String floor) {
        System.out.println("Admin selected floor: " + floor);
    }

    @Override
    public void accessRoom(String room) {
        System.out.println("Admin has full access to " + room);
        logger.update("Admin Accessed: " + room);
    }
}