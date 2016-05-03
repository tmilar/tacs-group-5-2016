package org.utn.marvellator.service;

import org.utn.marvellator.model.MarvelCharacter;

import java.util.List;

public interface FavoritesService {
    List<MarvelCharacter> getFavorites(String username);

    MarvelCharacter addFavorite(String username, Integer character);

    MarvelCharacter removeFavorite(String username, Integer character);
}

