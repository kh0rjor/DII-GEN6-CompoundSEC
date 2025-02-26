package access;

import java.time.LocalTime;

public interface AccessStrategy {
    boolean hasAccess(String room);  // Method to check room access
    boolean isAccessTimeValid();// Method to validate access based on time

}
