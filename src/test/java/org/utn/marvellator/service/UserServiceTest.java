package org.utn.marvellator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerUser_withATestUser_shouldCreateAndSaveTestUser(){
        String testUsername = "testUserName";
        User testUser = new User(testUsername);
        testUser.setPassword("pwd123");
        testUser.setEmail("marvellatoruser@gmail.com");

        userService.registerUser(testUser);

        assertEquals(1, userRepository.count());
        assertEquals(testUsername, userRepository.findFirstByUserName(testUsername).getUserName());
    }

    @Test
    public void addFav_withATestUser_shouldAssignAndPersistFavorite(){
        String testUsername = "testUserName2";
        int testCharacterId = 1;
        User testUser = new User(testUsername);
        testUser.addFavorite(testCharacterId);
        userService.registerUser(testUser);

        assertEquals(1, userRepository.findFirstByUserName(testUsername).getFavorites().size());
        assertTrue(userRepository.findFirstByUserName(testUsername).getFavorites().contains(testCharacterId));
    }

    @Test
    public void removeFav_withATestUser_shouldRemoveAndNoLongerPersistFavorite(){
        String testUsername = "testUserName3";
        int testCharacterId = 1;
        User testUser = new User(testUsername);
        testUser.addFavorite(testCharacterId);
        testUser.removeFavorite(testCharacterId);
        userService.registerUser(testUser);

        assertEquals(0, userRepository.findFirstByUserName(testUsername).getFavorites().size());
        assertFalse(userRepository.findFirstByUserName(testUsername).getFavorites().contains(testCharacterId));
    }
}
