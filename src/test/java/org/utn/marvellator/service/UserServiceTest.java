package org.utn.marvellator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTestConfig;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestConfig.class)
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

}
