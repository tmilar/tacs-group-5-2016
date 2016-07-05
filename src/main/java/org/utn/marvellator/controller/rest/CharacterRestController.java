package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
import java.util.List;

@RestController
public class CharacterRestController {

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    CharacterService characterService;

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @RequestMapping(value = "api/characters", method = RequestMethod.GET)
    public List<MarvelCharacter> characters() throws IOException, NoSuchAlgorithmException{
			return characterService.charactersPage(0);
    }

		@RequestMapping(value = "api/characters/{page}", method = RequestMethod.GET)
		public List<MarvelCharacter> characters(@PathVariable(value = "page") Integer page, Model model) throws IOException, NoSuchAlgorithmException {
			return characterService.charactersPage(page);
		}


    @RequestMapping(value = "api/ranking", method = RequestMethod.GET)
    public String ranking() {
        return "Ranking of users \n";
    }
}
