package gui;

import logging.AccessLogger;
import access.AccessStrategy;
import access.VisitorAccess;
import access.EmployeeAccess;
import access.AdminAccess;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RoomSelectionPanel extends JFrame {
    private String floor;
    private String userType;
    private AccessLogger logger;

    public RoomSelectionPanel(String floor, String userType, AccessLogger logger) {
        this.floor = floor;
        this.userType = userType;
        this.logger = logger;

        setTitle(floor + " - Select Room");
        setSize(600, 400);
        setLayout(new GridLayout(3, 3));

        List<String> rooms = getRoomsForFloor(floor);
        for (String room : rooms) {
            JButton roomButton = new JButton(room);
            roomButton.addActionListener(e -> handleAccess(room));  // Handle access attempt
            add(roomButton);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> this.dispose());
        add(backButton);

        setVisible(true);
    }

    //upcasting
    private void handleAccess(String room) {
        AccessStrategy strategy;

        // Determine access strategy based on user type
        switch (userType) {
            case "Visitor":
                strategy = new VisitorAccess(logger);  // Pass the logger
                break;
            case "Employee":
                strategy = new EmployeeAccess(logger);  // Pass the logger
                break;
            default:
                strategy = new AdminAccess(); // Admin has access to all rooms
                break;
        }

        // Check if the time is valid
        if (!strategy.isAccessTimeValid()) {
            JOptionPane.showMessageDialog(this, "Access Denied: Invalid Access Time.",
                    "Access Status", JOptionPane.ERROR_MESSAGE);
            logger.update("Access Denied: " + room + " (Invalid Time)");  // Log the attempt
            return; // Stop further access checks
        }

        // Check if the user has access to the room
        boolean hasAccess = strategy.hasAccess(room);
        logger.update(hasAccess ? "Access Granted: " + room : "Access Denied: " + room);

        // Show the appropriate message to the user
        JOptionPane.showMessageDialog(this, hasAccess ? "Access Granted" : "Access Denied",
                "Access Status",
                hasAccess ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }


    private List<String> getRoomsForFloor(String floor) {
        return switch (floor) {
            case "High Floor" -> Arrays.asList("CEO OFFICE", "EXECUTIVE LOUNGE", "BOARDROOM", "FINANCE ROOM", "SERVER ROOM", "PRIVATE MEETING ROOM");
            case "Medium Floor" -> Arrays.asList("GENERAL MEETING ROOM 1", "GENERAL MEETING ROOM 2", "BREAK ROOM", "PROJECT WORKSPACE", "MANAGER OFFICE", "HR OFFICE");
            case "Low Floor" -> Arrays.asList("LOBBY", "RECEPTION", "MAIN CONFERENCE ROOM", "CAFETERIA", "VISITOR LOUNGE", "STORAGE ROOM");
            default -> List.of();
        };
    }
}
