package marvellator;/*package controllers;

import marvellator.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class marvellator.UserController {
    *//* API *//*
    @RequestMapping(value="/user")
    public marvellator.User userHome(@RequestParam(value="name", defaultValue="superuser") String name) {
        return new marvellator.User("A default nickname",  name);
    }
    @RequestMapping(value="api/user",method= RequestMethod.POST)
    public String userSignUp() {
        return "marvellator.User registered!";
    }
    @RequestMapping(value="api/users",method= RequestMethod.GET)
    public String users() {
        return "List of users";
    }
    @RequestMapping(value="api/users/{id}/favs",method= RequestMethod.GET)
    public String usersFavorites(@PathVariable int id) {
        return "List of users' favorites";
    }
    @RequestMapping(value="api/users/{id}/favs",method= RequestMethod.POST)
    public String addFavorite(@PathVariable int id) {
        return "Added favorite character";
    }
    @RequestMapping(value="api/users/{idUser}/favs/{idCharacter}",method= RequestMethod.DELETE)
    public String deleteFavorite(@PathVariable int id) {
        return "Deleted favorite character";
    }

    *//* FRONT END *//*
    @RequestMapping(value="/register",method= RequestMethod.GET)
    public String registerPage() {
        return "Register Page";
    }
    @RequestMapping(value="users",method= RequestMethod.GET)
    public String usersPage() {
        return "Users Page";
    }
    @RequestMapping(value="users/{id}/favs",method= RequestMethod.GET)
    public String favoritesPage(@PathVariable int id) {
        return "Favorites Page";
    }
}*/

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value="/user", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("user", new User("name", "username"));
        return "user";
    }

    @RequestMapping(value="/user", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "result";
    }

}
