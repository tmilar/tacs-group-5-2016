package org.utn.marvellator.service;

import org.utn.marvellator.model.CharacterAlreadyFavoritedException;
import org.utn.marvellator.model.MarvelCharacter;
import java.util.List;


public interface FavoritesService {
    List<MarvelCharacter> getFavorites(String username);

    MarvelCharacter addFavorite(String username, MarvelCharacter character) throws CharacterAlreadyFavoritedException;

    MarvelCharacter removeFavorite(String username, MarvelCharacter character);
}

