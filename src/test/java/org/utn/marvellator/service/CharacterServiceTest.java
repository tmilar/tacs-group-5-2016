package org.utn.marvellator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.MarvelCharacter;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class CharacterServiceTest {


    @Autowired
    private CharacterService characterService;

    @Test
    public void getCharacters_shouldReturnMarvelAPICharacters(){
        List<MarvelCharacter> characters = characterService.getCharacters();

        assertFalse(characters.isEmpty());

    }
}
