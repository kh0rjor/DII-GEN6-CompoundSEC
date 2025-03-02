package access;

import java.time.LocalTime;

public class AdminAccess implements AccessStrategy {

    @Override
    public boolean hasAccess(String room) {
        return true;  // Admin has full access to all rooms
    }

    @Override
    public boolean isAccessTimeValid() {
        // Admin can access anytime
        return true;
    }

}