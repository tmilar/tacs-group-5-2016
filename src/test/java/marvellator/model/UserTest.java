package marvellator.model;

import org.junit.Test;

import static marvellator.model.UserRepository.repoUsers;
import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void aUserIsSignedUpProperly(){
        User user = new User("user", "aName", "aPassword");
        repoUsers.registerUser(user);
        assertTrue(repoUsers.getRegisteredUsers().contains(user));
    }
}