package access;

import logging.AccessLogger;

import java.time.LocalTime;

public class EmployeeAccess implements AccessStrategy {
    private AccessLogger logger;

    public EmployeeAccess(AccessLogger logger) {
        this.logger = logger;
    }

    @Override

    public boolean hasAccess(String room) {
        switch (room) {
            case "CEO Office":
            case "Finance Room":
            case "Server Room":
                return false; // Restricted for Employees
            default:
                return true; // Access allowed for others
        }
    }

    @Override
    public boolean isAccessTimeValid() {
        LocalTime now = LocalTime.now();
        // Employees can only access between 8 AM and 6 PM
        boolean validTime = now.isAfter(LocalTime.of(8, 0)) && now.isBefore(LocalTime.of(18, 0));

        if (!validTime) {
            // Notify admin if access is attempted outside of allowed time
            logger.update("Access Attempted Outside Allowed Time (Employee)");
        }

        return validTime;
    }

}
