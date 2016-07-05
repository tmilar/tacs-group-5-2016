package org.utn.marvellator.service;

import org.utn.marvellator.model.MarvelCharacter;

import java.io.IOException;
import java.util.List;

public interface CharacterService {

    MarvelCharacter getCharacterById(Integer characterId);

    MarvelCharacter getCharacterById(String characterId) throws IOException;

    List<MarvelCharacter> getCharactersPage(int offset, int limit) throws IOException;

    Integer getTotalCharacters() throws IOException;

    List<MarvelCharacter> charactersPage(Integer page) throws IOException;
}
