package marvellator.controller.apiControllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class CharacterApiController {

    @RequestMapping(value = "api/characters", method = RequestMethod.GET)
    public String characters() {
        return "List of characters \n";
    }

    @RequestMapping(value = "api/character/{id}", method = RequestMethod.POST)
    public String setAsFavorite(@PathVariable int id) {
        return "Character set as favorite. Its id is: " + id + "\n";
    }

    @RequestMapping(value = "api/ranking", method = RequestMethod.GET)
    public String ranking() {
        return "Ranking of users \n";
    }
}
