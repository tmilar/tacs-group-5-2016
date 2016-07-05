package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.*;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

import java.io.IOException;
import java.util.List;

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

    @RequestMapping(value = "/api/users/favorites", method = RequestMethod.POST)
    // @ResponseStatus(value = HttpStatus.OK)
    public void setAsFavorite(@RequestBody MarvelCharacterWrapper character) throws CharacterAlreadyFavoritedException, IOException {
        User user = currentUserDetailsService.getCurrentUser();
        MarvelCharacter marvelCharacter =  characterService.getCharacterById(character.getMarvelId());
        favoritesService.addFavorite(user.getUserName(), marvelCharacter);
    }

    @RequestMapping(value = "/api/users/favorites", method = RequestMethod.DELETE)
    // @ResponseStatus(value = HttpStatus.OK)
    public void removeFavorite(@RequestBody MarvelCharacterWrapper character) throws IOException {
        User user = currentUserDetailsService.getCurrentUser();
        MarvelCharacter marvelCharacter =  characterService.getCharacterById(character.getMarvelId());
        favoritesService.removeFavorite(user.getUserName(), marvelCharacter);
    }

}
