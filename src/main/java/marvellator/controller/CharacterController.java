package marvellator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CharacterController {

    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public String charactersPage() {
        return "Characters Page \n";
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String rankingPage() {
        return "Ranking Page \n";
    }
}
