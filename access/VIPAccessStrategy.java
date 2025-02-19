package access;

public class VIPAccessStrategy implements AccessStrategy {
    @Override
    public boolean hasAccess(Floor floor, String room) {
        return !room.equals("Front"); // VIP เข้าได้ทุกห้องยกเว้น Front
    }
}
