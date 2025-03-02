package gui;

import access.AccessStrategy;
import logging.AccessLogger;

import javax.swing.*;
import java.awt.*;

public class FloorSelectionPanel extends JFrame {
    private String userType;
    private AccessLogger logger;
    private AccessStrategy strategy;

    public FloorSelectionPanel(String userType, AccessLogger logger, AccessStrategy strategy) {
        this.userType = userType;
        this.logger = logger;
        this.strategy = strategy;

        setTitle("Select Floor");
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));

        JButton highFloor = new JButton("HIGH FLOOR");
        JButton mediumFloor = new JButton("MEDIUM FLOOR");
        JButton lowFloor = new JButton("LOW FLOOR");

        // Correct the addActionListener to pass the right arguments
        highFloor.addActionListener(e -> new RoomSelectionPanel("High Floor", userType, logger, strategy));
        mediumFloor.addActionListener(e -> new RoomSelectionPanel("Medium Floor", userType, logger, strategy));
        lowFloor.addActionListener(e -> new RoomSelectionPanel("Low Floor", userType, logger, strategy));

        add(highFloor);
        add(mediumFloor);
        add(lowFloor);

        setVisible(true);
    }
}