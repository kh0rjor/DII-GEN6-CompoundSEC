package view;

import controller.VisitorController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class VisitorGUI {
    private VisitorController controller = new VisitorController();
    private JFrame frame;
    private User visitor;
    private String reservationId;

    public VisitorGUI() {
        frame = new JFrame("Visitor Access");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showVisitorInfoForm();
    }

    private void showVisitorInfoForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField nameField = new JTextField(10);
        JTextField reservationField = new JTextField(10);
        JButton submitButton = new JButton("Proceed");

        panel.add(new JLabel("Enter Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Reservation ID:"));
        panel.add(reservationField);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            reservationId = reservationField.getText().trim();
            if (!name.isEmpty() && !reservationId.isEmpty()) {
                visitor = new User(name, "VISITOR");
                frame.getContentPane().removeAll();
                showFloorSelection();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showFloorSelection() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton lowFloorButton = new JButton("Low Floor");
        JButton mediumFloorButton = new JButton("Medium Floor");
        JButton highFloorButton = new JButton("High Floor");

        lowFloorButton.addActionListener(e -> showRoomSelection("Low Floor"));
        mediumFloorButton.addActionListener(e -> showRoomSelection("Medium Floor"));
        highFloorButton.addActionListener(e -> checkAccess("High Floor", visitor.getRole()));

        panel.add(new JLabel("Select Floor:"));
        panel.add(lowFloorButton);
        panel.add(mediumFloorButton);
        panel.add(highFloorButton);

        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showRoomSelection(String floor) {
        JPanel panel = new JPanel(new GridLayout(0, 3)); // Layout for better spacing

        if (floor.equals("Medium Floor")) {
            for (int i = 1; i <= 6; i++) {
                final int roomIndex = i;
                JButton roomButton = new JButton("Room " + roomIndex);
                roomButton.addActionListener(e -> checkAccess(floor + " - Room " + roomIndex, visitor.getRole()));
                panel.add(roomButton);
            }
        } else if (floor.equals("Low Floor")) {
            JButton meetingRoom = new JButton("Meeting Room");
            JButton fitness = new JButton("Fitness");
            JButton room1 = new JButton("Room 1");
            JButton room2 = new JButton("Room 2");
            JButton frontOffice = new JButton("Front Office Room");
            JButton storage = new JButton("Storage Room");

            meetingRoom.addActionListener(e -> checkAccess(floor + " - Meeting Room", visitor.getRole()));
            fitness.addActionListener(e -> checkAccess(floor + " - Fitness", visitor.getRole()));
            room1.addActionListener(e -> checkAccess(floor + " - Room 1", visitor.getRole()));
            room2.addActionListener(e -> checkAccess(floor + " - Room 2", visitor.getRole()));

            panel.add(meetingRoom);
            panel.add(fitness);
            panel.add(room1);
            panel.add(room2);

            // Disabled buttons for restricted areas
            frontOffice.setEnabled(false);
            storage.setEnabled(false);
            panel.add(frontOffice);
            panel.add(storage);
        }

        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void checkAccess(String location, String role) {
        boolean granted = controller.requestAccess(new User(visitor.getName(), role), location);
        JOptionPane.showMessageDialog(frame, granted ? "Access Granted" : "Access Denied");
    }
}
