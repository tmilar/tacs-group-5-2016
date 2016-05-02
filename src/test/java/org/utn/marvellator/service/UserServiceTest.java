package org.utn.marvellator.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.User;
import org.utn.marvellator.model.UserSession;
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

    @Autowired
    private UserSession loggedUser;

    private User testUser = new User();

    @Before
    public void setUp(){
        testUser.setUserName("testUsername");
        testUser.setPassword("pwd123");
        testUser.setEmail("marvellatoruser@gmail.com");
    }

    @Test
    public void registerUser_withATestUser_shouldCreateAndSaveTestUser(){
        userService.registerUser(testUser);
        assertEquals(1, userRepository.count());
        String testUsername = testUser.getUserName();
        assertEquals(testUsername, userRepository.findFirstByUserName(testUsername).getUserName());
    }

    @Test
    public void whenUserLogsInItShouldBeInUserSession(){
        userService.loginUser(testUser);
        assertEquals(loggedUser.getUser(), testUser);
    }

    @Test
    public void whenUserLogsOutItShouldntBeInSession(){
        userService.logoutUser();
        assertFalse(loggedUser.getUser().equals(testUser));
    }
}
