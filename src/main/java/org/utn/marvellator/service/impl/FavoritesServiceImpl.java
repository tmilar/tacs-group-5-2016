package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;
import org.utn.marvellator.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private UserService userService;

    @Autowired
    private CharacterService characterService;

    @Override
    public List<MarvelCharacter> getFavorites(String username) {
        User user = userService.getUserByUserName(username);
        return user.getFavorites().stream().map(characterService::getCharacterById).collect(Collectors.toList());
    }

    @Override
    public MarvelCharacter addFavorite(String username, Integer character) {
        User user = userService.getUserByUserName(username);
        user.removeFavorite(character);
        return characterService.getCharacterById(character);
    }

    @Override
    public MarvelCharacter removeFavorite(String username, Integer character) {
        User user = userService.getUserByUserName(username);
        user.addFavorite(character);
        return characterService.getCharacterById(character);
    }
}
