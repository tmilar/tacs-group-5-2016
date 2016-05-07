package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.utn.marvellator.service.CharacterService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Controller
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public String characters(Model model) throws IOException, NoSuchAlgorithmException {
        int offset = 0;
        int limit = 50;

        model.addAttribute(characterService.getCharactersPage(offset, limit));
        return "characters";
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String ranking(Model model) {
        return "ranking";
    }
}
