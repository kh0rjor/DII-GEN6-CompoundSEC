package access;

import java.util.Arrays;

public class MediumFloor extends Floor {
    public MediumFloor() {
        super("MediumFloor", Arrays.asList("Room1", "Room2", "Room3", "Room4", "Room5", "Room6"));
    }

    @Override
    public boolean canAccessRoom(String room) {
        return rooms.contains(room);
    }
}
