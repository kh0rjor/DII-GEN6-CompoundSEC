package gui;

import javax.swing.*;
import java.awt.*;
import admin.VisitorCard;
import logging.AccessLogger;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class VisitorCardManager extends JFrame {
    private JTextField nameField, idField, searchFieldForEdit, searchFieldForRevoke;
    private JComboBox<String> floorComboBox, roomComboBox;
    private AccessLogger logger;

    // Temporary list of visitor cards for demo purposes, simulating registration
    private List<VisitorCard> visitorCards = new ArrayList<>();

    // List to hold visitors that were registered when the program started
    private List<VisitorCard> initialVisitors = new ArrayList<>();

    public VisitorCardManager(AccessLogger logger) {
        this.logger = logger;
        setTitle("Manage Visitor Cards");
        setSize(600, 500);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Initialize initial visitors (this would be loaded from a database or file in a real scenario)
        initializeInitialVisitors();

        // Initialize panels for different functionalities
        JPanel addPanel = createAddCardPanel();
        JPanel editPanel = createEditCardPanel();
        JPanel revokePanel = createRevokeCardPanel();

        // Add the panels to the frame
        add(addPanel);
        add(editPanel);
        add(revokePanel);

        setVisible(true);
    }

    // Method to initialize initial visitors (this can be replaced with actual loading logic)
    private void initializeInitialVisitors() {
        // Example of initial visitors that were registered when the program started
        initialVisitors.add(new VisitorCard("Alice", "1001", "Low Floor", "LOBBY"));
        initialVisitors.add(new VisitorCard("Bob", "1002", "Medium Floor", "RECEPTION"));
    }

    // Method to create the Add Card section
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
        editButton.addActionListener(e -> searchVisitorCardForEditing());  // Call search method directly

        // Add components to panel
        panel.add(searchLabel);
        panel.add(searchFieldForEdit);
        panel.add(searchButton);
        panel.add(editButton);

        panel.setBorder(BorderFactory.createTitledBorder("Edit Visitor Card"));
        return panel;
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

    // Method to add a visitor card
    private void addVisitorCard() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String floor = (String) floorComboBox.getSelectedItem();
        String room = (String) roomComboBox.getSelectedItem();

        // Create new VisitorCard and add it to the list
        VisitorCard card = new VisitorCard(name, id, floor, room);
        visitorCards.add(card);

        // Log the addition of the visitor card
        logger.update("Added Visitor Card: " + name + " (" + id + ") - Floor: " + floor + ", Room: " + room);
        JOptionPane.showMessageDialog(this, "Visitor Card Added!");
    }

    // Method to search for a visitor card for editing (search by name or ID)
    private void searchVisitorCardForEditing() {
        String searchTerm = searchFieldForEdit.getText().trim();
        VisitorCard card = searchVisitorCard(searchTerm);

        if (card != null) {
            // Once found, pass the card to the edit method
            editVisitorCard(card); // This method will handle editing the card
        } else {
            JOptionPane.showMessageDialog(this, "Visitor Card not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to handle the actual editing of the visitor card
    private void editVisitorCard(VisitorCard card) {
        // Allow editing of fields for the found visitor card
        String newName = JOptionPane.showInputDialog(this, "Edit Name:", card.getName());
        String newId = JOptionPane.showInputDialog(this, "Edit ID:", card.getId());
        String newRoom = (String) JOptionPane.showInputDialog(this, "Edit Room:", card.getRoom(), JOptionPane.PLAIN_MESSAGE, null, new String[]{"LOBBY", "RECEPTION", "CAFETERIA", "VISITOR LOUNGE", "MAIN CONFERENCE ROOM"}, card.getRoom());

        // Update the card details
        card.setFloor(newName);  // Update name (if needed)
        card.setRoom(newRoom);   // Update room

        // Log the card update
        logger.update("Edited Visitor Card: " + newName + " (" + newId + ") - New Room: " + newRoom);

        // Show confirmation message
        JOptionPane.showMessageDialog(this, "Visitor Card Edited!");
    }

    // Method to revoke (delete) a visitor card (search by ID or name)
    private void revokeVisitorCard() {
        String searchTerm = searchFieldForRevoke.getText().trim();
        VisitorCard card = searchVisitorCard(searchTerm);

        if (card != null) {
            visitorCards.remove(card);  // Remove card from the list
            logger.update("Revoked Visitor Card: " + card.getName() + " (" + card.getId() + ")");
            JOptionPane.showMessageDialog(this, "Visitor Card Revoked!");
        } else {
            JOptionPane.showMessageDialog(this, "Visitor Card not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to search for a visitor card by name or ID (including initial visitors and added visitors)
    private VisitorCard searchVisitorCard(String searchTerm) {
        // Check in both initial visitors and added visitors
        for (VisitorCard card : initialVisitors) {
            if (card.getName().equalsIgnoreCase(searchTerm) || card.getId().equalsIgnoreCase(searchTerm)) {
                return card;  // Return the card if found
            }
        }
        for (VisitorCard card : visitorCards) {
            if (card.getName().equalsIgnoreCase(searchTerm) || card.getId().equalsIgnoreCase(searchTerm)) {
                return card;  // Return the card if found
            }
        }
        return null;  // Return null if no card is found
    }

    // Check if any visitor cards have expired (older than 1 day) and remove them
    private void cleanExpiredCards() {
        LocalDateTime now = LocalDateTime.now();

        // Remove cards that are older than 1 day
        visitorCards.removeIf(card -> Duration.between(card.getCreatedTime(), now).toDays() > 1);
        logger.update("Expired visitor cards cleaned.");
    }

    public static void main(String[] args) {
        new VisitorCardManager(new AccessLogger()); // Pass logger for audit log
    }
}
