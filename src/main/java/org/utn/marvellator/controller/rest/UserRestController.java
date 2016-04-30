package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;
import org.utn.marvellator.service.UserService;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    //TODO: Jsonify

    @RequestMapping(value = "api/users/{username}/favs/{idCharacter}", method = RequestMethod.DELETE)
    public String deleteFavorite(@PathVariable String username, @PathVariable int idCharacter) {
        User user = userRepository.findFirstByUserName(username);
        if(user != null){
            user.removeFavorite(idCharacter);
            userRepository.save(user);
            return "Favorite deleted";
        }else return "Failure";
    }

    @RequestMapping(value = "api/users/{username}/favs", method = RequestMethod.GET)
    public String usersFavorites(@PathVariable String username) {
        User user = userRepository.findFirstByUserName(username);

        if(user != null)
            return user.getFavorites().toString();
        else
            return "Failure";
    }

    @RequestMapping(value = "api/users/{username}/favs", method = RequestMethod.POST)
    public String addFavorite(@PathVariable String username,@RequestParam("characterId") int idCharacter){
        User user = userRepository.findFirstByUserName(username);

        if(user != null){
            user.addFavorite(idCharacter);
            userRepository.save(user);
            return "Favorite added";
        }else return "Failure";
    }
}
