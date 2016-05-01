package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.utn.marvellator.model.User;
import org.utn.marvellator.service.UserService;

import javax.validation.Valid;


@Controller
public class SessionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "id", defaultValue = "some user's id") String id) {
        //    return "Login page, the id is: " + id + "\n";
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute User user, Errors errors) {
       if (errors.hasErrors()) {
            return "home";
        }

        userService.loginUser(new User(user.getUserName(), user.getPassword()));
        return "redirect:/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage() {

        return "logout";
    }
}
