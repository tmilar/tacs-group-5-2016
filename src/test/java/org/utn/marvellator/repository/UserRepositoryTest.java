package org.utn.marvellator.repository;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.CharacterAlreadyFavoritedException;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CharacterService characterService;

    @Autowired
    FavoritesService favoritesService;

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

    @Ignore
    @Test
    public void shouldAssignAndPersistFavorite() throws CharacterAlreadyFavoritedException{
        String testUsername = "testUserName2";
        Integer testCharacterId = 1;
        User testUser = new User(testUsername);
        MarvelCharacter character = characterService.getCharacterById(testCharacterId);
        testUser.addFavorite(character);
        userRepository.save(testUser);

        assertEquals(1, userRepository.findFirstByUserName(testUsername).getFavorites().size());
        assertTrue(userRepository.findFirstByUserName(testUsername).getFavorites().contains(character));
    }
    @Ignore
    @Test
    public void shouldRemoveAndNoLongerPersistFavorite() throws CharacterAlreadyFavoritedException{
        String testUsername = "testUserName3";
        Integer testCharacterId = 1;
        User testUser = new User(testUsername);
        MarvelCharacter character = characterService.getCharacterById(testCharacterId);
        testUser.addFavorite(character);
        testUser.removeFavorite(character);
        userRepository.save(testUser);

        assertEquals(0, userRepository.findFirstByUserName(testUsername).getFavorites().size());
        assertFalse(userRepository.findFirstByUserName(testUsername).getFavorites().contains(character));
    }
}
