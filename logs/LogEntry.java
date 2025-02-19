package logs;

import java.time.LocalDateTime;

public class LogEntry {
    private String visitorName;
    private String room;
    private String status;
    private LocalDateTime timestamp;

    public LogEntry(String visitorName, String room, String status) {
        this.visitorName = visitorName;
        this.room = room;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return timestamp + " - " + visitorName + " tried to access " + room + " - " + status;
    }
}
