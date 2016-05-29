package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.CharacterAlreadyFavoritedException;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.MarvelCharacterWrapper;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class CharacterRestController {

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    CharacterService characterService;

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @RequestMapping(value = "api/characters", method = RequestMethod.GET)
    public String characters() {
        return "List of characters \n";
    }

    @RequestMapping(value = "api/ranking", method = RequestMethod.GET)
    public String ranking() {
        return "Ranking of users \n";
    }
}
