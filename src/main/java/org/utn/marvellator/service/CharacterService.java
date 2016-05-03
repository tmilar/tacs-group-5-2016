package org.utn.marvellator.service;

import org.utn.marvellator.model.MarvelCharacter;

import java.util.List;

/**
 * Created by Tomas on 5/2/2016.
 */
public interface CharacterService {

    List<MarvelCharacter> getCharacters();

    MarvelCharacter getCharacterById(Long characterId);
}
