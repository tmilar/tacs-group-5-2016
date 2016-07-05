package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.*;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

@RestController
public class UserRestController {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private CurrentUserDetailsService currentUserDetailsService;

    @RequestMapping(value = "api/users/{username}/favorites", method = RequestMethod.GET)
    public List<MarvelCharacter> usersFavorites(@PathVariable String username) {
        return favoritesService.getFavorites(username);
    }

    @RequestMapping(value = "/api/favorites", method = RequestMethod.POST)
    // @ResponseStatus(value = HttpStatus.OK)
    public void setAsFavorite(@RequestBody MarvelCharacterWrapper character) throws CharacterAlreadyFavoritedException, IOException, NoSuchAlgorithmException {
        User user = currentUserDetailsService.getCurrentUser();
        MarvelCharacter marvelCharacter =  new MarvelCharacter(character.getName(), character.getMarvelId());
        favoritesService.addFavorite(user.getUserName(), marvelCharacter);
    }
    @RequestMapping(value = "/api/favorites", method = RequestMethod.DELETE)
    // @ResponseStatus(value = HttpStatus.OK)
    public void removeFavorite(@RequestBody MarvelCharacterWrapper character) throws IOException, NoSuchAlgorithmException {
        User user = currentUserDetailsService.getCurrentUser();
        MarvelCharacter marvelCharacter =  new MarvelCharacter(character.getName(), character.getMarvelId());
        favoritesService.removeFavorite(user.getUserName(), marvelCharacter);
    }

}
