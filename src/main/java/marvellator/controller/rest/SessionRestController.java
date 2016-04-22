package marvellator.controller.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionRestController {

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login() {
        return "Login page (API) \n";
    }

    @RequestMapping(value = "api/logout", method = RequestMethod.POST)
    public String logout() {

        return "User signed out \n";
    }
}
