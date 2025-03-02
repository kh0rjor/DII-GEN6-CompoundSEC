package facade;

import logging.AccessLogger;

public class AccessFacadeFactory {
    public static AccessFacade getFacade(String userType, AccessLogger logger) {
        return switch (userType) {
            case "Visitor" -> new VisitorFacade(logger);
            case "Employee" -> new EmployeeFacade(logger);
            case "Admin" -> new AdminFacade(logger);
            default -> throw new IllegalArgumentException("Invalid user type");
        };
    }
}
