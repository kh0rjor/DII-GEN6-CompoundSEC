package view;

import controller.EmployeeController;
import javax.swing.*;

public class EmployeeGUI {
    private EmployeeController controller = new EmployeeController();

    public EmployeeGUI() {
        JFrame frame = new JFrame("Employee Panel");
        frame.setSize(400, 300);
        JPanel panel = new JPanel();

        JTextField cardField = new JTextField(10);
        JTextField ownerField = new JTextField(10);
        JButton issueButton = new JButton("Issue Card");
        JButton revokeButton = new JButton("Revoke Card");

        issueButton.addActionListener(e -> controller.issueCard(cardField.getText(), ownerField.getText()));
        revokeButton.addActionListener(e -> controller.revokeCard(cardField.getText()));

        panel.add(new JLabel("Card ID:"));
        panel.add(cardField);
        panel.add(new JLabel("Owner:"));
        panel.add(ownerField);
        panel.add(issueButton);
        panel.add(revokeButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
