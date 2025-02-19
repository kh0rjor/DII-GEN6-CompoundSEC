package access;

import java.util.Arrays;

public class HighFloor extends Floor {
    public HighFloor() {
        super("HighFloor", Arrays.asList("Deluxe1", "Deluxe2", "Deluxe3", "Deluxe4", "Deluxe5", "Deluxe6"));
    }

    @Override
    public boolean canAccessRoom(String room) {
        return rooms.contains(room);
    }
}
