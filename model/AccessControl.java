package model;

import java.util.HashMap;
import java.util.Map;

public class AccessControl {
    private static final Map<String, String[]> accessRules = new HashMap<>();

    static {
        accessRules.put("VISITOR", new String[]{"Low Floor", "Medium Floor"});
        accessRules.put("EMPLOYEE", new String[]{"Low Floor", "Medium Floor", "High Floor"});
    }

    public static boolean hasAccess(User user, String floor) {
        for (String allowedFloor : accessRules.getOrDefault(user.getRole(), new String[]{})) {
            if (allowedFloor.equals(floor)) return true;
        }
        return false;
    }
}
