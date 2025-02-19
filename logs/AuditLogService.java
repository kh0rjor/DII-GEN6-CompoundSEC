package logs;

import java.util.ArrayList;
import java.util.List;

public class AuditLogService {
    private static List<LogEntry> logs = new ArrayList<>();

    public static void logAccess(String visitorName, String room, boolean granted) {
        logs.add(new LogEntry(visitorName, room, granted ? "Access Granted" : "Access Denied"));
    }

    public static List<LogEntry> getLogs() {
        return logs;
    }
}
