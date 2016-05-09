package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.CharacterAlreadyFavoritedException;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;

import java.util.List;
import java.util.Set;

@RestController
public class UserRestController {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private FavoritesService favoritesService;


    @RequestMapping(value = "api/users/{username}/favorites", method = RequestMethod.DELETE)
    public MarvelCharacter deleteFavorite(@PathVariable String username, @RequestParam("characterId") String idCharacter) {
        MarvelCharacter character = characterService.getCharacterById(idCharacter);
        return favoritesService.removeFavorite(username, character);
    }

    @RequestMapping(value = "api/users/{username}/favorites", method = RequestMethod.GET)
    public List<MarvelCharacter> usersFavorites(@PathVariable String username) {
        return favoritesService.getFavorites(username);

    }

    @RequestMapping(value = "api/users/{username}/favorites", method = RequestMethod.POST)
    public MarvelCharacter addFavorite(@PathVariable String username, @RequestParam("characterId") String idCharacter) throws CharacterAlreadyFavoritedException{
        MarvelCharacter character = characterService.getCharacterById(idCharacter);
        return favoritesService.addFavorite(username, character);
    }
}
