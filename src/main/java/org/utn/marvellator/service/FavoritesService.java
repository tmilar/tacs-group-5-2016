package org.utn.marvellator.service;

import org.utn.marvellator.model.CharacterAlreadyFavoritedException;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;

import java.util.HashMap;
import java.util.List;


public interface FavoritesService {
    List<MarvelCharacter> getFavorites(String username);

    MarvelCharacter addFavorite(String username, MarvelCharacter character) throws CharacterAlreadyFavoritedException;

    HashMap<MarvelCharacter, String> favoritesWithStatus(List<MarvelCharacter> favorites, User user);

    MarvelCharacter removeFavorite(String username, MarvelCharacter character);
}

