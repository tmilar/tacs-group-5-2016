package org.utn.marvellator.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.MarvelCharacter;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class CharacterServiceTest {


    @Autowired
    private CharacterService characterService;

    @Test
    public void getCharactersPage_withOffset0AndLimit30_shouldReturn30MarvelCharacters() throws IOException {
        List<MarvelCharacter> characters = characterService.getCharactersPage(0, 30);

        assertEquals(30, characters.size());
    }

    @Test(expected = IOException.class)
    public void getCharactersPage_withOffset0AndLimit0_shouldGiveError() throws IOException {
        characterService.getCharactersPage(0, 0);
    }

    @Test(expected = IOException.class)
    public void getCharactersPage_withLimitGreaterThan100_shouldGiveError() throws IOException {
        characterService.getCharactersPage(0, 101);
    }

    @Test
    public void getTotalCharacters_shouldAlwaysReturnAValidAmount() throws IOException {

        Integer marvelCharactersCount = characterService.getTotalCharacters();

        assertNotNull(marvelCharactersCount);
        assertTrue(marvelCharactersCount > 0 && marvelCharactersCount < 2000);
    }

    @Test
    public void getCharacterById_shouldReturnAMarvelCharacter() throws IOException {
        MarvelCharacter marvelCharacter1 = characterService.getCharacterById("1009154");
        assertEquals(marvelCharacter1.getMarvelId(), "1009154");
    }

		/*@Test
		public void getAllCharacters_shouldMatchNumberOfCharactersFromApi() throws IOException {
			assertEquals(characterService.getTotalCharacters(), characterService.saveCharactersToDatabase());
		}*/
}
