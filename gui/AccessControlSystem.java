package gui;

import logging.AccessLogger;
import javax.swing.*;
import java.awt.*;

public class AccessControlSystem extends JFrame {
    private JTextField nameField;
    private JTextField idField;
    private JComboBox<String> roleComboBox;
    private AccessLogger logger = new AccessLogger();

    public AccessControlSystem() {
        setTitle("OFFICE Access System");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        // Role Selection ComboBox
        JLabel roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(new String[]{"Visitor", "Employee", "Admin"});

        // Name and ID Fields (No validation anymore, just save them for logs)
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JButton loginButton = new JButton("SUBMIT");

        // ActionListener to trigger the role-based access after input
        loginButton.addActionListener(e -> handleLogin());

        add(roleLabel);
        add(roleComboBox);
        add(nameLabel);
        add(nameField);
        add(idLabel);
        add(idField);
        add(loginButton);

        setVisible(true);
    }

    private void handleLogin() {
        String role = (String) roleComboBox.getSelectedItem();
        String name = nameField.getText().trim();
        String id = idField.getText().trim();

        // Log name and ID for auditing purposes
        logger.setUserInfo(name, id);  // Save user info to the logger

        // Check if role is selected and proceed accordingly
        if (role != null) {
            // Role validation based on selection
            if (role.equals("Admin")) {
                String password = JOptionPane.showInputDialog(this, "Enter Admin Password:");
                if (!"1234".equals(password)) {
                    JOptionPane.showMessageDialog(this, "Incorrect Password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new AdminDashboard(logger);
            } else {
                // For Visitor or Employee, proceed with floor selection
                new FloorSelectionPanel(role, logger);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a role!", "Access Denied", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AccessControlSystem();
    }
}
