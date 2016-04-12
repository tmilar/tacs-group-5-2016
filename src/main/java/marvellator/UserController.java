package marvellator;/*package controllers;

import marvellator.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class marvellator.UserController {
    *//* API *//*

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    /* @RequestMapping(value="/user", method=RequestMethod.GET)
     public String greetingForm(Model model) {
         model.addAttribute("user", new User("name", "username"));
         return "user";
     }*/
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User userHome(@RequestParam(value = "name", defaultValue = "A default name :)") String name) {
        return new User("A default nickname. We will only be receiving a userName. It is: ", name);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User userSignUp() {
        return new User("This is a default nickname, we are making a static response :) ", "A cute static name");
    }
    @RequestMapping(value="/users",method= RequestMethod.GET)
    public User users(@RequestParam(value = "id", defaultValue = "A cute static id ") String name) {
        return new User("This is a default id, we are making a static response :) ", name);
    }

}
