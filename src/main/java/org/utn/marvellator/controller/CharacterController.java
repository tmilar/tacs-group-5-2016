package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.model.UserSession;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Controller
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    UserSession loggedUser;

    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public String characters(Model model) throws IOException, NoSuchAlgorithmException{
           return charactersPage(0, model);
    }

    @RequestMapping(value = "/characters/{page}", method = RequestMethod.GET)
    public String characters(@PathVariable(value = "page") Integer page, Model model) throws IOException, NoSuchAlgorithmException {
       return charactersPage(page, model);
    }

    private String charactersPage(Integer page, Model model) throws IOException, NoSuchAlgorithmException{
        int offset = 10*page;
        int limit = 10;
        List<MarvelCharacter> characters = characterService.getCharactersPage(offset, limit);
        User user = currentUserDetailsService.getCurrentUser();
        model.addAttribute("characters", favoritesService.favoritesWithStatus(characters, user));
        model.addAttribute("page", page);
        return "characters";
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String favoritesPage(Model model) {
        //  String username = loggedUser.getUser().getUserName();
        model.addAttribute(favoritesService.getFavorites("eminem"));
        return "favorites";
        // return username;
    }


    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String ranking(Model model) {
        return "ranking";
    }


}
