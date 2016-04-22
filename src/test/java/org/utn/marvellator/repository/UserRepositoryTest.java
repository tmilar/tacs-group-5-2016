package org.utn.marvellator.repository;

import com.github.fakemongo.junit.FongoRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.MarvellatorApplicationTestConfig;
import org.utn.marvellator.model.User;

import static org.junit.Assert.assertEquals;

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
        userRepository.save(new User("fede"));
        assertEquals(1, userRepository.count());
    }


}
