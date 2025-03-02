package access;

import logging.AccessLogger;

import java.time.LocalTime;

public class VisitorAccess implements AccessStrategy {
    private AccessLogger logger;

    public VisitorAccess(AccessLogger logger) {
        this.logger = logger;
    }

    @Override

    public boolean hasAccess(String room) {
        switch (room) {
            case "CEO Office":
            case "Finance Room":
            case "Server Room":
            case "Private Meeting Room":
                return false; // Restricted for Visitors
            case "Lobby":
            case "Reception":
            case "Main Conference Room":
            case "Cafeteria":
            case "Visitor Lounge":
                return true; // Allowed for Visitors
            default:
                return false;
        }
    }

    @Override
    public boolean isAccessTimeValid() {
        LocalTime now = LocalTime.now();
        // Visitors can only access between 9 AM and 5 PM
        boolean validTime = now.isAfter(LocalTime.of(9, 0)) && now.isBefore(LocalTime.of(17, 0));

        if (!validTime) {
            // Notify admin if access is attempted outside of allowed time
            logger.update("Access Attempted Outside Allowed Time (Visitor)");
        }

        return validTime;
    }

}