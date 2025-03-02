package userdata;

import java.util.ArrayList;
import java.util.List;

public class UserDataManager {

    private static List<UserData> userList = new ArrayList<>();

    // Method to add a user (this method is called during both login and card creation)
    public static void addUser(String name, String id) {
        UserData newUser = new UserData(name, id);
        userList.add(newUser);
    }

    // Method to get user by name or ID
    public static UserData getUserByNameOrId(String nameOrId) {
        for (UserData user : userList) {
            if (user.getName().equalsIgnoreCase(nameOrId) || user.getId().equalsIgnoreCase(nameOrId)) {
                return user;
            }
        }
        return null;
    }

    // Method to remove a user
    public static void removeUser(UserData user) {
        userList.remove(user);
    }

    // Method to get all users
    public static List<UserData> getAllUsers() {
        return userList;
    }
}
