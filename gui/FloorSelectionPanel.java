package gui;

import logging.AccessLogger;

import javax.swing.*;
import java.awt.*;

public class FloorSelectionPanel extends JFrame {
    private String userType;
    private AccessLogger logger;

    public FloorSelectionPanel(String userType, AccessLogger logger) {
        this.userType = userType;
        this.logger = logger;

        setTitle("Select Floor");
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));

        JButton highFloor = new JButton("HIGH FLOOR");
        JButton mediumFloor = new JButton("MEDIUM FLOOR");
        JButton lowFloor = new JButton("LOW FLOOR");

        highFloor.addActionListener(e -> new RoomSelectionPanel("High Floor", userType, logger));
        mediumFloor.addActionListener(e -> new RoomSelectionPanel("Medium Floor", userType, logger));
        lowFloor.addActionListener(e -> new RoomSelectionPanel("Low Floor", userType, logger));

        add(highFloor);
        add(mediumFloor);
        add(lowFloor);

        setVisible(true);
    }
}