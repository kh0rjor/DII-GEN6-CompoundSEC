package logging;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AccessLogger implements AccessObserver {
    private List<String> logs = new ArrayList<>();
    private String userName;
    private String userId;

    // Method to store user info (name and ID)
    public void setUserInfo(String name, String id) {
        this.userName = name;
        this.userId = id;
    }

    @Override
    public void update(String message) {
        // Log the access attempt with name and ID if it's denied
        String logMessage = message + " (User: " + userName + ", ID: " + userId + ")";
        logs.add(logMessage);
        System.out.println("Audit Log: " + logMessage);

        // Notify the admin for access denial or invalid time
        notifyAdmin(logMessage);
    }

    private void notifyAdmin(String message) {
        // Simple popup notification for the Admin
        JOptionPane.showMessageDialog(null, message,
                "Access Attempted Notification", JOptionPane.WARNING_MESSAGE);
    }

    public List<String> getLogs(boolean filterDenied) {
        if (filterDenied) {
            // Return only denied access logs
            return logs.stream().filter(log -> log.contains("Access Denied")).toList();
        }
        return logs;
    }
}
