package marvellator.apiControllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {


    @RequestMapping(value = "api/users/{idUser}/favs/{idCharacter}", method = RequestMethod.DELETE)
    public String deleteFavorite(@PathVariable int idUser, @PathVariable int idCharacter) {
        return "Deleted favorite character. The user was: " + idUser + "The character: " + idCharacter + "\n";
    }

    @RequestMapping(value = "api/users/{id}/favs", method = RequestMethod.GET)
    public String usersFavorites(@PathVariable int id) {

        return "List of user" + id + "'s favorites \n";
    }

    @RequestMapping(value = "api/users/{id}/favs", method = RequestMethod.POST)
    public String addFavorite(@PathVariable int id) {
        return "Added favorite character, its id is: " + id + "\n";
    }


}
