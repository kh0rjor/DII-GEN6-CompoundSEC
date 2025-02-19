package access;

public class NormalAccessStrategy implements AccessStrategy {
    @Override
    public boolean hasAccess(Floor floor, String room) {
        if (floor instanceof HighFloor) {
            return false; // Normal visitor ไม่สามารถเข้าชั้นสูง
        }
        if (floor instanceof LowFloor && room.equals("Front")) {
            return false; // Normal visitor ไม่สามารถเข้าห้อง Front
        }
        return floor.canAccessRoom(room);
    }
}
