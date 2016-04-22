package org.utn.marvellator.repository;

import com.github.fakemongo.junit.FongoRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.MarvellatorApplicationTestConfig;
import org.utn.marvellator.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarvellatorApplicationTestConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
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
}
