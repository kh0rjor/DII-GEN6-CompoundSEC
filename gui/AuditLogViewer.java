package gui;

import logging.AccessLogger;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AuditLogViewer extends JFrame {
    private AccessLogger logger;
    private JCheckBox filterDeniedAccessCheckBox;

    public AuditLogViewer(AccessLogger logger) {
        this.logger = logger;
        setTitle("View Audit Logs");
        setSize(500, 400);
        setLayout(new GridLayout(3, 1));

        // Button to view logs and checkbox for filtering
        JButton viewLogsButton = new JButton("View Logs");
        filterDeniedAccessCheckBox = new JCheckBox("View only denied access logs");
        filterDeniedAccessCheckBox.setSelected(false);

        viewLogsButton.addActionListener(e -> viewLogs());

        add(viewLogsButton);
        add(filterDeniedAccessCheckBox);

        setVisible(true);
    }

    private void viewLogs() {
        boolean filterDenied = filterDeniedAccessCheckBox.isSelected();
        List<String> logs = logger.getLogs(filterDenied); // Get logs based on filter

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
