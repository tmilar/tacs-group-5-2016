package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.model.UserSession;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.FavoritesService;
import org.utn.marvellator.service.UserService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Controller
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    private UserService userService;

    @Autowired
    UserSession loggedUser;

    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public String characters(Model model) throws IOException{
           return charactersPage(0, model);
    }

    @RequestMapping(value = "/characters/{page}", method = RequestMethod.GET)
    public String characters(@PathVariable(value = "page") Integer page, Model model) throws IOException {
       return charactersPage(page, model);
    }

    private String charactersPage(Integer page, Model model) throws IOException{
        int offset = 10*page;
        int limit = 10;
        List<MarvelCharacter> characters = characterService.getCharactersPage(offset, limit);
        User user = currentUserDetailsService.getCurrentUser();
        model.addAttribute("characters", favoritesService.favoritesWithStatus(characters, user));
        model.addAttribute("page", page);
        return "characters";
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String favoritesPage(Model model) {
        User user = currentUserDetailsService.getCurrentUser();
        List<MarvelCharacter> characters = favoritesService.getFavorites(user.getUserName());
        model.addAttribute("characters", favoritesService.favoritesWithStatus(characters, user));
        return "favorites";
    }


    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String ranking(Model model) {
        HashMap<String,Integer> ranking = new HashMap<>();
        List<User> users = userService.getAllUsers();

        for(User user : users){
            List<MarvelCharacter> favorites = user.getFavorites();
            for(MarvelCharacter character : favorites){
                Integer count = ranking.get(character.getName());
                ranking.put(character.getName(), count != null? count + 1 : 1);
            }
        }

        ranking = sortByValue(ranking);
        model.addAttribute("ranking", ranking);
        return "ranking";
    }

    //Sortear map por sus values, copiado furiosamente de Stack Overflow
    private <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue( HashMap<K, V> map ) {
        HashMap<K, V> result = new LinkedHashMap<>();
        Stream<HashMap.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(
            new Comparator<HashMap.Entry<K,V>>() {
                @Override
                public int compare(HashMap.Entry<K, V> e1, HashMap.Entry<K,V> e2) {
                    return e2.getValue().compareTo(e1.getValue());
                }
            })
            .forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

        return result;
    }
}
