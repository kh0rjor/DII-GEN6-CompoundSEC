package visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisitorGUI extends JFrame {
    private JTextField nameField, idField;
    private JComboBox<String> visitorTypeBox, floorBox, roomBox;
    private JButton submitButton;

    private static final String[] NORMAL_FLOORS = {"Low Floor", "Medium Floor"};
    private static final String[] VIP_FLOORS = {"Low Floor", "Medium Floor", "High Floor"};
    private static final String[][] NORMAL_ROOMS = {
            {"Front", "Dining Room", "Lobby", "Room 1", "Room 2", "Room 3"},
            {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6"}
    };
    private static final String[][] VIP_ROOMS = {
            {"Dining Room", "Lobby", "Room 1", "Room 2", "Room 3"},
            {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6"},
            {"Deluxe 1", "Deluxe 2", "Deluxe 3", "Deluxe 4", "Deluxe 5", "Deluxe 6"}
    };

    public VisitorGUI() {
        setTitle("Visitor Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Visitor Type:"));
        visitorTypeBox = new JComboBox<>(new String[]{"Normal", "VIP"});
        add(visitorTypeBox);

        add(new JLabel("Floor:"));
        floorBox = new JComboBox<>(NORMAL_FLOORS);
        add(floorBox);

        add(new JLabel("Room:"));
        roomBox = new JComboBox<>(NORMAL_ROOMS[0]);
        add(roomBox);

        submitButton = new JButton("Register");
        add(submitButton);

        visitorTypeBox.addActionListener(e -> updateFloors());
        floorBox.addActionListener(e -> updateRooms());

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String visitorType = (String) visitorTypeBox.getSelectedItem();
            String floor = (String) floorBox.getSelectedItem();
            String room = (String) roomBox.getSelectedItem();

            VisitorCard visitorCard = new VisitorCard(name, id, visitorType, floor, room);
            JOptionPane.showMessageDialog(null, "Visitor Registered!\n" + visitorCard);
        });

        setVisible(true);
    }

    private void updateFloors() {
        if (visitorTypeBox.getSelectedItem().equals("VIP")) {
            floorBox.setModel(new DefaultComboBoxModel<>(VIP_FLOORS));
        } else {
            floorBox.setModel(new DefaultComboBoxModel<>(NORMAL_FLOORS));
        }
        updateRooms();
    }

    private void updateRooms() {
        int floorIndex = floorBox.getSelectedIndex();
        if (visitorTypeBox.getSelectedItem().equals("VIP")) {
            roomBox.setModel(new DefaultComboBoxModel<>(VIP_ROOMS[floorIndex]));
        } else {
            roomBox.setModel(new DefaultComboBoxModel<>(NORMAL_ROOMS[floorIndex]));
        }
    }

    public static void main(String[] args) {
        new VisitorGUI();
    }
}
