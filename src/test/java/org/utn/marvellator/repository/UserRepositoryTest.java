package org.utn.marvellator.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @After
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldSaveNewUsers() {
        userRepository.save(new User("test1"));
        assertEquals(1, userRepository.count());
    }

    @Test
    public void shouldFindUserByUserName() {
        String testUsername = "test1";
        userRepository.save(new User(testUsername));

        User foundUser = userRepository.findFirstByUserName(testUsername);

        assertNotNull(foundUser);
        assertEquals(testUsername, foundUser.getUserName());
    }

    @Test(expected = DuplicateKeyException.class)
    public void shouldNotSaveAnUserWithAnExistingUsername(){
        String testUsername = "test1";
        userRepository.save(new User(testUsername));
        userRepository.save(new User(testUsername));
    }


    @Test
    public void shouldAssignAndPersistFavorite(){
        String testUsername = "testUserName2";
        int testCharacterId = 1;
        User testUser = new User(testUsername);
        testUser.addFavorite(testCharacterId);
        userRepository.save(testUser);

        assertEquals(1, userRepository.findFirstByUserName(testUsername).getFavorites().size());
        assertTrue(userRepository.findFirstByUserName(testUsername).getFavorites().contains(testCharacterId));
    }

    @Test
    public void shouldRemoveAndNoLongerPersistFavorite(){
        String testUsername = "testUserName3";
        int testCharacterId = 1;
        User testUser = new User(testUsername);
        testUser.addFavorite(testCharacterId);
        testUser.removeFavorite(testCharacterId);
        userRepository.save(testUser);

        assertEquals(0, userRepository.findFirstByUserName(testUsername).getFavorites().size());
        assertFalse(userRepository.findFirstByUserName(testUsername).getFavorites().contains(testCharacterId));
    }
}
