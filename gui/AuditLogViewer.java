package gui;

import logging.AccessLogger;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AuditLogViewer extends JFrame {
    private AccessLogger logger;
    private JCheckBox filterDeniedAccessCheckBox;
    private JCheckBox filterVisitorCardCheckBox; // New checkbox for filtering Visitor Card Created logs

    public AuditLogViewer(AccessLogger logger) {
        this.logger = logger;
        setTitle("View Audit Logs");
        setSize(500, 400);
        setLayout(new GridLayout(4, 1));  // Updated layout to accommodate new checkbox

        // Button to view logs
        JButton viewLogsButton = new JButton("View Logs");

        // Checkbox for filtering denied access logs
        filterDeniedAccessCheckBox = new JCheckBox("View only denied access logs");
        filterDeniedAccessCheckBox.setSelected(false);

        // Checkbox for filtering visitor card created logs
        filterVisitorCardCheckBox = new JCheckBox("View only Visitor Card Created logs");
        filterVisitorCardCheckBox.setSelected(false);

        // View logs button's action listener
        viewLogsButton.addActionListener(e -> viewLogs());

        // Add components to the frame
        add(viewLogsButton);
        add(filterDeniedAccessCheckBox);
        add(filterVisitorCardCheckBox);

        setVisible(true);
    }

    private void viewLogs() {
        boolean filterDenied = filterDeniedAccessCheckBox.isSelected();
        boolean filterVisitorCard = filterVisitorCardCheckBox.isSelected();

        List<String> logs;

        if (filterDenied && filterVisitorCard) {
            // If both checkboxes are selected, return an empty list (as it doesn't make sense to have both)
            logs = List.of("Cannot filter both denied access and visitor card logs at the same time.");
        } else if (filterDenied) {
            // If denied access filter is selected, show only denied logs
            logs = logger.getLogs(true);  // Filter only denied access logs
        } else if (filterVisitorCard) {
            // If visitor card filter is selected, show only visitor card created logs
            logs = logger.getVisitorCardLogs();
        } else {
            // Show all logs if no filter is selected
            logs = logger.getLogs(false); // Show all logs
        }

        StringBuilder logText = new StringBuilder("Audit Logs:\n");

        for (String log : logs) {
            logText.append(log).append("\n");
        }

        // Display the logs
        JOptionPane.showMessageDialog(this, logText.toString());
    }

    public static void main(String[] args) {
        new AuditLogViewer(new AccessLogger()); // Pass logger for audit log
    }
}
