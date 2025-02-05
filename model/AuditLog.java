package model;

import java.util.ArrayList;
import java.util.List;

public class AuditLog {
    private static List<String> logs = new ArrayList<>();

    public static void log(String message) {
        logs.add(message);
        System.out.println("Audit Log: " + message);
    }

    public static List<String> getLogs() {
        return logs;
    }
}
