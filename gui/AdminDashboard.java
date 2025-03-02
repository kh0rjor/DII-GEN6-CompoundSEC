package gui;

import logging.AccessLogger;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private AccessLogger logger;

    public AdminDashboard(AccessLogger logger) {
        this.logger = logger;
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setLayout(new GridLayout(3, 1));

        // Buttons for managing visitor cards and viewing audit logs
        JButton manageVisitors = new JButton("Manage Visitor Cards");
        JButton viewAuditLogs = new JButton("View Audit Logs");

        manageVisitors.addActionListener(e -> openVisitorCardManager());
        viewAuditLogs.addActionListener(e -> openAuditLogViewer());

        add(manageVisitors);
        add(viewAuditLogs);

        setVisible(true);
    }

    // Open Visitor Card Manager
    private void openVisitorCardManager() {
        new VisitorCardManager(logger); // Pass logger to the VisitorCardManager
    }

    // Open Audit Log Viewer (For simplicity, assuming you already have it implemented)
    private void openAuditLogViewer() {
        new AuditLogViewer(logger);
    }

    public static void main(String[] args) {
        new AdminDashboard(new AccessLogger()); // Pass logger for audit log

    }
}