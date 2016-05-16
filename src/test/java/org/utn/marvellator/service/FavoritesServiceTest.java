package org.utn.marvellator.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.CharacterAlreadyFavoritedException;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class FavoritesServiceTest {

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    CharacterService characterService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws CharacterAlreadyFavoritedException{
        User testUser = new User("marshall", "999666");
        userService.registerUser(testUser);
        MarvelCharacter amun = new MarvelCharacter("Amun", "10010905");
        favoritesService.addFavorite("marshall", amun);
    }

    @After
    public void clear(){
        userRepository.deleteAll();
    }

    @Test
    public void addFavorite_withTestUser_shouldSaveIt() throws CharacterAlreadyFavoritedException{
        MarvelCharacter annihilus = new MarvelCharacter("Annihilus", "1009154");
        favoritesService.addFavorite("marshall", annihilus);
        assertEquals(favoritesService.getFavorites("marshall").size(), 2);
    }


    @Test(expected = CharacterAlreadyFavoritedException.class)
    public void addRepeatedFavorite_withTestUser_shouldThrowAnException() throws CharacterAlreadyFavoritedException{
        MarvelCharacter annihilus = new MarvelCharacter("Annihilus", "1009154");
        favoritesService.addFavorite("marshall", annihilus);
        assertEquals(2, favoritesService.getFavorites("marshall").size());
        favoritesService.addFavorite("marshall", annihilus);
        assertEquals(2, favoritesService.getFavorites("marshall").size());

    }

    @Test
    public void removeFavorite_withTestUser_shouldRemoveItTotally(){
        MarvelCharacter amun = new MarvelCharacter("Amun", "10010905");
        favoritesService.removeFavorite("marshall", amun);
        assertEquals(0, favoritesService.getFavorites("marshall").size());
    }

    @Test
    public void getFavorites_withTestUser_shouldGetAllOfThem(){
        assertEquals(1, favoritesService.getFavorites("marshall").size());
    }
}