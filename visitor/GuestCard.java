package visitor;

public class GuestCard extends VisitorCard {
    public GuestCard(String name, String id, String floor, String room) {
        super(name, id, "Guest", floor, room);
    }

    @Override
    public boolean accessFloor(String floor) {
        System.out.println("❌ Guest " + getName() + " does not have access to floor: " + floor);
        return false;
    }

    @Override
    public boolean accessRoom(String room) {
        if (room.equals(getRoom())) {
            System.out.println("✅ Guest " + getName() + " granted access to room: " + room);
            return true;
        }
        System.out.println("❌ Guest " + getName() + " denied access to room: " + room);
        return false;
    }
}
