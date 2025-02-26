package admin;

import java.time.LocalDateTime;

public class VisitorCard {

    private String name;
    private String id;
    private String floor;
    private String room;
    private LocalDateTime createdTime; // Timestamp when the card was created

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
