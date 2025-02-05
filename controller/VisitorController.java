package controller;

import model.User;
import model.AccessControl;
import model.AuditLog;

public class VisitorController {
    public boolean requestAccess(User user, String floor) {
        boolean granted = AccessControl.hasAccess(user, floor);
        AuditLog.log(user.getName() + " tried to access " + floor + ": " + (granted ? "GRANTED" : "DENIED"));
        return granted;
    }

}

