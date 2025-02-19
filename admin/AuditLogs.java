package admin;

import logs.AuditLogService;
import logs.LogEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AuditLogs extends JFrame {
    private DefaultListModel<String> logListModel;
    private JList<String> logList;
    private JButton filterDeniedButton;

    public AuditLogs() {
        setTitle("Audit Logs");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        logListModel = new DefaultListModel<>();
        logList = new JList<>(logListModel);
        filterDeniedButton = new JButton("Filter Access Denied");

        loadLogs(false);

        filterDeniedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLogs(true);
            }
        });

        add(new JScrollPane(logList), BorderLayout.CENTER);
        add(filterDeniedButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadLogs(boolean filterDenied) {
        logListModel.clear();
        List<LogEntry> logs = AuditLogService.getLogs();
        for (LogEntry log : logs) {
            if (!filterDenied || log.getStatus().equals("Access Denied")) {
                logListModel.addElement(log.toString());
            }
        }
    }
}
