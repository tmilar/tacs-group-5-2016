package marvellator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SessionController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "id", defaultValue = "some user's id") String id) {
    //    return "Login page, the id is: " + id + "\n";
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage() {

        return "logout";
    }
}
