package gui;

import javax.swing.*;
import java.awt.*;
import userdata.UserData;
import userdata.UserDataManager;
import logging.AccessLogger;

public class VisitorCardManager extends JFrame {
    private JTextField nameField, idField, searchFieldForEdit, searchFieldForRevoke;
    private JComboBox<String> floorComboBox, roomComboBox;
    private AccessLogger logger;

    public VisitorCardManager(AccessLogger logger) {
        this.logger = logger;
        setTitle("Manage Visitor Cards");
        setSize(600, 500);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize the panels for different functionalities
        JPanel addPanel = createAddCardPanel();
        JPanel editPanel = createEditCardPanel();
        JPanel revokePanel = createRevokeCardPanel();

        // Add the panels to the frame
        add(addPanel);
        add(editPanel);
        add(revokePanel);

        setVisible(true);
    }

    private JPanel createAddCardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Create UI components for adding a visitor card
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel floorLabel = new JLabel("Select Floor:");
        floorComboBox = new JComboBox<>(new String[]{"Low Floor", "Medium Floor", "High Floor"});

        JLabel roomLabel = new JLabel("Select Room:");
        roomComboBox = new JComboBox<>(new String[]{"LOBBY", "RECEPTION", "CAFETERIA", "VISITOR LOUNGE", "MAIN CONFERENCE ROOM"});

        JButton addButton = new JButton("Add Card");
        addButton.addActionListener(e -> addVisitorCard());

        // Add components to panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(floorLabel);
        panel.add(floorComboBox);
        panel.add(roomLabel);
        panel.add(roomComboBox);
        panel.add(addButton);

        panel.setBorder(BorderFactory.createTitledBorder("Add Visitor Card"));
        return panel;
    }

    private void addVisitorCard() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String floor = (String) floorComboBox.getSelectedItem();
        String room = (String) roomComboBox.getSelectedItem();

        // Create a new UserData object and add it to the UserDataManager
        UserData userData = new UserData(name, id);
        UserDataManager.addUser(name, id); // Collect user data in the UserDataManager

        // Optionally, you can log the creation of the card and show a confirmation message
        logger.update("Added Visitor Card: " + name + " (" + id + ") - Floor: " + floor + ", Room: " + room);
        JOptionPane.showMessageDialog(this, "Visitor Card Added!");
    }

    // Method to create the Edit Card section
    private JPanel createEditCardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Search field for editing a visitor card
        JLabel searchLabel = new JLabel("Search by ID or Name:");
        searchFieldForEdit = new JTextField();

        JButton searchButton = new JButton("Search Card");
        JButton editButton = new JButton("Edit Card");

        searchButton.addActionListener(e -> searchVisitorCardForEditing());
        editButton.addActionListener(e -> editVisitorCardForEditing());  // Edit logic will be handled here

        // Add components to panel
        panel.add(searchLabel);
        panel.add(searchFieldForEdit);
        panel.add(searchButton);
        panel.add(editButton);

        panel.setBorder(BorderFactory.createTitledBorder("Edit Visitor Card"));
        return panel;
    }

    // Method to edit the visitor card (from UserDataManager)
    private void editVisitorCardForEditing() {
        String searchTerm = searchFieldForEdit.getText().trim();
        UserData user = UserDataManager.getUserByNameOrId(searchTerm);

        if (user != null) {
            // Allow the user to edit details (e.g., name, ID, floor, room)
            String newName = JOptionPane.showInputDialog(this, "Edit Name:", user.getName());
            String newId = JOptionPane.showInputDialog(this, "Edit ID:", user.getId());

            // After editing, update the user data in the UserDataManager
            user.setName(newName);
            user.setId(newId);

            // Log the card update
            logger.update("Edited Visitor Card: " + newName + " (" + newId + ")");

            // Show confirmation message
            JOptionPane.showMessageDialog(this, "Visitor Card Edited!");
        } else {
            // If no card is found
            JOptionPane.showMessageDialog(this, "Visitor Card not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to search and edit the visitor card (from UserDataManager)
    private void searchVisitorCardForEditing() {
        String searchTerm = searchFieldForEdit.getText().trim();
        UserData card = UserDataManager.getUserByNameOrId(searchTerm);

        if (card != null) {
            // Once found, allow editing the card (example: update the room and floor)
            editVisitorCard(card);  // This method will handle editing the card
        } else {
            JOptionPane.showMessageDialog(this, "Visitor Card not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to edit the visitor card
    private void editVisitorCard(UserData card) {
        String newName = JOptionPane.showInputDialog(this, "Edit Name:", card.getName());
        String newId = JOptionPane.showInputDialog(this, "Edit ID:", card.getId());

        // After editing, save the modified details back to the UserDataManager
        card.setName(newName);
        card.setId(newId);

        // Log the card update
        logger.update("Edited Visitor Card: " + newName + " (" + newId + ")");

        // Show confirmation message
        JOptionPane.showMessageDialog(this, "Visitor Card Edited!");
    }

    // Method to create the Revoke Card section
    private JPanel createRevokeCardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Search field for revoking a visitor card
        JLabel searchLabel = new JLabel("Search by ID or Name:");
        searchFieldForRevoke = new JTextField();

        JButton revokeButton = new JButton("Revoke Card");
        revokeButton.addActionListener(e -> revokeVisitorCard());

        // Add components to panel
        panel.add(searchLabel);
        panel.add(searchFieldForRevoke);
        panel.add(revokeButton);

        panel.setBorder(BorderFactory.createTitledBorder("Revoke Visitor Card"));
        return panel;
    }

    // Method to revoke (delete) a visitor card
    private void revokeVisitorCard() {
        String searchTerm = searchFieldForRevoke.getText().trim();
        UserData card = UserDataManager.getUserByNameOrId(searchTerm);

        if (card != null) {
            // Remove the card from UserDataManager
            UserDataManager.removeUser(card);

            // Log the card removal
            logger.update("Revoked Visitor Card: " + card.getName() + " (" + card.getId() + ")");
            JOptionPane.showMessageDialog(this, "Visitor Card Revoked!");
        } else {
            JOptionPane.showMessageDialog(this, "Visitor Card not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new VisitorCardManager(new AccessLogger()); // Pass logger for audit log
    }
}
