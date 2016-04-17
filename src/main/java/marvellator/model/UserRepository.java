package marvellator.model;

import java.util.ArrayList;
import java.util.List;

public final class UserRepository {

    public static UserRepository repoUsers = new UserRepository();


    public User getCurrentUser() {
        return currentUser;
    }

    private User currentUser;

    public List<User> getRegisteredUsers() {
        return registeredUsers;
    }

    private List<User> registeredUsers = new ArrayList<User>();

    public void registerUser(User newUser){
        registeredUsers.add(newUser);
    }

    public void login(User currentUser){
        this.currentUser = currentUser;
    }
}
