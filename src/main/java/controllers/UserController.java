package controllers;

import model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    /* API */
    @RequestMapping("/user")
    public User userHome(@RequestParam(value="name", defaultValue="superuser") String name) {
        return new User("A default nickname",  name);
    }
    @RequestMapping(value="api/user",method= RequestMethod.POST)
    public String userSignUp() {
        return "User registered!";
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

    /* FRONT END */
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
}
