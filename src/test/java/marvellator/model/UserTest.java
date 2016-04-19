package marvellator.model;

import org.junit.Ignore;
import org.junit.Test;

//import static marvellator.repository.UserRepositoryImpl.repoUsers;
import static org.junit.Assert.*;


public class UserTest {

    @Ignore
    @Test
    public void aUserIsSignedUpProperly(){
        User user = new User("user", "aName", "aPassword");
//        repoUsers.registerUser(user);
//        assertTrue(repoUsers.getRegisteredUsers().contains(user));
    }
}