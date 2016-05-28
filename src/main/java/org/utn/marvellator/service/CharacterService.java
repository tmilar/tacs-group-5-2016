package org.utn.marvellator.service;

import org.apache.coyote.http11.upgrade.NioServletOutputStream;
import org.utn.marvellator.model.MarvelCharacter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CharacterService {

    MarvelCharacter getCharacterById(Integer characterId);

    MarvelCharacter getCharacterById(String characterId) throws IOException, NoSuchAlgorithmException;

    List<MarvelCharacter> getCharactersPage(int offset, int limit) throws NoSuchAlgorithmException, IOException;

    Integer getTotalCharacters() throws IOException, NoSuchAlgorithmException;
}
