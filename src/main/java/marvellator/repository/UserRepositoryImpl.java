package marvellator.repository;

import marvellator.model.User;

import java.util.ArrayList;
import java.util.List;

public final class UserRepositoryImpl {

//    public static UserRepositoryImpl repoUsers = new UserRepositoryImpl();


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
