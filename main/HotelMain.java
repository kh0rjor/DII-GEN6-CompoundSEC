package main;
import javax.swing.*;
import java.awt.*;

public class HotelMain extends JFrame {
    public HotelMain() {
        setTitle("Hotel Access System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("HOTEL", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton visitorButton = new JButton("Visitor");
        JButton adminButton = new JButton("Admin");
        panel.add(visitorButton);
        panel.add(adminButton);
        add(panel, BorderLayout.SOUTH);

        visitorButton.addActionListener(e -> new visitor.VisitorGUI());
        adminButton.addActionListener(e -> new auth.AdminLogin());

        setVisible(true);
    }

    public static void main(String[] args) {
        new HotelMain();
    }
}
