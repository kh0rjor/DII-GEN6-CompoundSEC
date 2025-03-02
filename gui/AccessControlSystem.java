package gui;

import access.AccessStrategy;
import access.EmployeeAccess;
import access.VisitorAccess;
import admin.VisitorCard;
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
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String role = (String) roleComboBox.getSelectedItem();

        // Check if the name and id are entered
        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both your Name and ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if either name or id is empty
        }

        // Log name and ID for auditing purposes
        logger.setUserInfo(name, id);  // Save user info to the logger

        // Store the user info in the UserDataManager when they log in
        UserDataManager.addUser(name, id);  // Add user to UserDataManager

        // Log first login and countdown for visitors
        if (role != null) {
            if (role.equals("Visitor")) {
                // Create a VisitorCard and check expiry
                VisitorCard visitorCard = new VisitorCard(name, id, "Low Floor", "Lobby");

                // Log Visitor Card Creation
                logger.update("Visitor Card Created for: " + name + " (" + id + ")");

                // Check for card expiration
                if (visitorCard.isCardExpired()) {
                    logger.update("Visitor Card Expired for: " + name + " (" + id + ")");
                } else {
                    logger.update("Visitor Card Valid: " + name + " (" + id + "), Remaining Time: " + visitorCard.getRemainingTime());
                }

                // Optionally show time countdown in UI (if needed)
                JOptionPane.showMessageDialog(this, "Visitor Card Valid for 24 hours.\nRemaining Time: " + visitorCard.getRemainingTime());
            }

            // Role validation based on selection
            if (role.equals("Admin")) {
                // Admin login logic with password check
                JPasswordField passwordField = new JPasswordField(10);
                int option = JOptionPane.showConfirmDialog(this, passwordField,
                        "Enter Admin Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String password = new String(passwordField.getPassword());  // Get the password entered

                    // Correct password check (password = 1234)
                    if (!"1234".equals(password)) {  // Correct password is "1234"
                        JOptionPane.showMessageDialog(this, "Incorrect Password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                        return;  // Exit if password is incorrect
                    }
                    new AdminDashboard(logger);  // Proceed to Admin Dashboard if password is correct
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
