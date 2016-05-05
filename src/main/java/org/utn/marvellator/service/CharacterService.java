package org.utn.marvellator.service;

import org.utn.marvellator.model.MarvelCharacter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Tomas on 5/2/2016.
 */
public interface CharacterService {

    List<MarvelCharacter> getCharacters() throws IOException, NoSuchAlgorithmException;

    MarvelCharacter getCharacterById(Integer characterId);
}
