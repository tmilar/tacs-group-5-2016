package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.marvellator.model.CharacterAlreadyFavoritedException;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;
import org.utn.marvellator.service.FavoritesService;
import org.utn.marvellator.service.UserService;

import java.util.HashMap;
import java.util.List;


@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MarvelCharacter> getFavorites(String username) throws UnknownUserException{
        User user = userService.getUserByUserName(username);
        if (user == null) {
            throw new UnknownUserException(username);
        }
       // return user.getFavorites().stream().map(characterService::getCharacterById).collect(Collectors.toList());
        return user.getFavorites();
    }

    @Override
    public MarvelCharacter addFavorite(String username, MarvelCharacter character) throws CharacterAlreadyFavoritedException {
        User user = userService.getUserByUserName(username);
        user.addFavorite(character);
        userRepository.save(user);
        return character;
    }

    @Override
    public HashMap<MarvelCharacter, Boolean> favoritesWithStatus(List<MarvelCharacter> characters, User user){
        HashMap<MarvelCharacter, Boolean> charactersWithStatus = new HashMap<>();
				for (MarvelCharacter c : characters){
					charactersWithStatus.put(c, user.hasFavorite(c));
				}
        //characters.forEach(character->charactersWithStatus.put(character, user.hasFavorite(character)));

        return charactersWithStatus;
    }

    @Override
    public MarvelCharacter removeFavorite(String username, MarvelCharacter character) {
        User user = userService.getUserByUserName(username);
        user.removeFavorite(character);
        userRepository.save(user);
        return character;
    }
}
