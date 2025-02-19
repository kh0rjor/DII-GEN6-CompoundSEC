package access;

import java.util.List;

public abstract class Floor {
    protected String floorName;
    protected List<String> rooms;

    public Floor(String floorName, List<String> rooms) {
        this.floorName = floorName;
        this.rooms = rooms;
    }

    public String getFloorName() {
        return floorName;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public abstract boolean canAccessRoom(String room);
}
