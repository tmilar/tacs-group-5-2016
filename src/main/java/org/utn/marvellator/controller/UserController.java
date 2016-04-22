package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User userHome(@RequestParam(value = "name", defaultValue = "A default name :)") String name) {
        return new User("A default nickname. We will only be receiving a userName. It is: ", name, "pass");
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void userSignUp(@ModelAttribute("userName") String userName, @ModelAttribute("name") String name, @ModelAttribute("password") String password) {
        userService.registerUser(name, userName, password);
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String favoritesPage(@PathVariable int id) {
        return "Favorites Page,the id is: " + id + "\n";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User users(@RequestParam(value = "id", defaultValue = "A cute static id ") String name) {
        return new User("This is a default id, we are making a static response :) ", name, "pass");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registerPage() {
        return "signup";
    }


}
