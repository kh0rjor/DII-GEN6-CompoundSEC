package gui;

import access.AccessStrategy;
import access.EmployeeAccess;
import access.VisitorAccess;
import decorator.WeekendAccessDecorator;
import logging.AccessLogger;
import userdata.UserDataManager;

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

        // Name and ID Fields (with validation)
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
                // Create a JPasswordField for password input (hides the input)
                JPasswordField passwordField = new JPasswordField(10);
                int option = JOptionPane.showConfirmDialog(this, passwordField,
                        "Enter Admin Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String password = new String(passwordField.getPassword());  // Get the password entered

                    if (!"1234".equals(password)) {
                        JOptionPane.showMessageDialog(this, "Incorrect Password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    new AdminDashboard(logger);
                } else {
                    // Handle cancel or dialog close
                    JOptionPane.showMessageDialog(this, "Password entry canceled", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                AccessStrategy strategy;

                // Wrapping employee with WeekendAccessDecorator if it's an employee role
                if (role.equals("Employee")) {
                    strategy = new EmployeeAccess(logger);
                    strategy = new WeekendAccessDecorator(strategy); // Apply Weekend Access
                } else {
                    strategy = new VisitorAccess(logger); // Default to VisitorAccess for Visitors
                }

                // For Visitor or Employee, proceed with floor selection
                new FloorSelectionPanel(role, logger, strategy);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a role!", "Access Denied", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AccessControlSystem();
    }
}
