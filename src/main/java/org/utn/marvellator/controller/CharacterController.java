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
    /*private <K, V extends Comparable<? super V>> HashMap<String, Integer> sortByValue(HashMap<K, V> map) {
				SortedSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {
					@Override
					public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
						int res = e1.getValue().compareTo(e2.getValue());
						if(res == 0)
							return e1.getKey().compareTo(e2.getKey());
						return res * -1;
					}
				});
				sortedSet.addAll((Collection<? extends Map.Entry<String, Integer>>) map.entrySet());
				HashMap<String, Integer> mapaFinal = new HashMap<>();
				for (Map.Entry<String, Integer> e: sortedSet){
					mapaFinal.put(e.getKey(), e.getValue());
				}

        return mapaFinal;
    }*/

		public static <K, V extends Comparable<? super V>> HashMap<K, V>
		sortByValue( HashMap<K, V> map )
		{
			List<Map.Entry<K, V>> list =
							new LinkedList<Map.Entry<K, V>>( map.entrySet() );
			Collections.sort( list, new Comparator<Map.Entry<K, V>>()
			{
				public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
				{
					return (o1.getValue()).compareTo( o2.getValue() );
				}
			} );

			HashMap<K, V> result = new LinkedHashMap<K, V>();
			for (Map.Entry<K, V> entry : list)
			{
				result.put( entry.getKey(), entry.getValue() );
			}
			return result;
		}

}
