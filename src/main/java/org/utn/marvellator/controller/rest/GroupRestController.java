package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.repository.GroupRepository;

import java.util.List;

@RestController
public class GroupRestController {

    @Autowired
    private GroupRepository groupRepository;

    @RequestMapping(value = "api/groups/{id}", method = RequestMethod.GET)
    public String group(@PathVariable String id) {
        Group group = groupRepository.findFirstById(id);
        return "A group";
    }

    @RequestMapping(value = "api/groups/{id}", method = RequestMethod.PUT)
    public String addCharacter(@PathVariable String id,@RequestParam("characterId") int idCharacter) throws CharacterAlreadyInGroupException {
        Group group = groupRepository.findFirstById(id);

        if(group != null){
            group.addCharacter(new MarvelCharacter(String.valueOf(idCharacter)));
            groupRepository.save(group);
            return "Character added to a group \n";
        }else return "Failure";
    }

    @RequestMapping(value = "api/groups/{id}", method = RequestMethod.DELETE)
    public String deleteGroup(@PathVariable String id) {
        Group group = groupRepository.findFirstById(id);

        if(group != null){
            groupRepository.delete(group);
            return "Group deleted \n";
        }else return "Failure";
    }

    @RequestMapping(value = "api/groups", method = RequestMethod.GET)
    public String groups() {
        List<Group> groups = groupRepository.findAll();
        return "List of groups \n";
    }

    @RequestMapping(value = "api/groups", method = RequestMethod.POST)
    public String newGroup(@RequestParam("name") String name) {
        Group group = new Group(name);
        groupRepository.save(group);
        return "New group \n";
    }


}
