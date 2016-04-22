package org.utn.marvellator.model;

import org.junit.Test;
import org.utn.marvellator.repository.UserRepository;

import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void aUserIsSignedUpProperly(){
        User user = new User("user", "aName", "aPassword");
        UserRepository.repoUsers.registerUser(user);
        assertTrue(UserRepository.repoUsers.getRegisteredUsers().contains(user));
    }
}