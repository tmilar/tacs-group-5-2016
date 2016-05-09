package org.utn.marvellator.service;

import org.utn.marvellator.model.MarvelCharacter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CharacterService {

    MarvelCharacter getCharacterById(Integer characterId);

    MarvelCharacter getCharacterById(String characterId);

    List<MarvelCharacter> getCharactersPage(int offset, int limit) throws NoSuchAlgorithmException, IOException;

    Integer getTotalCharacters() throws IOException, NoSuchAlgorithmException;
}
