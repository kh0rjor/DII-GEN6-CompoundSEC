package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private JButton manageVisitorsButton, viewLogsButton;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        manageVisitorsButton = new JButton("Manage Visitors");
        viewLogsButton = new JButton("View Audit Logs");

        manageVisitorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageVisitors();
            }
        });

        viewLogsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuditLogs();
            }
        });

        add(manageVisitorsButton);
        add(viewLogsButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
