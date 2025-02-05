package view;

import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Access Control System");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        JButton empButton = new JButton("Employee Management");
        JButton visitorButton = new JButton("Visitor Access");

        empButton.addActionListener(e -> new EmployeeGUI());
        visitorButton.addActionListener(e -> new VisitorGUI());

        panel.add(empButton);
        panel.add(visitorButton);
        frame.add(panel);
        frame.setVisible(true);
    }
}
