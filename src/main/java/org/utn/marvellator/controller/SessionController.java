package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.User;
import org.utn.marvellator.model.UserSession;
import org.utn.marvellator.service.UserService;

import javax.validation.Valid;

@Controller
@SessionAttributes("loggedUser")
public class SessionController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "id", defaultValue = "some user's id") String id) {
    //    return "Login page, the id is: " + id + "\n";
        return "login";
    }

    //The POST and actual handling of the form will be done by Spring Security
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute User user, Errors errors) {
        if (errors.hasErrors()) {
            return "home";
        }
        userService.loginUser(user);
      //  userService.loginUser(new UserSession(user.getUserName(), user.getPassword()));
        return "redirect:/index";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        userService.logoutUser();
        return "redirect:/home";
    }
}
