package visitor;

public class AdminCard extends VisitorCard {
    public AdminCard(String name, String id, String floor, String room) {
        super(name, id, "Admin", floor, room);
    }

    @Override
    public boolean accessFloor(String floor) {
        System.out.println("✅ Admin " + getName() + " granted access to floor: " + floor);
        return true;
    }

    @Override
    public boolean accessRoom(String room) {
        System.out.println("✅ Admin " + getName() + " granted access to room: " + room);
        return true;
    }
}
