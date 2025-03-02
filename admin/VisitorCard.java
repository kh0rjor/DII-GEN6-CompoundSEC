package admin;

import java.time.Duration;
import java.time.LocalDateTime;

public class VisitorCard {

    private String name;
    private String id;
    private String floor;
    private String room;
    private LocalDateTime createdTime; // Timestamp when the card was created
    private static final Duration EXPIRY_DURATION = Duration.ofHours(24); // 24 hours validity

    public VisitorCard(String name, String id, String floor, String room) {
        this.name = name;
        this.id = id;
        this.floor = floor;
        this.room = room;
        this.createdTime = LocalDateTime.now(); // Set the creation time when the card is created
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getFloor() {
        return floor;
    }

    public String getRoom() {
        return room;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isCardExpired() {
        // Check if the card is expired (more than 24 hours have passed)
        return Duration.between(createdTime, LocalDateTime.now()).compareTo(EXPIRY_DURATION) > 0;
    }

    public String getRemainingTime() {
        // Get remaining time until expiry
        Duration remainingTime = EXPIRY_DURATION.minus(Duration.between(createdTime, LocalDateTime.now()));
        return String.format("%d hours, %d minutes", remainingTime.toHours(), remainingTime.toMinutesPart());
    }

    @Override
    public String toString() {
        return "VisitorCard{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", floor='" + floor + '\'' +
                ", room='" + room + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
