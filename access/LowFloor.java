package access;

import java.util.Arrays;

public class LowFloor extends Floor {
    public LowFloor() {
        super("LowFloor", Arrays.asList("Front", "Dining Room", "Lobby", "Room1", "Room2", "Room3"));
    }

    @Override
    public boolean canAccessRoom(String room) {
        return rooms.contains(room);
    }
}
