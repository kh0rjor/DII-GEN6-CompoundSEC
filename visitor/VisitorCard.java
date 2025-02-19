package visitor;

public abstract class VisitorCard { // เปลี่ยนเป็น abstract class
    private String name;
    private String id;
    private String visitorType;
    private String floor;
    private String room;

    public VisitorCard(String name, String id, String visitorType, String floor, String room) {
        this.name = name;
        this.id = id;
        this.visitorType = visitorType;
        this.floor = floor;
        this.room = room;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getVisitorType() {
        return visitorType;
    }
    public String getFloor() {
        return floor;
    }
    public String getRoom() {
        return room;
    }

    public abstract boolean accessFloor(String floor);
    public abstract boolean accessRoom(String room);

    @Override
    public String toString() {
        return "VisitorCard [Name=" + name + ", ID=" + id + ", Type=" + visitorType +
                ", Floor=" + floor + ", Room=" + room + "]";
    }
}
