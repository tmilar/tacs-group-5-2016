package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.service.FavoritesService;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private FavoritesService favoritesService;


    @RequestMapping(value = "api/users/{username}/favorites", method = RequestMethod.DELETE)
    public MarvelCharacter deleteFavorite(@PathVariable String username, @RequestParam("characterId") Integer idCharacter) {
        return favoritesService.removeFavorite(username, idCharacter);
    }

    @RequestMapping(value = "api/users/{username}/favorites", method = RequestMethod.GET)
    public List<MarvelCharacter> usersFavorites(@PathVariable String username) {
        return favoritesService.getFavorites(username);

    }

    @RequestMapping(value = "api/users/{username}/favorites", method = RequestMethod.POST)
    public MarvelCharacter addFavorite(@PathVariable String username, @RequestParam("characterId") Integer idCharacter){
       return favoritesService.addFavorite(username, idCharacter);
    }
}
