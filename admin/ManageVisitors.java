package admin;

import visitor.VisitorCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import access.AccessStrategy;

public class ManageVisitors extends JFrame {
    private DefaultListModel<String> visitorListModel;
    private JList<String> visitorList;
    private JButton addVisitorButton, editVisitorButton, removeVisitorButton;
    private List<VisitorCard> visitors;


    public ManageVisitors() {
        setTitle("Manage Visitors");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        visitors = new ArrayList<>();
        visitorListModel = new DefaultListModel<>();
        visitorList = new JList<>(visitorListModel);

        JPanel buttonPanel = new JPanel();
        addVisitorButton = new JButton("Add Visitor");
        editVisitorButton = new JButton("Edit Visitor");
        removeVisitorButton = new JButton("Remove Visitor");

        addVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Visitor Name:");
                String id = JOptionPane.showInputDialog("Enter Visitor ID:");
                if (name != null && id != null) {
                    VisitorCard newVisitor = new VisitorCard(name, id, "Normal", "LowFloor", "Lobby");
                    visitors.add(newVisitor);
                    visitorListModel.addElement(newVisitor.toString());
                }
            }
        });

        editVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = visitorList.getSelectedIndex();
                if (index != -1) {
                    VisitorCard visitor = visitors.get(index);
                    String newName = JOptionPane.showInputDialog("Edit Name:", visitor.getName());
                    if (newName != null) visitor.setName(newName);
                    visitorListModel.set(index, visitor.toString());
                }
            }
        });

        removeVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = visitorList.getSelectedIndex();
                if (index != -1) {
                    visitors.remove(index);
                    visitorListModel.remove(index);
                }
            }
        });

        buttonPanel.add(addVisitorButton);
        buttonPanel.add(editVisitorButton);
        buttonPanel.add(removeVisitorButton);

        add(new JScrollPane(visitorList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
